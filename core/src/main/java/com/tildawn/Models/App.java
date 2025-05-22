package com.tildawn.Models;

import java.util.ArrayList;

public class App {
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;

    public static void addUser(User user) {
        users.add(user);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
