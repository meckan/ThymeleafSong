package com.example.thymeleafsong.DBHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
@Service
public class SongsService implements SongsRepository{

    private final ConnectionManager connectionManager;

    @Autowired
    public SongsService(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    @Override
    public Collection<String> getRandomSongs(int amount){
        Connection conn = connectionManager.getConn();
        Collection<String> songs = new ArrayList();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM Track ORDER BY RANDOM() LIMIT ?");

            stmt.setString(1,Integer.toString(amount));

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                songs.add(resultSet.getString("name"));
            }

            return songs;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return songs;
        }
    }
}
