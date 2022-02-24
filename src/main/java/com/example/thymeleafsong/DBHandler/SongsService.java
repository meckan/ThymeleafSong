package com.example.thymeleafsong.DBHandler;

import com.example.thymeleafsong.BuissnesModels.Song;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
/**
 * The service that fetches the random songs from the database.
 * Service also performs a search by song title query against the database.
 */
@Service
public class SongsService implements SongsRepository{

    private final ConnectionManager connectionManager;

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

    /**
     * Search for a given title of a song against the database and returns the song,
     * or a collection of songs that matches the title given.
     * @param title inputted song title to be searched for.
     * @return Collection of songs that matches the inputted search string.
     */
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
                        resultSet.getString(1),
                        resultSet.getString(2),
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

}
