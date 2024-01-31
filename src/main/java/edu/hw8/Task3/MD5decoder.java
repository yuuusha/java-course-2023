package edu.hw8.Task3;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MD5decoder {
    private static String currPassword = "999";

    private static Map<String, String> encryptedUserData;

    private static Map<String, String> decodedUserData;

    private MD5decoder() {

    }

    public static void loadDataBase(Map<String, String> encryptedUserData) {
        MD5decoder.encryptedUserData = new HashMap<>(encryptedUserData);
        decodedUserData = new HashMap<>();
    }

    @SuppressWarnings("MagicNumber")
    public static String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();

            BigInteger num = new BigInteger(1, digest);
            StringBuilder hash = new StringBuilder(num.toString(16));
            while (hash.length() < 32) {
                hash.insert(0, "0");
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("MagicNumber")
    public static Map<String, String> bruteforcePassword() {
        while (decodedUserData.size() < encryptedUserData.size() && currPassword.length() < 5) {
            currPassword = PasswordGenerator.nextPassword(currPassword);
            String currPasswordMD5 = getMD5Hash(currPassword);
            if (encryptedUserData.containsKey(currPasswordMD5)) {
                decodedUserData.put(encryptedUserData.get(currPasswordMD5), currPassword);
            }
        }

        return decodedUserData;
    }

//    public static void main(String[] args) {
//        MD5decoder.loadDataBase(UserData.userData);
//        Map<String, String> decodedUserData = MD5decoder.bruteforcePassword();
//        for (var x : decodedUserData.entrySet()) {
//            System.out.println(x.getKey() + ": " + x.getValue());
//        }
//    }
}
