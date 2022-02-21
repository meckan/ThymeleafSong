package com.example.thymeleafsong.BuissnesModels;

public class CustomerGenre extends Customer {

    private String favoriteGenre;


    public CustomerGenre(int customerID, String firstName, String lastName, String county, String postCode, String phone, String email, String favoriteGenre) {
        super(customerID, firstName, lastName, county, postCode, phone, email);
        this.favoriteGenre = favoriteGenre;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }


    @Override
    public String toString() {
        return super.toString() + "\nCustomerGenre{" +
                "favoriteGenre='" + favoriteGenre + '\'' +
                '}';
    }
}
