package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task4 {

    private Task4() {

    }

    public static boolean passwordContains(String password) {
        String regex = "[~!@#$%^&*|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
