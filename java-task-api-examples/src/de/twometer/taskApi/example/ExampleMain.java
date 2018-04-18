package de.twometer.taskApi.example;

import de.twometer.taskApi.annotations.Async;
import de.twometer.taskApi.core.Task;

import static de.twometer.taskApi.TaskApi.await;

public class ExampleMain {

    public static void main(String[] args) {
        System.out.println("Startup");
        (new ExampleMain()).testAsyc();
        System.out.println("Control came back");
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

    @Async
    private void testAsyc() {
        System.out.println("Awaiting method");
        await(longOperation());
        System.out.println("Done awaiting");
    }

}
