package com.example.thymeleafsong.DBHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The service that fetches the random artists from the database.
 */
@Service
public class ArtistService implements ArtistRepository{

    private final ConnectionManager connectionManager;

    @Autowired
    public ArtistService(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    @Override
    public Collection<String> getRandomArtists(int amount){
        Connection conn = connectionManager.getConn();
        Collection<String> artists = new ArrayList();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM Artist ORDER BY RANDOM() LIMIT ?");

            stmt.setString(1,Integer.toString(amount));

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                artists.add(resultSet.getString("name"));
            }

            return artists;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return artists;
        }
    }
}
