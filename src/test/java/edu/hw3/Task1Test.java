package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("Строка, состоящая из латинских букв")
    @CsvSource({"Hello world!, Svool dliow!", "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"})
    void latinSymbolsTest(String inputString, String expectedResult) {
        String res = Task1.atbash(inputString);
        assertEquals(expectedResult, res);
    }

    @ParameterizedTest
    @DisplayName("Строка, состоящая не из латинских букв")
    @CsvSource({"Привет, мир!", "123456"})
    void notLatinSymbolsTest(String inputString) {
        String res = Task1.atbash(inputString);
        assertEquals(inputString, res);
    }

}
