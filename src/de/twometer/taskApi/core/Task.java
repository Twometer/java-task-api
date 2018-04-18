package de.twometer.taskApi.core;

import de.twometer.taskApi.api.GenericRunnable;

public abstract class Task {

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

    public abstract void execute();

}
