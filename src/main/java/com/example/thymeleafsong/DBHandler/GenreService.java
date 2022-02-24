package com.example.thymeleafsong.DBHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
/**
 * The service that fetches the random genres from the database.
 */
@Service
public class GenreService implements GenreRepository {

    private final ConnectionManager connectionManager;

    @Autowired
    public GenreService(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    @Override
    public Collection<String> getRandomGenres(int amount){
        Connection conn = connectionManager.getConn();
        Collection<String> genres = new ArrayList();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM Genre ORDER BY RANDOM() LIMIT ?");

            stmt.setString(1,Integer.toString(amount));

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                genres.add(resultSet.getString("name"));
            }

            return genres;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return genres;
        }
    }
}
