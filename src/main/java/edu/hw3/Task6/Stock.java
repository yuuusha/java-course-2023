package edu.hw3.Task6;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {

    String name;
    int price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return price == stock.price && Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override public String toString() {
        return name + " " + price;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return Integer.compare(o.price, this.price);
    }
}
