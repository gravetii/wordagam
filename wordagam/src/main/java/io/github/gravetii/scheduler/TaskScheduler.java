package io.github.gravetii.scheduler;

import java.util.concurrent.*;

public class TaskScheduler {

    private static volatile TaskScheduler INSTANCE;

    private ExecutorService executor;

    public static TaskScheduler get() {
        if (INSTANCE == null) {
            synchronized (TaskScheduler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskScheduler();
                }
            }
        }

        return INSTANCE;
    }

    private TaskScheduler() {
        this.executor = Executors.newFixedThreadPool(1);
    }

    public void submit(Runnable task) {
        executor.submit(task);
    }

    public Future<?> submit(Callable<?> task) {
        return executor.submit(task);
    }

    public void close() {
        try {
            this.executor.shutdown();
            this.executor.awaitTermination(2, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            // log exception
        }
    }

}
