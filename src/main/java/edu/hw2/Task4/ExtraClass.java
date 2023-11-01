package edu.hw2.Task4;

public class ExtraClass {

    private ExtraClass() {

    }

    public static CallingInfo extraMethod() {
        return CallingManager.callingInfo();
    }
}
