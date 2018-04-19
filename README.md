# java-task-api
Task-based asynchronous pattern for java.

_Make porting from C# to java easy_

## Getting started
To use the following code examples with this syntax, you'll need the following import statements
```java
import de.twometer.taskApi.core.*;
import static de.twometer.taskApi.TaskApi.*;
```

## Running custom tasks
```csharp
    private Task LongOperation() {
        return Task.Run(() => {
            Thread.Sleep(10000);
        });
    }
```

becomes

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

## Async methods
```csharp
   private async void testAsync() {
      Console.WriteLine("Awaiting method");
      await longOperation;
      Console.WriteLine("Done awaiting");
   }
```
becomes
```java
    private void testAsync() {
        async(() -> {
            System.out.println("Awaiting method");
            await(longOperation());
            System.out.println("Done awaiting");
        });
    }
```

## Async Task methods
```csharp
   private async Task testAsync() {
      Console.WriteLine("Awaiting method");
      await longOperation();
      Console.WriteLine("Done awaiting");
   }
```
becomes
```java
    private Task testAsync() {
        return asyncTask(() -> {
            System.out.println("Awaiting method");
            await(longOperation());
            System.out.println("Done awaiting");
        });
    }
```

## Task with return values
```csharp
   private async Task<string> testAsync() {
      Console.WriteLine("Awaiting method");
      await longOperation;
      Console.WriteLine("Done awaiting");
      return "Hi";
   }
```
becomes
```java
    private GenericTask<String> testAsync() {
        return asyncGenericTask(String.class, () -> {
            System.out.println("Awaiting method");
            await(longOperation());
            System.out.println("Done awaiting");
			return "Hi";
        });
    }
```

## Utility methods
- `Task.delay(int millis)` like in C#