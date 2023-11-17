package edu.hw4;

public enum ValidationError {
    WEIGHT_ERROR("Вес животного отрицательный"),
    AGE_ERROR("Возраст животного отрицательный"),
    NAME_ERROR("Имя животного состоит из 1 буквы"),
    HEIGHT_ERROR("Рост животного отрицательный");

    final String message;
    ValidationError(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }
}
