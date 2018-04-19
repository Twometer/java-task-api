package de.twometer.taskApi;

import de.twometer.taskApi.core.GenericTask;
import de.twometer.taskApi.core.Task;
import de.twometer.taskApi.core.TaskEngine;

public class TaskApi {

    public static void await(Task task) {
        task.getWaitHandle().await();
    }

    public static <T> T await(GenericTask<T> task) {
        task.getWaitHandle().await();
        return task.getResult();
    }

    public static void async(Runnable runnable) {
        TaskEngine.executeAsyncMethod(runnable);
    }

}
