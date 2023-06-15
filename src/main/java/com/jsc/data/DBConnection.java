package com.jsc.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL      = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String DB_USERNAME = "LOGINYOUTUBE";
    private static final String DB_PASSWORD = "123456";
    private static Connection connection;

    private DBConnection(){}

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace(System.out);
            }
        }
        return connection;
    }
}
