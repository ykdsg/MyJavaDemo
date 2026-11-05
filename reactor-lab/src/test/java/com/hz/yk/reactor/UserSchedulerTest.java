package com.hz.yk.reactor;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSchedulerTest {

    private static final String USER_NAME = "Alice";
    private static final String SOURCE_SCHEDULER_NAME = "user-source";
    private static final String DOWNSTREAM_SCHEDULER_NAME = "user-downstream";
    private static final Duration VERIFY_TIMEOUT = Duration.ofSeconds(3);

    @Test
    public void testSynchronousSourceRunsOnCallingThread() {
        Thread callingThread = Thread.currentThread();
        AtomicReference<Thread> sourceThread = new AtomicReference<>();
        AtomicReference<Thread> mapThread = new AtomicReference<>();

        Mono<String> result = Mono.fromCallable(() -> {
            sourceThread.set(Thread.currentThread());
            return USER_NAME;
        }).map(userName -> {
            mapThread.set(Thread.currentThread());
            return userName;
        });

        StepVerifier.create(result)
                .expectNext(USER_NAME)
                .expectComplete()
                .verify(VERIFY_TIMEOUT);

        assertSame(callingThread, sourceThread.get());
        assertSame(callingThread, mapThread.get());
    }

    @Test
    public void testPublishOnChangesOnlyDownstreamThread() {
        Thread callingThread = Thread.currentThread();
        AtomicReference<Thread> sourceThread = new AtomicReference<>();
        AtomicReference<Thread> mapBeforeThread = new AtomicReference<>();
        AtomicReference<Thread> mapAfterThread = new AtomicReference<>();
        Scheduler downstreamScheduler = Schedulers.newSingle(DOWNSTREAM_SCHEDULER_NAME);

        try {
            Mono<String> result = Mono.fromCallable(() -> {
                sourceThread.set(Thread.currentThread());
                return USER_NAME;
            }).map(userName -> {
                mapBeforeThread.set(Thread.currentThread());
                return userName;
            }).publishOn(downstreamScheduler).map(userName -> {
                mapAfterThread.set(Thread.currentThread());
                return userName;
            });

            StepVerifier.create(result)
                    .expectNext(USER_NAME)
                    .expectComplete()
                    .verify(VERIFY_TIMEOUT);

            assertSame(callingThread, sourceThread.get());
            assertSame(callingThread, mapBeforeThread.get());
            assertNotSame(callingThread, mapAfterThread.get());
            assertTrue(mapAfterThread.get().getName().startsWith(DOWNSTREAM_SCHEDULER_NAME));
        } finally {
            downstreamScheduler.dispose();
        }
    }

    @Test
    public void testSubscribeOnAndPublishOnUseSeparateThreads() {
        Thread callingThread = Thread.currentThread();
        AtomicReference<Thread> sourceThread = new AtomicReference<>();
        AtomicReference<Thread> mapAThread = new AtomicReference<>();
        AtomicReference<Thread> mapBThread = new AtomicReference<>();
        AtomicReference<Thread> mapCThread = new AtomicReference<>();
        Scheduler sourceScheduler = Schedulers.newSingle(SOURCE_SCHEDULER_NAME);
        Scheduler downstreamScheduler = Schedulers.newSingle(DOWNSTREAM_SCHEDULER_NAME);

        try {
            Mono<String> result = Mono.fromCallable(() -> {
                sourceThread.set(Thread.currentThread());
                return USER_NAME;
            }).map(userName -> {
                mapAThread.set(Thread.currentThread());
                return userName;
            }).subscribeOn(sourceScheduler).map(userName -> {
                mapBThread.set(Thread.currentThread());
                return userName;
            }).publishOn(downstreamScheduler).map(userName -> {
                mapCThread.set(Thread.currentThread());
                return userName;
            });

            StepVerifier.create(result)
                    .expectNext(USER_NAME)
                    .expectComplete()
                    .verify(VERIFY_TIMEOUT);

            assertNotSame(callingThread, sourceThread.get());
            assertTrue(sourceThread.get().getName().startsWith(SOURCE_SCHEDULER_NAME));
            assertSame(sourceThread.get(), mapAThread.get());
            assertSame(sourceThread.get(), mapBThread.get());
            assertNotSame(sourceThread.get(), mapCThread.get());
            assertNotSame(callingThread, mapCThread.get());
            assertTrue(mapCThread.get().getName().startsWith(DOWNSTREAM_SCHEDULER_NAME));
        } finally {
            downstreamScheduler.dispose();
            sourceScheduler.dispose();
        }
    }
}
