package com.tildawn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tildawn.Models.User;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:game.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createUsersTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL UNIQUE, " +
            "password TEXT NOT NULL, " +
            "securityQuestionAnswer TEXT NOT NULL, " +
            "score INTEGER DEFAULT 0" +
            ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSql);
            stmt.execute("ALTER TABLE users ADD COLUMN maxAliveTime REAL DEFAULT 0");
        } catch (SQLException e) {
            if (!e.getMessage().contains("duplicate column name")) {
                e.printStackTrace();
            }
        }

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute("ALTER TABLE users ADD COLUMN kills INTEGER DEFAULT 0");
        } catch (SQLException e) {
            if (!e.getMessage().contains("duplicate column name")) {
                e.printStackTrace();
            }
        }
    }

    public static void saveUser(String username, String password, String securityAnswer, int score, float maxAliveTime, int kills) {
        String sql = "INSERT INTO users(username, password, securityQuestionAnswer, score, maxAliveTime, kills) " +
            "VALUES(?, ?, ?, ?, ?, ?) " +
            "ON CONFLICT(username) DO UPDATE SET " +
            "score = excluded.score, " +
            "maxAliveTime = excluded.maxAliveTime, " +
            "kills = excluded.kills;";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, securityAnswer);
            pstmt.setInt(4, score);
            pstmt.setFloat(5, maxAliveTime);
            pstmt.setInt(6, kills);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String securityAnswer = rs.getString("securityQuestionAnswer");
                int score = rs.getInt("score");
                float maxAliveTime = rs.getFloat("maxAliveTime");
                int kills = rs.getInt("kills");

                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Security Answer: " + securityAnswer);
                System.out.println("Score: " + score);
                System.out.println("Max Alive Time: " + maxAliveTime);
                System.out.println("Kills: " + kills);
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String securityAnswer = rs.getString("securityQuestionAnswer");
                int score = rs.getInt("score");
                float maxAliveTime = rs.getFloat("maxAliveTime");
                int kills = rs.getInt("kills");

                User user = new User(username, password, securityAnswer, score, maxAliveTime, kills);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void updateUserScore(String username, int newScore) {
        String sql = "UPDATE users SET score = ? WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newScore);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserPassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUsername(String currentUsername, String newUsername) {
        String sql = "UPDATE users SET username = ? WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setString(2, currentUsername);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Username updated from " + currentUsername + " to " + newUsername);
            } else {
                System.out.println("User not found: " + currentUsername);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserMaxAliveTime(String username, float newTime) {
        String sql = "UPDATE users SET maxAliveTime = ? WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setFloat(1, newTime);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserKills(String username, int newKills) {
        String sql = "UPDATE users SET kills = ? WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newKills);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User '" + username + "' was deleted.");
            } else {
                System.out.println("No user found with username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
