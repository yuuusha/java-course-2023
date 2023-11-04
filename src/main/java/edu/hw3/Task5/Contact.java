package edu.hw3.Task5;

import java.util.Objects;

public final class Contact {

    String name;
    String surname;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String surname() {
        return surname;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(surname, contact.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
