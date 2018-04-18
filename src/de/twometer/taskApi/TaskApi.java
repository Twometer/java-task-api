package de.twometer.taskApi;

import de.twometer.taskApi.core.GenericTask;
import de.twometer.taskApi.core.Task;

public class TaskApi {

    public static void await(Task task) {
        task.execute();
    }

    public static <T> T await(GenericTask<T> task) {
        task.execute();
        return task.getResult();
    }

}
