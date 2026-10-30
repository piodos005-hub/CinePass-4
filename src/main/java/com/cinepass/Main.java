package com.cinepass;

import com.cinepass.dao.DatabaseInitializer;
import com.cinepass.dao.UserDao;
import com.cinepass.ui.Authentication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();
        Authentication authentication = new Authentication(userDao, scanner);
        authentication.start();
        scanner.close();
    }
}
