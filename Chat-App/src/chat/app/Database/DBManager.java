package chat.app.Database;

import chat.app.Models.Group;
import chat.app.Models.User;
import chat.app.Server.Server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import chat.app.UI.LoginFrame;
import chat.app.UI.ModernChatFrame;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static chat.app.Server.Server.isUserExist;

public class DBManager {
    private static Connection con;

    public static void ConnectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/chatappdb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "rawal117"
            );
            System.out.println("Database connection success");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Hash password using MD5
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean registerUser(String username, String email, String password) {
        try {
            // Check if username already exists
            if (isUserExist(username)) {
                return false;
            }

            // Check if email already exists
            String checkEmail = "SELECT COUNT(*) FROM userlist WHERE email = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkEmail);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return false;
            }

            // Insert new user
            String sql = "INSERT INTO userlist (username, email, password_hash) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, hashPassword(password));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    public static boolean loginUser(String username, String password) {
        try {
            String sql = "SELECT password_hash FROM userlist WHERE username = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String storedHash = result.getString("password_hash");
                if (storedHash.equals(hashPassword(password))) {
                    // Update last login and online status
                    sql = "UPDATE userlist SET last_login = NOW(), is_online = TRUE WHERE username = ?";
                    statement = con.prepareStatement(sql);
                    statement.setString(1, username);
                    statement.executeUpdate();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }

    // ... keep existing methods ...

    private static void ensureConnected() throws SQLException {
        if (con == null || con.isClosed()) {
            ConnectDB();
        }
    }

    public static List<UserDB> GetUserList() {
    }
}