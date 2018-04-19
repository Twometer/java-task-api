package de.twometer.taskApi.core;

import de.twometer.taskApi.TaskApi;
import de.twometer.taskApi.api.GenericRunnable;
import de.twometer.taskApi.util.WaitHandle;

public abstract class Task {

    private WaitHandle waitHandle;

    public static Task run(Runnable runnable) {
        Task t = new Task() {
            @Override
            public void execute() {
                runnable.run();
            }
        };
        TaskEngine.runTask(t);
        return t;
    }

    public static <T> GenericTask<T> runGeneric(Class<T> returnType, GenericRunnable<T> runnable) {
        GenericTask<T> t = new GenericTask<T>() {
            @Override
            public T executeGeneric() {
                return runnable.run();
            }
        };
        TaskEngine.runTask(t);
        return t;
    }

    public static Task delay(int millis) {
        return run(() -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static Task waitAll(Task... tasks) {
        return TaskApi.asyncTask(() -> {
           for(Task t : tasks) TaskApi.await(t);
        });
    }

    public abstract void execute();

    public Task() {
        this.waitHandle = new WaitHandle();
    }

    public WaitHandle getWaitHandle() {
        return waitHandle;
    }
}
