package de.twometer.taskApi.core;

public class TaskEngine {

    public static void executeAsyncMethod(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void runTask(Task task) {

    }
}
