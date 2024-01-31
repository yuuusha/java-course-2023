package edu.hw7.Task3_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachePersonMap implements PersonDatabase {

    private final Map<Integer, Person> idPerson = new HashMap<>();
    private final Map<String, List<Person>> nameId = new HashMap<>();
    private final Map<String, List<Person>> addressId = new HashMap<>();
    private final Map<String, List<Person>> phoneId = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            idPerson.put(person.id(), person);
            addToIndex(nameId, person.name(), person);
            addToIndex(addressId, person.address(), person);
            addToIndex(phoneId, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idPerson.remove(id);
            removeFromIndex(nameId, person.name(), person);
            removeFromIndex(addressId, person.address(), person);
            removeFromIndex(phoneId, person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return nameId.getOrDefault(name, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressId.getOrDefault(address, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneId.getOrDefault(phone, new ArrayList<>());
        } finally {
            lock.readLock().unlock();
        }
    }

    private void addToIndex(Map<String, List<Person>> index, String key, Person person) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private void removeFromIndex(Map<String, List<Person>> id, String key, Person person) {
        List<Person> people = id.get(key);
        if (people != null) {
            people.remove(person);
            if (people.isEmpty()) {
                id.remove(key);
            }
        }
    }
}
