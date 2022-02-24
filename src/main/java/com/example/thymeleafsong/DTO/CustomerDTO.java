package com.example.thymeleafsong.DTO;
import java.util.List;

/**
 * The Data Transfer Object for a Customer
 */
public class CustomerDTO {

    private int customerId;
    private String firstName;
    private String lastName;
    private String county;
    private String postCode;
    private String phone;
    private String email;

    private List<String> favoriteGenre;

    private double Total;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String firstName, String lastName, String county, String postCode, String phone, String email, List<String> favoriteGenre, double total) {
        this.customerId = customerId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public List<String> getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(List<String> favoriteGenre) {
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
                "customerID=" + customerId +
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
