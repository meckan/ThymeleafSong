package com.example.thymeleafsong.DBHandler;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class ConnectionManager {

    public Connection getConn() {
        try {
            String dbURL = "jdbc:sqlite:src\\main\\resources\\Chinook_Sqlite.sqlite";
            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public void closeConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
