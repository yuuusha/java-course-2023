package edu.hw10.Task1.classes;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

public class Animal {
    public final String name;
    public final int age;
    public final double weight;

    public Animal(String name, @Max(100) @Min(0) int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

}
