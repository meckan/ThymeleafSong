package com.example.thymeleafsong.DBHandler;

import com.example.thymeleafsong.BuissnesModels.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private String dbURL = "jdbc:sqlite:src\\main\\resources\\Chinook_Sqlite.sqlite";

    public List<Customer> getAllCustomers(){
        Connection conn = getConn();
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                Customer customer = new Customer();
                customer = getDataFromCustomerResultSet(resultSet);

                customers.add(customer);
            }

            return customers;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return customers;
        }
    }

    public Customer getCustomer(int id){
        Connection conn = getConn();
        Customer customer = new Customer();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer WHERE Customer.CustomerID = ?");

            stmt.setString(1, Integer.toString(id));

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                customer = getDataFromCustomerResultSet(resultSet);
            }

            return customer;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return  customer;
        }
    }

    public List<Customer> getCustomers(int limit,int offset){
        Connection conn = getConn();
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer LIMIT ? OFFSET ?");

            stmt.setString(1, Integer.toString(limit));
            stmt.setString(2, Integer.toString(offset));

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                Customer customer = getDataFromCustomerResultSet(resultSet);
                customers.add(customer);
            }

            return customers;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return  customers;
        }
    }

    public Customer getCustomer(String firstName, String lastName){
        Connection conn = getConn();
        Customer customer = new Customer();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode, Phone,Email FROM Customer WHERE FirstName LIKE ? OR  LastName LIKE ?");

            stmt.setString(1, firstName + '%');
            stmt.setString(2, lastName + '%');

            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                customer = getDataFromCustomerResultSet(resultSet);
            }

            return customer;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return  customer;
        }
    }

    private Customer getDataFromCustomerResultSet(ResultSet resultSet) throws SQLException{
        Customer customer = new Customer();
        customer.setCustomerID(resultSet.getInt("CustomerId"));
        customer.setFirstName(resultSet.getString("FirstName"));
        customer.setLastName(resultSet.getString("LastName"));
        customer.setEmail(resultSet.getString("Email"));
        customer.setPhone(resultSet.getString("Phone"));
        customer.setPostCode(resultSet.getString("PostalCode"));
        customer.setCountry(resultSet.getString("Country"));

        return customer;
    }

    private Connection getConn() {
        try {
            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
