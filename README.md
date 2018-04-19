# java-task-api
Task-based asynchronous pattern for java


## Running custom tasks
```java
    private Task longOperation() {
        return Task.run(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
```

## Async & await
```java
    @Async
    private void testAsync() {
        System.out.println("Awaiting method");
        await(longOperation());
        System.out.println("Done awaiting");
    }
```