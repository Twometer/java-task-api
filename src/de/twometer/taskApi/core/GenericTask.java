package de.twometer.taskApi.core;

public abstract class GenericTask<T> extends Task {

    private T result;

    public void execute() {
        result = executeGeneric();
    }

    public abstract T executeGeneric();

    public T getResult() {
        return result;
    }
}
