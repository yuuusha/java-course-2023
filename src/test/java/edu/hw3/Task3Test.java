package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    static Stream<Arguments> freqDictTestData() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"),  new HashMap<>(Map.of("bb", 2, "a", 2))),
            Arguments.of(List.of("this", "and", "that", "and"),  new HashMap<>(Map.of("that", 1, "and", 2, "this", 1))),
            Arguments.of(List.of("код", "код", "код", "bug"),  new HashMap<>(Map.of("код", 3, "bug", 1))),
            Arguments.of(List.of(1, 1, 2, 2),  new HashMap<>(Map.of(1, 2, 2, 2)))
        );
    }

    @ParameterizedTest
    @DisplayName("Частотный список")
    @MethodSource({"freqDictTestData"})
    void latinSymbolsTest(List<?> inputList, HashMap<?, Integer> expectedResult) {
        HashMap<?, Integer> res = Task3.freqDict(inputList);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        List<Integer> inputList = new ArrayList<>();
        HashMap<Integer, Integer> res = Task3.freqDict(inputList);
        HashMap<Integer, Integer> expectedResult = new HashMap<>();
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("В списке разные типы")
    void differentTypesTest() {
        List<Object> inputList = new ArrayList<>(List.of(1, "hello", "hello", 1, 1, '?'));
        HashMap<Object, Integer> res = Task3.freqDict(inputList);
        HashMap<Object, Integer> expectedResult = new HashMap<>(Map.of("hello", 2, 1, 3, '?', 1));
        assertEquals(expectedResult, res);
    }
}
