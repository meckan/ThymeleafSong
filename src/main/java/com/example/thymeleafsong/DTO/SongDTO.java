package com.example.thymeleafsong.DTO;

public class SongDTO {

    private String name;
    private String artistName;
    private String albumTitle;
    private String genre;

    public SongDTO() {
    }

    public SongDTO(String name, String artistName, String albumTitle, String genre) {
        this.name = name;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.genre = genre;
    }

    //region gettersSetters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    //endregion
}
