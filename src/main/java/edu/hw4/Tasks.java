package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Tasks {
    private List<Animal> animals;

    public Tasks(List<Animal> animals) {
        this.animals = new ArrayList<>(animals);
    }

    public List<Animal> Task1() {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public List<Animal> Task2(int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public Map<Animal.Type, Integer>Task3() {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
    }

    public Animal Task4() {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    public Animal.Sex Task5() {
        Map<Animal.Sex, Long> sexCount = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        long maleCount = sexCount.get(Animal.Sex.M);
        long femaleCount = sexCount.get(Animal.Sex.F);

        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        }
        return Animal.Sex.F;
    }

    public Map<Animal.Type, Animal> Task6() {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal,
                (existing, replacement) -> existing.weight() > replacement.weight() ? existing : replacement
            ));
    }

    public Animal Task7(int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    public Optional<Animal> Task8(int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public int Task9() {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public List<Animal> Task10() {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    public List<Animal> Task11() {
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    public Long Task12() {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public List<Animal> Task13() {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    public boolean Task14(int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public Map<Animal.Type, Integer> Task15(int k, int l) {
        Map<Animal.Type, Integer> result = animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));

        for (Animal.Type type : Animal.Type.values()) {
            result.putIfAbsent(type, 0);
        }

        return result;
    }

    public List<Animal> Task16() {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public boolean Task17() {
        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites())
            .count();
        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG && animal.bites())
            .count();
        return spiderBites > dogBites;
    }

    public Animal Task18(List<List<Animal>> listsOfAnimals) {

        List<Animal> concatList = new ArrayList<>();
        for (var list: listsOfAnimals) {
            concatList.addAll(list);
        }

        return concatList.stream()
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public Map<String, Set<ValidationError>> Task19() {
        return animals.stream()
            .filter(animal ->
                animal.name().length() < 2 ||
                animal.age() < 0 ||
                animal.height() < 0 ||
                animal.weight() < 0)
            .collect(Collectors.toMap(
                Animal::name,
                animal -> {
                    Set<ValidationError> errors = new HashSet<>();
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

    public Map<String, String> Task20() {
        Map<String, Set<ValidationError>> map = Task19();

        return map.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(ValidationError::getMessage)
                    .collect(Collectors.joining(", "))
            ));
    }
}
