package edu.hw7;

import edu.hw7.Task3_5.CachePersonMap;
import edu.hw7.Task3_5.Person;
import edu.hw7.Task3_5.PersonDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3_5Test {
    Person p1 = new Person(1, "Джон", "ул. Колотушкина", "89006001020");
    Person p2 = new Person(2, "Майкл", "ул. Пушкина", "89006001021");
    Person p3 = new Person(3, "Сэм", "ул. Ленина", "89006001022");
    Person p4 = new Person(4, "Ли", "ул. Лермонтова", "89006001023");
    Person p5 = new Person(5, "Смит", "ул. Ломоносова", "89006001024");
    PersonDatabase t;

    @BeforeEach
    void setPerson() {
        t = new CachePersonMap();
        t.add(p1);
        t.add(p2);
        t.add(p3);
        t.add(p4);
        t.add(p5);
    }

    @Test
    @DisplayName("Тест на поиск по имени")
    void addTest() {
        List<Person> res = t.findByName("Джон");
        List<Person> expectedResult = List.of(p1);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест на поиск по адресу")
    void addressTest() {
        List<Person> res = t.findByAddress("ул. Пушкина");
        List<Person> expectedResult = List.of(p2);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест на поиск по номеру")
    void phoneTest() {
        List<Person> res = t.findByPhone("89006001022");
        List<Person> expectedResult = List.of(p3);
        assertEquals(expectedResult, res);
    }
}
