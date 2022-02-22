package com.example.thymeleafsong.DBHandler;

import com.example.thymeleafsong.BuissnesModels.Customer;
import com.example.thymeleafsong.BuissnesModels.CustomerGenre;
import com.example.thymeleafsong.BuissnesModels.CustomerSpender;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Repository
public interface CustomerRepository {
    Boolean addNewCustomer(Customer customer);

    Customer updateExistingCustomer(Customer customer);

    List<String> getNrCustomersByCountry();

    List<CustomerSpender> getBiggestCustomersSpenders();

    CustomerGenre getCustomersFavoriteGenre(int customerId);
}