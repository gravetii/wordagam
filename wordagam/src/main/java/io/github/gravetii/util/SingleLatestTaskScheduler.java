package io.github.gravetii.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class SingleLatestTaskScheduler {

  private static volatile SingleLatestTaskScheduler INSTANCE;

  private ExecutorService executor;

  private Future current;

  private SingleLatestTaskScheduler() {
    this.executor =
        Executors.newSingleThreadExecutor(
            (r) -> {
              Thread thread = new Thread(r);
              thread.setDaemon(true);
              return thread;
            });
  }

  public static SingleLatestTaskScheduler get() {
    if (INSTANCE == null) {
      synchronized (SingleLatestTaskScheduler.class) {
        if (INSTANCE == null) {
          INSTANCE = new SingleLatestTaskScheduler();
        }
      }
    }

    return INSTANCE;
  }

  public void submit(FutureTask<?> task) {
    if (this.current != null) {
      this.current.cancel(true);
    }

    this.current = this.executor.submit(task);
  }
}
