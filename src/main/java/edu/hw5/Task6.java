package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task6 {

    private Task6() {

    }

    public static boolean isSubstring(String mainString, String subString) {
        String regex = ".*" + subString + ".*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mainString);

        return matcher.matches();
    }
}
