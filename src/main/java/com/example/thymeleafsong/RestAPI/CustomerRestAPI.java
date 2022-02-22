package com.example.thymeleafsong.RestAPI;

import com.example.thymeleafsong.Controller.CustomerController;
import com.example.thymeleafsong.DBHandler.CustomerDBHandler;
import com.example.thymeleafsong.DTO.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerRestAPI {

    /*
    5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)
    6. Update an existing customer.
    7. Return the number of customers in each country, ordered descending (high to low). i.e. USA: 13, …
    8. Customers who are the highest spenders (total in invoice table is the largest), ordered descending.
    9. For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this context
       means the genre that corresponds to the most tracks from invoices associated to that customer.
     */

    @GetMapping("/")
    public String test() {
        return "Hello";
    }

    @PostMapping("/addCustomer")
    public boolean addCustomer(@RequestBody CustomerDTO dto) {
        CustomerController controller = new CustomerController();
        return controller.addNewCustomer(dto);
    }

    @PutMapping("/updateCustomer")
    private CustomerDTO updateCustomer(@RequestBody CustomerDTO dto) {
        CustomerController controller = new CustomerController();
        return  controller.updateCustomer(dto);
    }

    @GetMapping("/getCustomerByContry")
    public List<String> getCustomersByCountry() {
        CustomerController controller = new CustomerController();
        return controller.getNrCustomersByCountry();
    }

    @GetMapping("/getCustomersFavGenre/{customerId}")
    public CustomerDTO getCstomersFavGenre(@PathVariable int customerId) {
        CustomerController controller = new CustomerController();
        return controller.getCustomerFavGenre(customerId);
    }

    @GetMapping("/getBiggestSpenders")
    public List<CustomerDTO> getBiggestSpenders() {
        CustomerController controller = new CustomerController();
        return controller.getBiggestSpenders();
    }

}