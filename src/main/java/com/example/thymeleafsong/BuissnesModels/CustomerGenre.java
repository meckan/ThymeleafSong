package com.example.thymeleafsong.BuissnesModels;

import java.util.List;

public class CustomerGenre extends Customer {

    private List<String> favoriteGenre;


    public CustomerGenre() {
    }

    public CustomerGenre(int customerId, String firstName, String lastName, String country, String postCode, String phone, String email, List<String> favoriteGenre) {
        super(customerId, firstName, lastName, country, postCode, phone, email);
        this.favoriteGenre = favoriteGenre;
    }


    public List<String> getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(List<String> favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCustomerGenre{" +
                "favoriteGenre='" + favoriteGenre + '\'' +
                '}';
    }
}
