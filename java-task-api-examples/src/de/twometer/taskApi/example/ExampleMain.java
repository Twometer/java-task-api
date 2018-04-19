package de.twometer.taskApi.example;

import de.twometer.taskApi.core.GenericTask;
import de.twometer.taskApi.core.Task;

import static de.twometer.taskApi.TaskApi.async;
import static de.twometer.taskApi.TaskApi.await;

public class ExampleMain {

    public static void main(String[] args) {
        System.out.println("Startup");
        (new ExampleMain()).testAsync();
        System.out.println("Control came back");
    }

    private GenericTask<String> longStringOperation() {
        return Task.runGeneric(String.class, () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Test";
        });
    }

    private Task longOperation() {
        return Task.run(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void testAsync() {
        async(() -> {
            System.out.println("Awaiting method");
            await(longOperation());
            System.out.println("Done awaiting");
            System.out.println(await(longStringOperation()));
        });
    }

}
