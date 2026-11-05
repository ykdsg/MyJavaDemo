# Reactor 两小时入门实验

这是独立 Maven 练习目录，不继承仓库父 POM，不修改 base 或 ddd 模块。
需要 JDK 11+ 和 Maven；首次运行需要下载依赖，测试本身不启动服务、不访问网络。

## 当前学习入口：先模型，后工程用法

跨对话继续学习：先读 `STUDY-STATE.md`，再读 `LEARNING-PATH.md`。
前者记录学习进度、易错点、下一案例与可复制的新对话提示，后者保存课程路线和参考工程映射。
用例已实现、测试通过不等于已经学完，后续会按实际理解更新学习状态。

如果已完成前面的入门练习，接下来从 `LEARNING-PATH.md` 开始，不必重复旧题。
参考 llm-agent 的业务场景，但在本目录独立运行：先读 ReactorModelTest 的六个模型案例，
再看 CompletionFlowTest 的六个任务编排案例。示范已经实现，不要求你补测试。
资源生命周期、事件总线和流式重试按学习进度继续新增，路线图中的后续阶段尚未实现。

```bash
mvn -B -f reactor-lab/pom.xml "-Dtest=ReactorModelTest,CompletionFlowTest" test
```

从仓库根目录执行；两个测试类均不连接参考工程，不增加业务依赖。

## 第一课：信号与订阅（约 15 分钟）

目标：能解释返回什么、何时执行、如何验证。暂时不讨论线程切换。

### 1. 运行

从仓库根目录执行：

```bash
mvn -f reactor-lab/pom.xml test
```

只运行一个案例，例如延迟执行案例：

```bash
mvn -f reactor-lab/pom.xml -Dtest=ReactorBasicsTest#callableShouldExecuteOnceForEachSubscription test
```

### 2. 按下面顺序阅读案例，不依赖 JUnit 实际执行顺序

案例文件：src/test/java/com/hz/yk/reactor/ReactorBasicsTest.java。

| 案例 | 看什么 | 应当能解释 |
| --- | --- | --- |
| monoShouldEmitOneValueThenComplete | expectNext + expectComplete | 一个元素之后正常结束 |
| fluxShouldEmitValuesInOrderThenComplete | 多次元素信号 | 一个序列可以有多个元素 |
| emptyMonoShouldCompleteWithoutValues | 只有完成信号 | 空不等于失败，也不是发出 null |
| errorMonoShouldFailWithoutNormalCompletion | 错误类型与消息 | 错误终止和正常终止互斥 |
| callableShouldExecuteOnceForEachSubscription | 三处计数断言 | 组装为 0，订阅后为 1、2 |
| justShouldCaptureAnEagerlyComputedValue | 参数求值与重复订阅 | 组装已经调用一次，两次订阅均得到 1 |
| doOnNextShouldExecuteOnlyWhenAValueIsDelivered | 副作用计数 | 组装不调用回调，收到元素才调用 |

Mono 表示 0～1 个元素，Flux 表示 0～N 个元素。正常终止是 onComplete，失败终止是 onError。
序列也可能持续运行或被取消；不是所有序列都一定正常终止。

StepVerifier.create 构造验证器，verify 才触发这里的测试订阅并检查信号。
expectNext 检查元素及顺序，expectComplete 检查正常结束，expectErrorMatches 检查失败。
每个 verify 都有等待上限，避免写错链后无限等待。

不要把“没有订阅就不会执行”当作无条件规则：Java 会先计算 just 的方法参数。
fromCallable 将调用保存为任务；在本课默认请求下，每次订阅都会重新调用一次。
它不会自动缓存，也不会自动切换到后台线程。

### 3. 先预测，再运行

1. fromCallable 案例中，组装后、第一次验证后、第二次验证后，callCount 各是多少？
2. just 案例对应三处计数各是多少？两次订阅分别收到什么？
3. 为什么 Mono.empty 不能用 expectNext(null) 验证？
4. 去掉 verify，只保留 create 和 expectNext，是否已经执行验证？

## 首题 A1：偶数过滤与放大（约 10 分钟）

在 src/main/java/com/hz/yk/reactor/NumberPipeline.java 新增一个方法：

- 方法名：filterEvenAndScale。
- 输入：Flux<Integer> source；调用方传入非 null 的 Publisher，null 参数需立即拒绝。
- 输出：Flux<Integer>。
- 输入序列 1、2、3、4、5、6；保留偶数，再乘以 10。
- 不允许在方法内调用 subscribe、block 或 Thread.sleep。
- 不转换成 List 后使用 Stream；直接使用 Reactor 操作符。
- 按项目规范将用到的数值定义为具名常量。

在 src/test/java/com/hz/yk/reactor/NumberPipelineTest.java 编写验收：

| Case | 输入 | 预期信号 |
| --- | --- | --- |
| 正常输入 | 1～6 | 20、40、60，然后完成 |
| 空序列 | Flux.empty | 无元素，正常完成 |
| 全是奇数 | 1、3、5 | 无元素，正常完成 |
| 包含零和负数 | -2、-1、0、1、2 | -20、0、20，然后完成 |
| 上游失败 | Flux.error | 保留同一异常，不转换成成功 |
| 非法参数 | null Publisher | 立即拒绝，使用明确的异常类型 |

提示：先考虑 filter，再考虑 map；方法只描述处理链，订阅交给测试。
验证完成后运行 ai-check check <新增的每个 Java 文件> --json，要求零 error。

## 后续课程，按完成情况逐步推进

1. map / flatMap / concatMap：值转换、异步展开与顺序。
2. switchIfEmpty / onErrorResume / retry：空结果、错误与重新订阅。
3. subscribeOn / publishOn：线程边界与阻塞源包装。
4. 按需请求、取消与虚拟时间测试。
5. 商品详情聚合：正常、空结果、降级与超时验收。

先完成第一课和 A1，不需要现在学习所有操作符。

## 综合题：商品详情聚合

本节约 15–20 分钟，不连接真实接口。先阅读
`src/main/java/com/hz/yk/reactor/product/ProductDetailService.java`，再运行
`src/test/java/com/hz/yk/reactor/product/ProductDetailServiceTest.java`。

```bash
mvn -B -f reactor-lab/pom.xml -Dtest=ProductDetailServiceTest test
```

命令从仓库根目录执行；若已进入 reactor-lab，省略 `-f reactor-lab/pom.xml`。

业务契约：

| 分支 | 正常结果 | 空结果 | 错误 |
| --- | --- | --- | --- |
| 商品 | 商品名称 | 商品不存在异常 | 原样传播 |
| 价格 | 实时价格 | 价格不可用异常，不访问缓存 | 查询缓存；缓存为空也报价格不可用，缓存失败继续报错 |
| 库存 | IN_STOCK / OUT_OF_STOCK | UNKNOWN | 超过 200ms 未返回降级 UNKNOWN，其他非超时错误传播 |

阅读顺序：

1. `testSuccessDoesNotSubscribeToCache`：三个分支如何组合成一个详情，缓存为何没被订阅。
2. `testMissingProductFailsInsteadOfCompletingEmpty`：先规范化空分支，避免 zip 静默完成。
3. `testPriceFailureUsesCachedPrice` 与 `testCacheFailureIsNotSwallowed`：局部恢复错误，不兜底整条链。
4. `testStockTimeoutUsesUnknownAndCancelsSource`：199ms 无结果，200ms 降级，并取消库存源。
5. `testAsyncSourcesAggregateAfterSlowestSource`：50/80/100ms 三个异步源在 100ms 聚合，而非 230ms。

注意：zip 本身不创建线程，也不把阻塞调用自动并行化。时间案例在 withVirtualTime 的 Supplier
内创建整条链，没有真实 sleep。业务代码不调用 subscribe 或 block；订阅由测试或调用方负责。

自测问题：如果直接将原始的空商品 Mono 交给 zip，会得到业务错误还是空完成？
如果把价格分支的 onErrorResume 移到 zip 后面，会影响哪些错误？
