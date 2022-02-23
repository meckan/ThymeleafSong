package com.example.thymeleafsong.DBHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class SongsService {
    public Collection<String> getRandomSongs(int amount){
        Connection conn = getConn();
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

    public Collection<String> getRandomGenres(int amount){
        Connection conn = getConn();
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

    public Collection<String> getRandomArtists(int amount){
        Connection conn = getConn();
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

    private Connection getConn() {
        try {
            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
