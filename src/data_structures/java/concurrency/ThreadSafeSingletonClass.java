package data_structures.java.concurrency;

 class ThreadSafeSingletonClass {
    private static ThreadSafeSingletonClass threadSafeSingletonClassInstance;

    private ThreadSafeSingletonClass() {

    }

    public synchronized static ThreadSafeSingletonClass getInstance() {
        ThreadSafeSingletonClass threadSafeSingletonClassInstance = null;
        if (threadSafeSingletonClassInstance == null) {
            threadSafeSingletonClassInstance = new ThreadSafeSingletonClass();
        }
        return threadSafeSingletonClassInstance;
    }
}