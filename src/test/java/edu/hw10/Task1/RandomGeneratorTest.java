package edu.hw10.Task1;

import edu.hw10.Task1.classes.Animal;
import edu.hw10.Task1.classes.University;
import edu.hw10.Task1.classes.Mixed;
import edu.hw10.Task1.classes.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomGeneratorTest {
    @Test
    public void animalTest() {
        RandomGenerator generator = new RandomGenerator();
        Animal animal = generator.nextObject(Animal.class, String.class, int.class, double.class);
        Assertions.assertThat(animal.name).isNotNull();
        Assertions.assertThat(animal.age).isGreaterThan(0).isLessThan(100);
        Assertions.assertThat(animal.weight).isGreaterThan(0);
    }

    @Test
    public void universityTest() {
        RandomGenerator generator = new RandomGenerator();
        University company = generator.nextObject(University.class, String.class, String.class, int.class, Person.class);
        Assertions.assertThat(company.title).isNotNull();
        Assertions.assertThat(company.principal).isNotNull();
        Assertions.assertThat(company.studentCount).isGreaterThan(0);
        Assertions.assertThat(company.student).isNotNull();
        Assertions.assertThat(company.student.name()).isNotNull();
        Assertions.assertThat(company.student.age()).isNotNull();
    }

    @Test
    public void mixedTest() {
        RandomGenerator generator = new RandomGenerator();
        Mixed mixed = generator.nextObject(Mixed.class);
        Assertions.assertThat(mixed.value()).isNotNull();
        Assertions.assertThat(mixed.value2()).isNotNull();
        Assertions.assertThat(mixed.value3()).isNotNull();
        Assertions.assertThat(mixed.value4()).isNotNull();
        Assertions.assertThat(mixed.value5()).isNotNull();
        Assertions.assertThat(mixed.value6()).isNotNull();
        Assertions.assertThat(mixed.value7()).isNotNull();
        Assertions.assertThat(mixed.value8()).isNotNull();
    }

}
