package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksTest {

    private final Animal CAT =
        new Animal("Киска", Animal.Type.CAT, Animal.Sex.F, 6, 60, 5, false);
    private final Animal DOG =
        new Animal("Джек", Animal.Type.DOG, Animal.Sex.M, 4, 100, 10, true);
    private final Animal BIRD =
        new Animal("Коко", Animal.Type.BIRD, Animal.Sex.M, 1, 20, 1, false);
    private final Animal FISH =
        new Animal("Рюря", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
    private final Animal SPIDER =
        new Animal("Пича", Animal.Type.SPIDER, Animal.Sex.M, 2, 50, 2, true);
    private final Animal CAT1 =
        new Animal("Мурмурка", Animal.Type.CAT, Animal.Sex.M, 3, 30, 3, true);
    private final Animal DOG1 =
        new Animal("Кусака", Animal.Type.DOG, Animal.Sex.F, 8, 120, 13, true);

    Tasks t;

    @BeforeEach
    void setAnimals() {
        t = new Tasks(List.of(CAT, DOG, BIRD, FISH, SPIDER, CAT1, DOG1));
    }

    @Test
    @DisplayName("Тест 1 задания")
    void Task1Test() {
        List<Animal> expectedResult = new ArrayList<>(List.of(FISH, BIRD, CAT1, SPIDER, CAT, DOG, DOG1));
        List<Animal> res = t.Task1();
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 2 задания")
    void Task2Test() {
        List<Animal> expectedResult = new ArrayList<>(List.of(DOG1, DOG, CAT, CAT1));
        List<Animal> res = t.Task2(4);
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 3 задания")
    void Task3Test() {
        Map<Animal.Type, Integer> res = t.Task3();
        Map<Animal.Type, Integer> expectedResult = new HashMap<>(Map.of(
            Animal.Type.CAT, 2,
            Animal.Type.SPIDER, 1,
            Animal.Type.BIRD, 1,
            Animal.Type.FISH, 1,
            Animal.Type.DOG, 2));
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 4 задания")
    void Task4Test() {
        Animal res = t.Task4();
        assertEquals(CAT1, res);
    }

    @Test
    @DisplayName("Тест 5 задания")
    void Task5Test() {
        Animal.Sex res = t.Task5();
        assertEquals(Animal.Sex.M, res);
    }

    @Test
    @DisplayName("Тест 6 задания")
    void Task6Test() {
        Map<Animal.Type, Animal> res = t.Task6();
        Map<Animal.Type, Animal> expectedResult = new HashMap<>(Map.of(
            Animal.Type.CAT, CAT,
            Animal.Type.SPIDER, SPIDER,
            Animal.Type.BIRD, BIRD,
            Animal.Type.FISH, FISH,
            Animal.Type.DOG, DOG1));
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 7 задания")
    void Task7Test() {
        Animal res = t.Task7(3);
        assertEquals(DOG, res);
    }

    @Test
    @DisplayName("Тест 8 задания")
    void Task8Test() {
        Optional<Animal> res = t.Task8(55);
        assertEquals(CAT1, res.get());
    }

    @Test
    @DisplayName("Тест 9 задания")
    void Task9Test() {
        int res = t.Task9();
        assertEquals(26, res);
    }

    @Test
    @DisplayName("Тест 10 задания")
    void Task10Test() {
        List<Animal> res = t.Task10();
        List<Animal> expectedResult = new ArrayList<>(List.of(CAT, BIRD, FISH, SPIDER, CAT1, DOG1));
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 11 задания")
    void Task11Test() {
        List<Animal> res = t.Task11();
        List<Animal> expectedResult = new ArrayList<>(List.of(DOG1));
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 12 задания")
    void Task12Test() {
        Long res = t.Task12();
        assertEquals(0, res);
    }

    @Test
    @DisplayName("Тест 13 задания")
    void Task13Test() {
        List<Animal> res = t.Task13();
        List<Animal> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 14 задания")
    void Task14Test() {
        boolean res = t.Task14(110);
        assertTrue(res);
    }

    @Test
    @DisplayName("Тест 15 задания")
    void Task15Test() {
        Map<Animal.Type, Integer> res = t.Task15(2, 7);
        Map<Animal.Type, Integer> expectedResult = new HashMap<>(Map.of(
            Animal.Type.CAT, 8,
            Animal.Type.SPIDER, 2,
            Animal.Type.BIRD, 0,
            Animal.Type.FISH, 0,
            Animal.Type.DOG, 10));
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 16 задания")
    void Task16Test() {
        List<Animal> res = t.Task16();
        List<Animal> expectedResult = new ArrayList<>(List.of(CAT1, CAT, DOG, DOG1, BIRD, FISH, SPIDER));
        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 17 задания")
    void Task17Test() {
        boolean res = t.Task17();
        assertFalse(res);
    }

    @Test
    @DisplayName("Тест 18 задания")
    void Task18Test() {
        List<List<Animal>> lists = new ArrayList<>(List.of(
            new ArrayList<>(List.of(CAT1, DOG1, FISH)),
            new ArrayList<>(List.of(CAT, DOG))
        ));

        Animal res = t.Task18(lists);
        assertEquals(FISH, res);
    }

    @Test
    @DisplayName("Тест 19 задания")
    void Task19Test() {
        Animal CAT2 =
            new Animal("К", Animal.Type.CAT, Animal.Sex.F, -1, -60, -7, false);
        Tasks t1 = new Tasks(List.of(CAT, DOG, CAT2));

        Map<String, Set<ValidationError>> res = t1.Task19();
        Map<String, Set<ValidationError>> expectedResult = new HashMap<>(Map.of(
            CAT2.name(), Set.of(ValidationError.NAME_ERROR,
                ValidationError.AGE_ERROR,
                ValidationError.HEIGHT_ERROR,
                ValidationError.WEIGHT_ERROR)));

        assertEquals(expectedResult, res);
    }

    @Test
    @DisplayName("Тест 20 задания")
    void Task20Test() {
        Animal CAT2 =
            new Animal("К", Animal.Type.CAT, Animal.Sex.F, -1, -60, -7, false);
        Tasks t1 = new Tasks(List.of(CAT, DOG, CAT2));

        Map<String, String> res = t1.Task20();
        Map<String, String> expectedResult = new HashMap<>(Map.of(
            CAT2.name(),
            ValidationError.NAME_ERROR.getMessage() + ", " +
            ValidationError.WEIGHT_ERROR.getMessage() +  ", " +
            ValidationError.AGE_ERROR.getMessage() + ", " +
                ValidationError.HEIGHT_ERROR.getMessage()));

        assertEquals(expectedResult, res);
    }
}
