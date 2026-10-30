package com.cinepass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private DatabaseInitializer() {
    }

    public static void initialize() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            createUsersTable(conn);
            createActivityLogsTable(conn);
            seedDefaultAdmin(conn);
        } catch (SQLException e) {
            System.out.println("Database initialization failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void createUsersTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL UNIQUE, "
                + "password TEXT NOT NULL, "
                + "roles TEXT NOT NULL, "
                + "status TEXT NOT NULL DEFAULT 'PENDING')";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void createActivityLogsTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS activity_logs ("
                + "log_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id INTEGER NOT NULL, "
                + "action TEXT NOT NULL, "
                + "module TEXT NOT NULL, "
                + "description TEXT, "
                + "log_timestamp TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                + "FOREIGN KEY (user_id) REFERENCES users(id))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void seedDefaultAdmin(Connection conn) throws SQLException {
        String check = "SELECT COUNT(*) FROM users WHERE roles = ?";
        try (PreparedStatement ps = conn.prepareStatement(check)) {
            ps.setString(1, "ADMIN");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return;
                }
            }
        }
        String insert = "INSERT INTO users (username, password, roles, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setString(1, "admin");
            ps.setString(2, "admin123");
            ps.setString(3, "ADMIN");
            ps.setString(4, "APPROVED");
            ps.executeUpdate();
        }
    }
}
