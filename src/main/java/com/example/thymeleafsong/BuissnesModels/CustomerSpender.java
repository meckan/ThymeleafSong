package com.example.thymeleafsong.BuissnesModels;

public class CustomerSpender extends Customer{

    private double Total;


    public CustomerSpender() {
    }

    public CustomerSpender(double total) {
        Total = total;
    }

    public CustomerSpender(int customerID, String firstName, String lastName, String county, String postCode, String phone, String email, double total) {
        super(customerID, firstName, lastName, county, postCode, phone, email);
        Total = total;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public String toString() {

        String parent = super.toString();

        return parent + "CustomerSpender{" +
                "Total=" + Total +
                '}';
    }
}
