package com.example.thymeleafsong.DBHandler;

import java.util.Collection;

public interface GenreRepository {
    Collection<String> getRandomGenres(int amount);
}
