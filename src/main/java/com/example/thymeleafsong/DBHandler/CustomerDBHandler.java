package com.example.thymeleafsong.DBHandler;

import com.example.thymeleafsong.BuissnesModels.Customer;
import com.example.thymeleafsong.BuissnesModels.CustomerGenre;
import com.example.thymeleafsong.BuissnesModels.CustomerSpender;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerDBHandler implements CustomerRepository {

    @Override
    public Boolean addNewCustomer(Customer customer) {
        Connection conn = getConn();
        int result = 0;
        try {
            PreparedStatement pS = conn.prepareStatement("INSERT INTO Customer (FirstName,LastName,Country,PostalCode,Phone,Email) VALUES (?,?,?,?,?,?)");
            setStatement(pS, customer);

            result = pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConn(conn);
        return result == 1;
    }

    @Override
    public Customer updateExistingCustomer(Customer customer) {
        Connection conn = getConn();
        try {
            PreparedStatement pS = conn.prepareStatement("UPDATE Customer SET FirstName=?,LastName=?,Country=?,PostalCode=?,Phone=?,Email=? WHERE CustomerId =?");
            setStatement(pS, customer);
            pS.setInt(7, customer.getCustomerId());
            pS.executeUpdate();
            pS = conn.prepareStatement("SELECT CustomerId  ,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer WHERE CustomerId = ?");
            pS.setInt(1, customer.getCustomerId());
            ResultSet resultSet = pS.executeQuery();
            return new Customer(resultSet.getInt(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6), resultSet.getString(7));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // TODO error hantering
        closeConn(conn);
        return null;
    }

    private void setStatement(PreparedStatement pS, Customer customer) throws SQLException {
        pS.setString(1, customer.getFirstName());
        pS.setString(2, customer.getLastName());
        pS.setString(3, customer.getCountry());
        pS.setString(4, customer.getPostCode());
        pS.setString(5, customer.getPhone());
        pS.setString(6, customer.getEmail());
    }

    @Override
    public List<String> getNrCustomersByCountry() {
        Connection conn = getConn();
        List<String> returnList = new ArrayList<>();
        try {
            PreparedStatement pS = conn.prepareStatement(
                    "SELECT Country, COUNT(*) AS Total FROM Customer GROUP BY Country ORDER BY Total DESC");
            ResultSet resultSet = pS.executeQuery();
            while (resultSet.next()) {
                returnList.add(resultSet.getString("Country") + ": " + resultSet.getString("Total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConn(conn);
        return returnList;
    }

    @Override
    public List<CustomerSpender> getBiggestCustomersSpenders() {
        Connection conn = getConn();
        List<CustomerSpender> customerList = new ArrayList<>();
        try {
            PreparedStatement pS = conn.prepareStatement("SELECT Customer.CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email,SUM(Total) as Total FROM Customer\n" +
                    "    INNER JOIN Invoice I ON Customer.CustomerId = I.CustomerId GROUP BY Customer.CustomerId ORDER BY Total DESC;");
            ResultSet resultSet = pS.executeQuery();
            while (resultSet.next()) {

                customerList.add(new CustomerSpender(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8)
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConn(conn);
        return customerList;

    }

    /**
     * . For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this context
     * means the genre that corresponds to the most tracks from invoices associated to that customer.
     *
     * @return
     */
    @Override
    public CustomerGenre getCustomersFavoriteGenre(int customerId) {
        Connection conn = getConn();
        try {
            PreparedStatement pS = conn.prepareStatement(
                    "SELECT  Customer.CustomerId  ,FirstName,LastName,Country,PostalCode,Phone,Email,G.Name " +
                            "FROM Customer INNER JOIN Invoice I on Customer.CustomerId = I.CustomerId " +
                            "INNER JOIN InvoiceLine IL on I.InvoiceId = IL.InvoiceId " +
                            "INNER JOIN Track T on T.TrackId = IL.TrackId " +
                            "INNER JOIN Genre G on G.GenreId = T.GenreId " +
                            "WHERE Customer.CustomerId = ? GROUP BY G.GenreId ORDER BY COUNT(G.GenreId) DESC LIMIT 1");
            pS.setInt(1, customerId);
            ResultSet resultSet = pS.executeQuery();
            while (resultSet.next()) {
                return new CustomerGenre(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // TODO kanske bättre error hantering
        closeConn(conn);
        return null;
    }

    private Connection getConn() {
        try {
            String dbURL = "jdbc:sqlite:src\\main\\resources\\Chinook_Sqlite.sqlite";
            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    private void closeConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
//            System.err.println("");
        }
    }

}
