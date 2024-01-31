package edu.hw8.Task3;

public class PasswordGenerator {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";

    private PasswordGenerator() {

    }

//    public static void main(String[] args) {
//
//        String password = nextPassword("999");
//        System.out.println(password);
//    }

    public static String nextPassword(String current) {
        char[] passwordArray = current.toCharArray();
        int index = passwordArray.length - 1;

        while (index >= 0) {
            int charIndex = ALPHABET.indexOf(passwordArray[index]);

            if (charIndex < ALPHABET.length() - 1) {
                passwordArray[index] = ALPHABET.charAt(charIndex + 1);
                break;
            } else {
                passwordArray[index] = ALPHABET.charAt(0);
                index--;
            }
        }

        if (index < 0 && passwordArray[0] == ALPHABET.charAt(0)) {
            char[] newPasswordArray = new char[passwordArray.length + 1];
            newPasswordArray[0] = ALPHABET.charAt(0);
            System.arraycopy(passwordArray, 0, newPasswordArray, 1, passwordArray.length);
            passwordArray = newPasswordArray;
        }

        return new String(passwordArray);
    }
}
