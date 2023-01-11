package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, main.java.Customer> storage;
    private static final Pattern validationEamil = Pattern.compile(
            "^[(a-z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$"
    );
    private static final Pattern validationNumber = Pattern.compile(
            "^[(\\+)]+[0-9]{1,11}"
    );
    public CustomerStorage() {
        storage = new HashMap<>();
    }
    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        String[] components = data.split("\\s+");
        if (components.length != 4){
            throw new IllegalArgumentException();
        }
        if (!validationEamil.matcher(components[2]).matches()) {
            throw new IllegalArgumentException();
        }
        if (!validationNumber.matcher(components[3]).matches()) {
            throw new IllegalArgumentException();
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new main.java.Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public main.java.Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}