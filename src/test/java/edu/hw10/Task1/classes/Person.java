package edu.hw10.Task1.classes;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;

public record Person(String name, @Min(10) @Max(90) Integer age) {
}
