package fr.isima.cours.jee.server.controllers.auth;

public class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
