package com.example.thymeleafsong.DBHandler;

import java.util.Collection;

public interface ArtistRepository {
    Collection<String> getRandomArtists(int amount);
}
