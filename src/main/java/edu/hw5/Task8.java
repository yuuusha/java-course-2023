package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task8 {

    private Task8() {

    }

    public static boolean oddLength(String inputString) {

        String regex = "^[0|1]([0|1][0|1])*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

    public static boolean oddLengthWhen0AndEvenLengthWhen1(String inputString) {

        String regex = "^(0([0|1][0|1])*)$|^(1[0|1]([0|1][0|1])*)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

    public static boolean everyOddSymbolIs1(String inputString) {

        String regex = "^(1[0|1])*(1)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

    public static boolean allExcept11And111(String inputString) {

        String regex = "^(?!11$|111$)[0|1]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }
}
