package edu.hw4;

public enum ValidationError {
    NAME_ERROR("Имя животного состоит из 1 буквы"),
    AGE_ERROR("Возраст животного отрицательный"),
    HEIGHT_ERROR("Рост животного отрицательный"),
    WEIGHT_ERROR("Вес животного отрицательный");

    final String message;
    ValidationError(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }
}
