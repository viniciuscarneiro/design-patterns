package io.brito.examples.pattern.chainofresponsibility.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Database {

    private Map<String, String> users = new HashMap<>();

    public void register(String username, String password) {
        users.put(username, password);
    }

    public boolean hasUsername(String username) {
        return users.containsKey(username);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
