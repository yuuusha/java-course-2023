package edu.hw8;

import edu.hw8.Task3.MD5decoder;
import edu.hw8.Task3.MD5decoderParallel;
import edu.hw8.Task3.UserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    @DisplayName("Однопоточный перебиратор паролей")
    void singleThreadPassword() {
        TreeMap<String, String> expectedResult = new TreeMap<>(Map.of(
            "a.v.petrov", "aaaa",
            "k.v.krukov", "aaaaa")
        );
        MD5decoder.loadDataBase(UserData.USER_DATA);
        TreeMap<String, String> decodedUserData = new TreeMap<>(MD5decoder.bruteforcePassword());
//        for (var x : decodedUserData.entrySet()) {
//            System.out.println(x.getKey() + ": " + x.getValue());
//        }
        assertEquals(expectedResult, decodedUserData);
    }

    @Test
    @DisplayName("Многопоточный перебиратор паролей")
    void multiThreadPassword() {
        TreeMap<String, String> expectedResult = new TreeMap<>(Map.of(
            "a.v.petrov", "aaaa",
            "k.v.krukov", "aaaaa",
            "s.a.sinuk", "99999")
        );
        MD5decoderParallel.loadDataBase(UserData.USER_DATA);
        Map<String, String> decodedUserData = MD5decoderParallel.bruteforcePasswordMultithread(4);
//        for (var x : decodedUserData.entrySet()) {
//            System.out.println(x.getKey() + ": " + x.getValue());
//        }
        assertEquals(expectedResult, decodedUserData);
    }
}
