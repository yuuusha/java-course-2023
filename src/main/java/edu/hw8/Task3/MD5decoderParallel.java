package edu.hw8.Task3;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MD5decoderParallel {

    private static final String START_PASS = "aaaa";
    private static ConcurrentHashMap<String, String> encryptedUserData;

    private static ConcurrentHashMap<String, String> decodedUserData;

    private MD5decoderParallel() {

    }

    public static void loadDataBase(Map<String, String> encryptedUserData) {
        MD5decoderParallel.encryptedUserData = new ConcurrentHashMap<>(encryptedUserData);
        decodedUserData = new ConcurrentHashMap<>();
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

    public static Map<String, String> bruteforcePassword(String startPass, String endPass) {
        String currPassword = startPass;
        while (decodedUserData.size() < encryptedUserData.size() && !Objects.equals(currPassword, endPass)) {
            currPassword = PasswordGenerator.nextPassword(currPassword);
            String currPasswordMD5 = getMD5Hash(currPassword);
            if (encryptedUserData.containsKey(currPasswordMD5)) {
                decodedUserData.put(encryptedUserData.get(currPasswordMD5), currPassword);
            }
        }

        return decodedUserData;
    }

    public static Map<String, String> bruteforcePasswordMultithread(int countOfThreads) {

        if (countOfThreads < 1 || countOfThreads >= PasswordGenerator.ALPHABET.length()) {
            throw new RuntimeException("Количество потоков должно быть больше 1 и меньше длины алфавита");
        }

        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);
        List<Callable<Void>> works = new ArrayList<>();

        works.add(() -> {
            bruteforcePassword("999", "aaaaa");
            return null;
        });

        int workParts = PasswordGenerator.ALPHABET.length() / countOfThreads;

        for (int i = 0; i < countOfThreads - 1; i++) {
            int finalI = i;
            works.add(() -> {
                String startPass = PasswordGenerator.ALPHABET.charAt(finalI * workParts) + START_PASS;
                String endPass = PasswordGenerator.ALPHABET.charAt(finalI * workParts + workParts) + START_PASS;
                bruteforcePassword(startPass, endPass);
                return null;
            });
        }

        works.add(() -> {
            String startPass = PasswordGenerator.ALPHABET.charAt((countOfThreads - 1) * workParts) + START_PASS;
            String endPass = "aaaaaa";
            bruteforcePassword(startPass, endPass);
            return null;
        });

        try {
            executorService.invokeAll(works);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
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
