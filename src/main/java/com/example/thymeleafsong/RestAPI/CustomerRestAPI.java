package com.example.thymeleafsong.RestAPI;

import com.example.thymeleafsong.Controller.CustomerController;
import com.example.thymeleafsong.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerRestAPI {

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

    @PutMapping("/updateCustomer/{customerId}")
    private CustomerDTO updateCustomer(@RequestBody CustomerDTO dto,@PathVariable int customerId) {
        return  controller.updateCustomer(customerId,dto);
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
