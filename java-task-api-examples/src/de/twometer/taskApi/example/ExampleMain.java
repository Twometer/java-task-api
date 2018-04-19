package de.twometer.taskApi.example;

import de.twometer.taskApi.core.GenericTask;
import de.twometer.taskApi.core.Task;

import static de.twometer.taskApi.TaskApi.async;
import static de.twometer.taskApi.TaskApi.asyncTask;
import static de.twometer.taskApi.TaskApi.await;

public class ExampleMain {

    public static void main(String[] args) {
        System.out.println("Startup");
        (new ExampleMain()).test();
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

    private void test() {
        System.out.println("> Async test");
        testAsync();
        System.out.println("  Async test return");
        System.out.println("> Await async test");
        await(testAsyncTask());
        System.out.println("  Done external");
    }

    private void testAsync() {
        async(() -> {
            System.out.println("Awaiting method");
            await(longOperation());
            System.out.println("Done awaiting, testing with return");
            System.out.println(await(longStringOperation()));
        });
    }

    private Task testAsyncTask() {
        return asyncTask(() -> {
            await(longOperation());
            System.out.println("Done internal");
        });
    }

}
