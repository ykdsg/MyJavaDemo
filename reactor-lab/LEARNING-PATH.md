# 模型优先：从 Reactor 实验到 llm-agent 源码

新对话先读 `STUDY-STATE.md`：当前学习断点、历史讨论摘要、待验收内容和接续提示保存在那里。
本文件只维护静态课程路线；表中的“已实现”表示代码就绪，不表示学习者已经掌握。

## 目标与学习方式

先搞懂发布模型，再学任务编排，最后看资源与事件驱动。参考 llm-agent 的业务意图，
但所有实验都在当前 reactor-lab 内完成：不启动参考工程，不调用模型，不连接 Redis 或数据库。
示范和断言已经写好，不要求你补测试。每次只读一个方法，按“预测 → 单独运行 → 解释 → 回看业务”推进。

本实验保留 Reactor 3.8.8；参考工程当前 POM 管理 Reactor 3.6.9，不为阅读它而修改依赖。
下面映射的是这些版本共有的基础语义，不是生产实现的完整复制。

## 接下来的 90 分钟

| 阶段 | 时间 | 要解决的问题 | 当前状态 |
| --- | --- | --- | --- |
| 0. 框架模型 | 15 分钟 | Mono/Flux 是什么，谁订阅，谁请求，谁接收 | ReactorModelTest 六例已实现 |
| 1. 完成信号编排 | 20 分钟 | 没有元素的任务如何驱动下一阶段 | CompletionFlowTest 六例已实现 |
| 2. 回调与资源 | 20 分钟 | SSE、租约如何结束及清理 | 后续按进度新增，尚未实现 |
| 3. 事件驱动 | 20 分钟 | 工作流如何被事件驱动和停止 | 后续按进度新增，尚未实现 |
| 4. 流式重试 | 10 分钟 | 已经输出内容后还能否重试 | 后续按进度新增，尚未实现 |
| 5. 串联验收 | 5 分钟 | 独立解释触发、数据、顺序、终止、资源 | 学完后口头验收 |

这些时间是学习预算，不要求一次读完。后续阶段只列学习目标，没有创建空测试类或未完成方法。

## 0. 先建立模型

把 Publisher 类比为“结果通知通道”，而不是结果本身。Mono 最多交付一个元素；Flux 可以交付多个。
通道还会通知正常完成或错误，也可能继续等待或被取消。Mono<Void> 不交付业务元素，仍能通知完成或错误。

| 角色 | 含义 | 本地用例中的对象 |
| --- | --- | --- |
| Publisher | 提供数据与终止信号 | Mono、Flux |
| Subscriber | 接收订阅握手、元素和终止信号 | BaseSubscriber 或 StepVerifier 内部订阅者 |
| Subscription | 一次订阅的控制句柄 | onSubscribe 提供，可 request 或 cancel |

```text
建立关系：Subscriber → subscribe → Publisher
交付句柄：Publisher → onSubscribe(Subscription) → Subscriber
请求元素：Subscriber → Subscription.request(n) → 上游
交付结果：Publisher → onNext(value) → Subscriber
终止通知：Publisher → onComplete / onError → Subscriber
主动取消：Subscriber → Subscription.cancel() → 上游
```

请求量约束元素交付，不代表所有副作用都一定延迟到 request；源可能在订阅阶段就启动外部工作。
本课使用同步且按需求计算的 fromSupplier 来把三个阶段分开观察，不将它推广成所有 Publisher 的规则。

### 阅读顺序

文件：`src/test/java/com/hz/yk/reactor/ReactorModelTest.java`。
以下是学习顺序，不依赖 JUnit 实际运行顺序。

| 方法 | 观察点 | 要解释的问题 |
| --- | --- | --- |
| testMonoListPublishesOneElementContainingAllChunks | 一次请求收到整个列表 | 列表里有三项，为什么仍是一个元素？ |
| testFluxPublishesChunksAsSeparateElements | 同样三项逐个交付 | Flux 与 List 的差别是什么？ |
| testVoidMonoRunsTaskWithoutPublishingAnElement | saved 从 false 变 true，只有完成 | 没有 onNext 是否等于任务没执行？ |
| testAssemblySubscriptionAndRequestedDeliveryAreDifferentStages | 计数与线程身份 | 组装、订阅握手、请求交付分别发生了什么？ |
| testSubscriberReceivesSubscriptionAndControlsDemand | 显式三个角色，先请求一项，再请求两项 | 订阅是否等于立刻收到全部元素？ |
| testCancellationStopsDeliveryWithoutNormalCompletion | 只收到首项，取消为 true、完成为 false | 取消与正常完成有什么区别？ |

空完成、错误类型与错误消息无需另写一套用例，复习既有 ReactorBasicsTest 的
`emptyMonoShouldCompleteWithoutValues` 和 `errorMonoShouldFailWithoutNormalCompletion` 即可。

本章没有配置 Scheduler。线程身份断言用于说明 Mono/Flux 不自动创建后台线程，
不是说所有 Mono/Flux 都必须在订阅线程执行。

## 1. 用完成信号组织任务

文件：`src/test/java/com/hz/yk/reactor/CompletionFlowTest.java`。
先能解释 Mono<Void>，再看本章，不需要先掌握资源管理或事件总线。

| 方法 | 项目类比 | 要解释的问题 |
| --- | --- | --- |
| testVoidTaskCompletesWithoutCallingFlatMapMapper | 保存不返回业务值 | 为什么 flatMap 映射函数没有执行？ |
| testThenStartsAgentAfterInitializationCompletes | 初始化 → Agent 循环 | 后一步为什么能在没有前一步元素时执行？ |
| testThenReturnPreservesAnswerAfterSavingCompletes | afterRun → 原 AgentResult | 为什么保存完成后还可以返回原结果？ |
| testThenManyAndConcatWithRunToolsAfterAllChunks | 初始化 → 模型响应块 → 工具阶段 | 工具判断看到的是不是完整的响应块？ |
| testInitializationErrorPreventsAgentSubscription | 初始化失败 | 后续阶段是否还会被订阅？ |
| testThenDelaysSubscriptionButDeferAlsoDelaysFactoryCall | then 与 defer 组合 | 延迟订阅为什么不等于延迟方法调用？ |

```text
flatMap：拿到一个上游元素，基于该元素进入后续 Mono
then：等上游正常完成，再订阅后续 Mono，忽略前面的元素
thenMany：等上游正常完成，再订阅后续多元素流
thenReturn：等上游正常完成，再交付一个已有值
concatWith：前段正常完成后接后段，保留两段交付的元素
defer：把源的工厂调用放到订阅时，避免组装时就构造或读取状态
```

这些是本章使用方式，不是完整 API 手册。特别注意：正常完成可以没有元素；错误不是正常完成。

## 如何运行

从当前仓库根目录运行一个方法：

```bash
mvn -B -f reactor-lab/pom.xml -Dtest=ReactorModelTest#testMonoListPublishesOneElementContainingAllChunks test
```

运行模型篇和编排篇：

```bash
mvn -B -f reactor-lab/pom.xml "-Dtest=ReactorModelTest,CompletionFlowTest" test
```

如果已经进入 reactor-lab 目录，省略 `-f reactor-lab/pom.xml`。
每个案例独立运行，断言是观察结果，不用依赖控制台打印，不用先学完测试框架。

## 回看参考工程时找哪里

参考根目录：`/Users/ykdsg/my_workspace/llm-agent`。下面路径均相对该根目录，按类名和方法定位即可。

| 本地学习内容 | 参考工程文件 | 重点位置 |
| --- | --- | --- |
| 初始化、then、thenReturn | engine-biz/src/main/java/com/yt/llm/server/engine/biz/framework/agent/kernel/AgentLifecycleExecutor.java | execute、initialize、invokeAfterRun |
| thenMany、concatWith、defer | engine-biz/src/main/java/com/yt/llm/server/engine/biz/framework/chain/chat/LlmChain.java | executeRecursiveTurn |
| 回调桥接，后续再学 | engine-biz/src/main/java/com/yt/llm/server/engine/biz/framework/supporter/GeneralLlmSupporter.java | Flux.create、SSE listener、onDispose |
| 租约与取消竞态，后续再学 | engine-biz/src/main/java/com/yt/llm/server/engine/biz/integration/workflow/thread/RedisGraphThreadExecutionCoordinator.java | acquire、release、lossSignal |
| 资源与停止信号，后续再学 | engine-biz/src/main/java/com/yt/llm/server/engine/biz/biz/completion/CompletionBiz.java | runInGraphThread、executeUnderThreadLease |
| 事件总线，后续再学 | engine-biz/src/main/java/com/yt/llm/server/engine/biz/framework/chain/workflow/graph/GraphRuntime.java | initEventBus、dispatchTask、ensureResultSaverInitialized |
| 流式重试，后续再学 | engine-biz/src/main/java/com/yt/llm/server/engine/biz/framework/supporter/AbstractGeneralServiceSupporter.java | sseRetry |

不要照搬生产工程的业务复杂度。模型篇只有一处手动 subscribe，用于直接展示 Subscriber 和 Subscription；
其他案例由 StepVerifier 驱动。显式手动订阅意味着需要考虑生命周期，不代表每个业务方法都应自行 subscribe。

## 每轮学习的验收方式

看完一个方法后，先不看断言，回答四句话：谁发、谁收、何时执行、怎样结束。
编排篇再补一句：后一步依赖前一步的值，还是只依赖前一步正常完成？
答对就进入下一个案例；答错只对照一个最小案例，不要求你编写测试或重复完成旧练习。
