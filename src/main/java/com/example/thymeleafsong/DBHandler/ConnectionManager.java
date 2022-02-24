package com.example.thymeleafsong.DBHandler;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A service that opens a connection against the database and closes it.
 */
@Service
public class ConnectionManager {

    /**
     * Open connection to database.
     * @return
     */
    public Connection getConn() {
        try {
            //String dbURL = "jdbc:sqlite:src\\main\\resources\\Chinook_Sqlite.sqlite";
            String dbURL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";

            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /**
     * Closes connection to database.
     * @param conn
     */
    public void closeConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
