package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task7 {

    private Task7() {

    }

    public static boolean lengthThreeAndZero(String inputString) {

        String regex = "^[0|1]{2}0[0|1]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

    public static boolean sameStartEnd(String inputString) {

        String regex = "^(0|1)[0|1]*\\1$$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

    public static boolean lengthBetween1and3(String inputString) {

        String regex = "^[0|1]{1,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }
}
