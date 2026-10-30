package com.cinepass.ui;

import com.cinepass.dao.UserDao;
import com.cinepass.model.User;

import java.util.Scanner;

public class Authentication {
    private final UserDao userDao;
    private final Scanner scanner;

    public Authentication(UserDao userDao, Scanner scanner) {
        this.userDao = userDao;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== CINEPASS =====");
            System.out.println("1. Register Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> {
                    System.out.println("Thank you for using CinePass. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void register() {
        System.out.println();
        System.out.println("----- REGISTER ACCOUNT -----");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }
        if (userDao.usernameExists(username)) {
            System.out.println("Username already taken.");
            return;
        }
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }
        String role = selectRole();
        User newUser = new User(0, username, password, role, "PENDING");
        if (userDao.registerUser(newUser)) {
            System.out.println("Registration successful! Your account status is PENDING.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private String selectRole() {
        while (true) {
            System.out.println("Select role:");
            System.out.println("1. ADMIN");
            System.out.println("2. CASHIER");
            System.out.println("3. CUSTOMER");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    return "ADMIN";
                case "2":
                    return "CASHIER";
                case "3":
                    return "CUSTOMER";
                default:
                    System.out.println("Invalid choice. Enter 1, 2 or 3.");
            }
        }
    }

    private void login() {
        System.out.println();
        System.out.println("----- LOGIN -----");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        User user = userDao.login(username, password);
        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }
        if (!"APPROVED".equalsIgnoreCase(user.status())) {
            System.out.println("Your account is " + user.status() + ". Please wait for an administrator to approve it.");
            return;
        }
        routeByRole(user);
    }

    private void routeByRole(User user) {
        switch (user.roles().toUpperCase()) {
            case "ADMIN" -> new AdminMenu(userDao, scanner, user).start();
            case "CASHIER" -> new CashierMenu(scanner, user).start();
            case "CUSTOMER" -> new CustomerMenu(scanner, user).start();
            default -> System.out.println("Unknown role assigned to this account: " + user.roles());
        }
    }
}
