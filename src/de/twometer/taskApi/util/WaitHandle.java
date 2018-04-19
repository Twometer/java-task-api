package de.twometer.taskApi.util;

public class WaitHandle {

    private final Object handle = new Object();

    public void await() {
        synchronized (handle) {
            try {
                handle.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyHandle() {
        synchronized (handle) {
            handle.notifyAll();
        }
    }

}
