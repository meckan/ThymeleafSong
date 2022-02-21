package com.example.thymeleafsong.DTO;

public class CustomerDTO {

    private int customerID;
    private String firstName;
    private String lastName;
    private String county;
    private String postCode;
    private String phone;
    private String email;

    private String favoriteGenre;

    private double Total;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerID, String firstName, String lastName, String county, String postCode, String phone, String email, String favoriteGenre, double total) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.county = county;
        this.postCode = postCode;
        this.phone = phone;
        this.email = email;
        this.favoriteGenre = favoriteGenre;
        Total = total;
    }

    //region gettersSetter

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    //endregion

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerID=" + customerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", county='" + county + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", favoriteGenre='" + favoriteGenre + '\'' +
                ", Total=" + Total +
                '}';
    }
}
