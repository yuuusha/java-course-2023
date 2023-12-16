package edu.hw10.Task2.interfaces;

import edu.hw10.Task2.cache.Cache;

public interface FibCalculatorWithoutPersist {
    @Cache
    long fib(long number);
}
