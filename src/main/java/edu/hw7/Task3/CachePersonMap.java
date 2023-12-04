package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachePersonMap implements PersonDatabase {

    private final Map<Integer, Person> idPerson = new HashMap<>();
    private final Map<String, List<Person>> nameId = new HashMap<>();
    private final Map<String, List<Person>> addressId = new HashMap<>();
    private final Map<String, List<Person>> phoneId = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        idPerson.put(person.id(), person);
        addToIndex(nameId, person.name(), person);
        addToIndex(addressId, person.address(), person);
        addToIndex(phoneId, person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idPerson.remove(id);
        removeFromIndex(nameId, person.name(), person);
        removeFromIndex(addressId, person.address(), person);
        removeFromIndex(phoneId, person.phoneNumber(), person);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameId.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressId.getOrDefault(address, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneId.getOrDefault(phone, new ArrayList<>());
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
