package com.cinepass.ui;

import com.cinepass.dao.UserDao;
import com.cinepass.model.User;

import java.util.Scanner;

public class AdminMenu {
    private final UserDao userDao;
    private final Scanner scanner;
    private final User currentAdmin;

    public AdminMenu(UserDao userDao, Scanner scanner, User currentAdmin) {
        this.userDao = userDao;
        this.scanner = scanner;
        this.currentAdmin = currentAdmin;
    }

    public void start() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println();
            System.out.println("===== ADMIN MENU =====");
            System.out.println("Logged in as: " + currentAdmin.username() + " (" + currentAdmin.roles() + ")");
            System.out.println("1. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                System.out.println("You have been logged out.");
                loggedIn = false;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
