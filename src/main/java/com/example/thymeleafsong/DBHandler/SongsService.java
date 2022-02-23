package com.example.thymeleafsong.DBHandler;

import com.example.thymeleafsong.BuissnesModels.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class SongsService {

    private final ConnectionManager connectionManager;

    public SongsService(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

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


    public Collection<Song> getSongByTitle(String title){
        Connection conn = connectionManager.getConn();
        Collection<Song> songCollection = new ArrayList<>();
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT Track.Name, Artist.Name, Album.Title, G.Name\n" +
                    "FROM Track\n" +
                    "         inner join Album A on A.AlbumId = Track.AlbumId\n" +
                    "         inner join Genre G on G.GenreId = Track.GenreId\n" +
                    "         inner join Album Album on Album.AlbumId = Track.AlbumId\n" +
                    "         inner join Artist Artist on Artist.ArtistId = A.ArtistId\n" +
                    "WHERE Track.Name LIKE ?");
            stmt.setString(1,  '%'+ title + '%');

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                songCollection.add(new Song(
                        resultSet.getNString(1),
                        resultSet.getNString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
            return songCollection;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



//    private Connection getConn() {
//        try {
//            return DriverManager.getConnection(dbURL);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
//        return null;
//    }
}
