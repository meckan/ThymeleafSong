package com.example.thymeleafsong.DBHandler;

import java.util.Collection;

public interface SongsRepository {
    Collection<String> getRandomSongs(int amount);
}
