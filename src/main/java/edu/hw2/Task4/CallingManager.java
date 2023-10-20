package edu.hw2.Task4;

public class CallingManager {

    private CallingManager() {

    }

    public static CallingInfo callingInfo() {

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        String className = stack[2].getClassName();
        String methodName = stack[2].getMethodName();

        return new CallingInfo(className, methodName);
    }
}
