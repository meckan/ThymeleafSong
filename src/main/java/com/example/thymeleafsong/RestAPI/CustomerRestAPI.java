package com.example.thymeleafsong.RestAPI;

import com.example.thymeleafsong.BuissnesModels.Customer;
import com.example.thymeleafsong.Controller.CustomerController;
import com.example.thymeleafsong.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final CustomerController controller;

    public CustomerRestAPI(@Autowired CustomerController controller){
        this.controller = controller;
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers(){
        return controller.getAllCustomers();
    }

    @GetMapping("/customer/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable int customerId){
        return controller.getCustomerById(customerId);
    }

    @GetMapping("/customers/{offset}/{limit}")
    public List<CustomerDTO> getCustomersByOffsetLimit(@PathVariable int offset,@PathVariable int limit){
        return controller.getCustomers(limit,offset);
    }

    @GetMapping("/customer/{firstName}/{lastName}")
    public CustomerDTO getCustomerByFirstLastName(@PathVariable String firstName,@PathVariable String lastName){
        return controller.getCustomerByName(firstName,lastName);
    }


    @PostMapping("/addCustomer")
    public boolean addCustomer(@RequestBody CustomerDTO dto) {
        return controller.addNewCustomer(dto);
    }

    @PutMapping("/updateCustomer")
    private CustomerDTO updateCustomer(@RequestBody CustomerDTO dto) {
        return  controller.updateCustomer(dto);
    }

    @GetMapping("/getCustomerByContry")
    public List<String> getCustomersByCountry() {
        return controller.getNrCustomersByCountry();
    }

    @GetMapping("/getCustomersFavGenre/{customerId}")
    public CustomerDTO getCstomersFavGenre(@PathVariable int customerId) {
        return controller.getCustomerFavGenre(customerId);
    }

    @GetMapping("/getBiggestSpenders")
    public List<CustomerDTO> getBiggestSpenders() {
        return controller.getBiggestSpenders();
    }

}
