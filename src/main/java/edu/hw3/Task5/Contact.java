package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Contact implements Comparable<Contact> {

    String name;
    String surname;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        if (!surname.equals(o.surname)) {
            return surname.compareTo(o.surname);
        }
        return name.compareTo(o.name);
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
