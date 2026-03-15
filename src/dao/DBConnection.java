package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database Connection Class
 * Handles JDBC connection to MySQL database
 * Configuration loaded from config.properties
 */
public class DBConnection {
    
    private static final String CONFIG_FILE = "config.properties";
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;
    
    // Load configuration
    static {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            props.load(fis);
            fis.close();
            
            DB_URL = props.getProperty("db.url", "jdbc:mysql://localhost:3306/emp_management");
            DB_USER = props.getProperty("db.user", "root");
            DB_PASSWORD = props.getProperty("db.password", "");
            
        } catch (IOException e) {
            System.err.println("Warning: Could not load " + CONFIG_FILE + ", using defaults.");
            DB_URL = "jdbc:mysql://localhost:3306/emp_management";
            DB_USER = "root";
            DB_PASSWORD = "";
        }
        
        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }
    }
    
    /**
     * Get database connection
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    /**
     * Close database connection
     * @param conn Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
