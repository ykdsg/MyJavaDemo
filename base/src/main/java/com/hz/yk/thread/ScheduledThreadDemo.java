package com.hz.yk.thread;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wuzheng.yk
 * @date 2023/10/17
 */
public class ScheduledThreadDemo {

    private final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(5);

    /**
     * 任务时间大于间隔时间，也不会立刻执行，而是等上一个任务结束后再等待一个间隔时间执行
     *
     * @throws InterruptedException
     */
    @Test
    public void testScheduleWithFixedDelay() throws InterruptedException {
        final int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("start," + new Date());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("end ," + new Date());
            latch.countDown();
        }, 0, 1000, TimeUnit.MILLISECONDS);
        latch.await();
    }

    /**
     * 可以看到任务时间大于间隔时间的话，下个任务会立刻执行
     *
     * @throws InterruptedException
     */
    @Test
    public void testScheduleWithRate() throws InterruptedException {
        final int count = 10;
        AtomicInteger index = new AtomicInteger();

        CountDownLatch latch = new CountDownLatch(count);
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("-start," + new Date() + ",thread=" + Thread.currentThread().getName());
            try {
                if (index.get() == 0) {
                    Thread.sleep(10000);
                } else {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            index.getAndIncrement();
            System.out.println("-end ," + new Date());
            latch.countDown();
        }, 0, 1000, TimeUnit.MILLISECONDS);
        latch.await();
    }

    /**
     * 任务超时控制
     *
     * @throws InterruptedException
     */
    @Test
    public void testScheduleTimeout() throws InterruptedException {

        scheduler.scheduleAtFixedRate(new Runner(), 0, 1000, TimeUnit.MILLISECONDS);
        Thread.sleep(10000000);
    }

    private static class Caller implements Callable<Boolean> {

        @Override
        public Boolean call() {
            try {
                Thread.sleep(10000);
                System.out.println(new Date());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private static class Runner implements Runnable {

        @Override
        public void run() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Boolean> future = executor.submit(new Caller());
            try {
                future.get(1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("timeout");
                future.cancel(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
