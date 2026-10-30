# Reactor 两小时入门实验

这是独立 Maven 练习目录，不继承仓库父 POM，不修改 base 或 ddd 模块。
需要 JDK 11+ 和 Maven；首次运行需要下载依赖，测试本身不启动服务、不访问网络。

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
