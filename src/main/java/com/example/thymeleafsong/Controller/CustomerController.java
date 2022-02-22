package com.example.thymeleafsong.Controller;


import com.example.thymeleafsong.BuissnesModels.Customer;
import com.example.thymeleafsong.BuissnesModels.CustomerGenre;
import com.example.thymeleafsong.BuissnesModels.CustomerSpender;
import com.example.thymeleafsong.DBHandler.CustomerDBHandler;
import com.example.thymeleafsong.DTO.CustomerDTO;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {



    public boolean addNewCustomer(CustomerDTO dto){
        CustomerDBHandler dbHandler = new CustomerDBHandler();
        return dbHandler.addNewCustomer(convertToCustomer(dto));
    }

    public CustomerDTO updateCustomer(CustomerDTO dto){
        CustomerDBHandler dbHandler = new CustomerDBHandler();
        return convertToDTO(dbHandler.updateExistingCustomer(convertToCustomer(dto)));
    }

    public List<String> getNrCustomersByCountry(){
        CustomerDBHandler dbHandler = new CustomerDBHandler();
        return dbHandler.getNrCustomersByCountry();
    }

    public CustomerDTO getCustomerFavGenre(int customerId) {
        CustomerDBHandler dbHandler = new CustomerDBHandler();
        return convertToDTO(dbHandler.getCustomersFavoriteGenre(customerId));
    }

    public List<CustomerDTO> getBiggestSpenders(){
        CustomerDBHandler dbHandler = new CustomerDBHandler();
        List<CustomerSpender> customerSpenderList = dbHandler.getBiggestCustomersSpenders();
        List<CustomerDTO> dtoList = new ArrayList<>();
        for (CustomerSpender spender: customerSpenderList) {
            dtoList.add(convertToDTO(spender));
        }
        return dtoList;
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setCounty(customer.getCounty());
        dto.setPostCode(customer.getPostCode());
        dto.setEmail(customer.getEmail());

        if (customer instanceof CustomerGenre)
            dto.setFavoriteGenre(((CustomerGenre) customer).getFavoriteGenre());
        if(customer instanceof CustomerSpender)
            dto.setTotal(((CustomerSpender) customer).getTotal());

        return dto;
    }

    private Customer convertToCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        customer.setCustomerId(dto.getCustomerId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setCounty(dto.getCounty());
        customer.setPostCode(dto.getPostCode());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        return customer;
    }

}
