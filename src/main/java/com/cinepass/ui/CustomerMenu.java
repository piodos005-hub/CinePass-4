package com.cinepass.ui;

import com.cinepass.model.User;

import java.util.Scanner;

public class CustomerMenu {
    private final Scanner scanner;
    private final User currentUser;

    public CustomerMenu(Scanner scanner, User currentUser) {
        this.scanner = scanner;
        this.currentUser = currentUser;
    }

    public void start() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println();
            System.out.println("===== CUSTOMER MENU =====");
            System.out.println("Logged in as: " + currentUser.username() + " (" + currentUser.roles() + ")");
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
