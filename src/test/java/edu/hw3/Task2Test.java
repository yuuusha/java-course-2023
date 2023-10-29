package edu.hw3;

import edu.hw1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    static Stream<Arguments> canClusterBracketsTestData() {
        return Stream.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        );
    }

    @ParameterizedTest
    @DisplayName("Кластеризуемая последовательность")
    @MethodSource({"canClusterBracketsTestData"})
    void canClusterBracketsTest(String inputString, List<?> expectedResult) {
        List<String> res = Task2.clusterBrackets(inputString);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Пустая последовательность")
    void emptyBracketsTest() {
        List<String> res = Task2.clusterBrackets("");
        List<String> emptyResult = new ArrayList<>();
        assertEquals(emptyResult, res);
    }

    @Test
    @DisplayName("Некластеризуемая последовательность")
    void cantClusterBracketsTest() {
        Throwable thrown = catchThrowable(() -> {
            Task2.clusterBrackets("())(");
        });
        assertThat(thrown).hasMessage("Некорректный ввод: некластеризуемая последовательность");
    }

    @Test
    @DisplayName("В последовательности присутствуют отличные от \"(\" или \")\" символы")
    void notBracketsSymbolsTest() {
        Throwable thrown = catchThrowable(() -> {
            Task2.clusterBrackets("(()a)");
        });
        assertThat(thrown).hasMessage("Некорректный ввод: встретился символ, отличный от \"(\" или \")\"");
    }

}
