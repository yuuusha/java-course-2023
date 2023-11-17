package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tasks {
    private List<Animal> animals;

    public Tasks(List<Animal> animals) {
        this.animals = new ArrayList<>(animals);
    }

    public List<Animal> task1() {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public List<Animal> task2(int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public Map<Animal.Type, Integer> task3() {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    public Animal task4() {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public Animal.Sex task5() {
        Map<Animal.Sex, Long> sexCount = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        long maleCount = sexCount.get(Animal.Sex.M);
        long femaleCount = sexCount.get(Animal.Sex.F);

        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        }
        return Animal.Sex.F;
    }

    public Map<Animal.Type, Animal> task6() {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingDouble(Animal::weight))
            ));
    }

    public Animal task7(int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    public Optional<Animal> task8(int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public int task9() {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public List<Animal> task10() {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    public List<Animal> task11() {
        final int k = 100;
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > k)
            .toList();
    }

    public Long task12() {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public List<Animal> task13() {
        return animals.stream()
            .filter(animal -> animal.name().trim().split(" ").length > 2)
            .toList();
    }

    public boolean task14(int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public Map<Animal.Type, Integer> task15(int k, int l) {
        Map<Animal.Type, Integer> result = animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));

        for (Animal.Type type : Animal.Type.values()) {
            result.putIfAbsent(type, 0);
        }

        return result;
    }

    public List<Animal> task16() {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public boolean task17() {
        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();
        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();
        return spiderBites > dogBites;
    }

    public Animal task18(List<List<Animal>> listsOfAnimals) {
        return listsOfAnimals.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public Map<String, TreeSet<ValidationError>> task19() {
        return animals.stream()
            .filter(animal ->
                animal.name().length() < 2
                    || animal.age() < 0
                    || animal.height() < 0
                    || animal.weight() < 0)
            .collect(Collectors.toMap(
                Animal::name,
                animal -> {
                    TreeSet<ValidationError> errors = new TreeSet<>();
                    if (animal.name().length() < 2) {
                        errors.add(ValidationError.NAME_ERROR);
                    }
                    if (animal.age() < 0) {
                        errors.add(ValidationError.AGE_ERROR);
                    }
                    if (animal.height() < 0) {
                        errors.add(ValidationError.HEIGHT_ERROR);
                    }
                    if (animal.weight() < 0) {
                        errors.add(ValidationError.WEIGHT_ERROR);
                    }
                    return errors;
                }
            ));
    }

    public Map<String, String> task20() {
        Map<String, TreeSet<ValidationError>> map = task19();

        return map.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(ValidationError::getMessage)
                    .collect(Collectors.joining(", "))
            ));
    }
}
