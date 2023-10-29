package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Task5Test {

    static Stream<Arguments> notNullTestData() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                "ASC",
                List.of(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke"))
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                "DESC",
                List.of(
                    new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos"))
            )
        );
    }

    static Stream<Arguments> nullTestData() {
        return Stream.of(
            Arguments.of(
                new String[] {},
                "DESC",
                new ArrayList<Contact>()
            ),
            Arguments.of(
                null,
                "DESC",
                new ArrayList<Contact>()
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Ненулевые данные")
    @MethodSource({"notNullTestData"})
    void notNullTest(String[] names, String typeSort, List<Contact> expectedResult) {
        List<Contact> result = Task5.parseContacts(names, typeSort);
        assertIterableEquals(result, expectedResult);
    }

    @ParameterizedTest
    @DisplayName("Нулевые данные")
    @MethodSource({"nullTestData"})
    void nullTest(String[] names, String typeSort, List<Contact> expectedResult) {
        List<Contact> result = Task5.parseContacts(names, typeSort);
        assertIterableEquals(result, expectedResult);
    }

    @Test
    @DisplayName("Ошибка в имени и фамилии")
    void mistakeNameTest() {
        String[] names = new String[]{ "My Name Is" };
        Throwable thrown = catchThrowable(() -> {
            Task5.parseContacts(names, "DESC");
        });
        assertThat(thrown).hasMessage("Неверно записаны имя и фамилия");
    }

    @Test
    @DisplayName("Ошибка в порядке сортировки")
    void mistakeTypeSortTest() {
        String[] names = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        Throwable thrown = catchThrowable(() -> {
            Task5.parseContacts(names, "AAA");
        });
        assertThat(thrown).hasMessage("Неверно указан порядок сортировки");
    }

}
