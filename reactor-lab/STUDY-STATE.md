# Reactor 学习状态与新对话接续

最后更新：2026-11-04。

本文件保存动态学习状态，是新会话的第一入口；课程顺序、时间预算与参考工程映射见 LEARNING-PATH.md。
“用例已实现 / 测试通过”“已讨论过”“能够独立解释”是三种不同状态，不得混为一谈。

## 1. 新会话先做什么

1. 遵循用户通用规范 `/Users/ykdsg/.codex/AGENTS.md` 以及项目适用规范。
2. 依次读取本文件、`reactor-lab/LEARNING-PATH.md`、`reactor-lab/README.md`。
3. 从下面的“当前断点”继续，只读取本轮相关方法，不扫描完整聊天历史或全部工程。
4. 每轮一个知识点、一个小案例；先确认理解，再推进下一例。

本文件中的项目相对路径以 `/Users/ykdsg/my_workspace/MyJavaDemo` 为根目录。
参考工程位于 `/Users/ykdsg/my_workspace/llm-agent`，只借鉴场景，不要求启动或修改它。

## 2. 当前断点：从这里继续

- 当前阶段：LEARNING-PATH.md 的第 0 阶段“先建立模型”。
- 当前状态：已讲解 Mono/Flux 的基本定义和类比，尚未按新模型课程逐例验收。
- 下一份代码：`reactor-lab/src/test/java/com/hz/yk/reactor/ReactorModelTest.java`。
- 下一方法：`testMonoListPublishesOneElementContainingAllChunks`。
- 开场问题：同样三个响应块，为什么 Mono<List<String>> 交付一个元素，而 Flux<String> 交付三个元素？
- 这道开场问题尚未收到学习者回答，不应记录为已掌握。

建议先用“结果通知通道”类比解释 Publisher 与结果的区别，再对照第一个和第二个方法。
讲清 Mono/Flux 后，再逐步引入 Subscriber、Subscription、request 与 cancel，不一轮解释完全部概念。

## 3. 学习者偏好与推进约定

- 优先搞懂框架模型、设计目的和类比，再学操作符，不以背 API 为目标。
- 参考 llm-agent 的真实代码，但在当前 reactor-lab 中运行独立、最小的教学用例。
- 示范与验收由助手准备，不默认要求学习者自己补测试或实现完整服务。
- 按“解释一个概念 → 预测一个案例 → 可选运行 → 即时纠错”推进；愿意看结果时再运行，不必每轮跑全量。
- 不要求启动 Spring、Redis、HTTP 或真实模型调用，不一次性粘贴大量生产源码。
- 不因前面讨论过某个操作符就跳过用户希望补强的模型，不反复布置旧练习。
- 没有完成口头或实践验收的内容，保持“已讲解 / 待确认”，不自动标记掌握。

## 4. 历史讨论摘要：用于定位，不当作全部掌握的证明

| 内容 | 历史情况 | 后续处理 |
| --- | --- | --- |
| just、fromCallable、defer、重复订阅 | 做过问答，讨论过值与任务描述、工厂的区别 | 在组装 / 订阅案例中顺带复习 |
| map、flatMap、concatMap | 完成过练习或顺序判断 | 不重新布置同样的基础题 |
| 空结果、错误、fallback、retry | 做过分支与调用次数判断 | 在任务编排中复习正常完成与失败阻断 |
| subscribeOn、publishOn | 正确判断过线程边界 | 区分“发布模型”与“执行线程”即可 |
| 背压、request、取消 | 讨论并已有示范 | 从 Publisher / Subscriber / Subscription 模型重新串联 |
| zip、timeout、商品详情聚合 | 大部分场景预测正确，曾误判空分支能组合出详情，已纠正 | 关注空完成与业务错误的差别 |
| 冷源、cache、TTL、retry 顺序 | 首次把缓存与不缓存的结果对调，后续正确判断错误缓存、重试和 TTL 时间线 | 不视为所有缓存设计都已掌握 |
| Reactor Context | 正确判断过读写位置；追问过为何向上游提供上下文，已解释 | 与项目显式传递的业务 Context 区分 |
| Mono.create | 已解释 sink 和回调桥接的概念 | 生命周期、竞态与资源释放尚待系统学习 |
| usingWhen、Sinks、takeUntilOther、retryWhen 等 | 结合参考工程做过概要介绍 | 后续章节仍待逐项学习，不标完成 |

需要留意的历史易错点：

1. 复用同一个冷源 Mono，不等于复用一次执行结果；缓存后的 Mono 还需要复用同一个实例。
2. 空完成不是发出 null；zip 的必填分支为空时，不会自动生成详情。
3. 分支内恢复错误不代表分支完全隔离，未恢复的错误仍可能终止整个聚合。
4. cache 不只缓存成功值；TTL 到期不意味着自动刷新，读取缓存不等于续期。
5. 有业务 Context 对象，不等于使用了 Reactor Context；Mono/Flux 也不等于自动启动后台线程。

## 5. 本地材料与学习验收状态

| 材料 | 代码状态 | 学习状态 |
| --- | --- | --- |
| ReactorBasicsTest 及已有用户练习 | 已存在、历史测试通过 | 有历史学习记录，按需复习 |
| product/ProductDetailServiceTest | 已实现 14 个验收 | 已做过部分业务场景问答 |
| ReactorModelTest | 六个模型案例已实现并通过 | 尚未逐例验收，从首例开始 |
| CompletionFlowTest | 六个编排案例已实现并通过 | 尚未正式推进本章 |
| 回调与资源、事件驱动、流式重试后续章节 | 路线已有，专门的下一阶段案例尚未落地 | 待模型与编排篇完成后推进 |

模型篇待验收清单（不是实现任务）：

- [ ] `testMonoListPublishesOneElementContainingAllChunks`
- [ ] `testFluxPublishesChunksAsSeparateElements`
- [ ] `testVoidMonoRunsTaskWithoutPublishingAnElement`
- [ ] `testAssemblySubscriptionAndRequestedDeliveryAreDifferentStages`
- [ ] `testSubscriberReceivesSubscriptionAndControlsDemand`
- [ ] `testCancellationStopsDeliveryWithoutNormalCompletion`

编排篇全部方法位于 `reactor-lab/src/test/java/com/hz/yk/reactor/CompletionFlowTest.java`，
学习顺序见 LEARNING-PATH.md；目前均未逐例验收，不提前勾选为完成。

## 6. 最近一次工程验证快照

- 验证日期：2026-11-04，模型和编排用例落地时。
- 新增模型篇与编排篇：12 个测试通过；reactor-lab 全量：52 个测试通过，零失败、零错误、零跳过。
- 当时新增的两个 Java 文件经 ai-check 与 ai-refactor 检查无违规。
- 这是上次实际运行结果，不代表新会话中已经重新执行；仅修改学习文档无需重复运行 Maven。
- 已有用户或前序任务的未提交 / 已暂存改动，不得顺带提交、重置或重新暂存。

单独运行当前案例（从当前项目根目录）：

```bash
mvn -B -f reactor-lab/pom.xml -Dtest=ReactorModelTest#testMonoListPublishesOneElementContainingAllChunks test
```

## 7. 新对话可复制的提示

```text
继续我的 Reactor 学习。当前项目是 /Users/ykdsg/my_workspace/MyJavaDemo。
请先读取 reactor-lab/STUDY-STATE.md、reactor-lab/LEARNING-PATH.md 和 reactor-lab/README.md，
遵循项目及用户通用规范，从学习状态文件中的当前断点继续。
先讲框架模型和类比，再用本地最小案例理解，参考 llm-agent 但不启动它。
每轮只推进一个知识点，不要求我自己补测试；不要把测试通过等同于我已掌握。
本轮学习结束后，请更新 STUDY-STATE.md 中的验收状态、易错点与下一断点。
```

## 8. 后续如何维护

- 学完一个小案例后，根据学习者实际回答或明确反馈，更新对应勾选、日期及下一方法。
- 分开记录“已讲解”“能独立解释”“待复习”，不能仅凭助手给出答案就标记掌握。
- 新出现的理解偏差简要加入易错点；已无必要的细节可以压缩，不粘贴完整聊天记录。
- 课程顺序或新增案例改变时更新 LEARNING-PATH.md；学习进度只在本文件维护，避免两份进度冲突。
