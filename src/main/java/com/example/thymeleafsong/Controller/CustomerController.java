package com.example.thymeleafsong.Controller;


import com.example.thymeleafsong.BuissnesModels.Customer;
import com.example.thymeleafsong.BuissnesModels.CustomerGenre;
import com.example.thymeleafsong.BuissnesModels.CustomerSpender;
import com.example.thymeleafsong.DBHandler.CustomerRepository;
import com.example.thymeleafsong.DBHandler.CustomerService;
import com.example.thymeleafsong.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    
    private final CustomerRepository repository;
    
    public CustomerController(@Autowired CustomerService repository){
        this.repository = repository;
    }


    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer customer : repository.getAllCustomers() ) {
            dtoList.add(convertToDTO(customer));
        }
        return dtoList;
    }

    public CustomerDTO getCustomerById(int id){
        return convertToDTO(repository.getCustomer(id));
    }

    public List<CustomerDTO> getCustomers(int limit, int offset){
        List<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer customer: repository.getCustomers(limit,offset)) {
            dtoList.add(convertToDTO(customer));
        }
        return dtoList;
    }

    public CustomerDTO getCustomerByName(String firstName, String lastName){
        return convertToDTO(repository.getCustomer(firstName,lastName));
    }


    public boolean addNewCustomer(CustomerDTO dto){
        return repository.addNewCustomer(convertToCustomer(dto));
    }

    public CustomerDTO updateCustomer(CustomerDTO dto){
        return convertToDTO(repository.updateExistingCustomer(convertToCustomer(dto)));
    }

    public List<String> getNrCustomersByCountry(){
        return repository.getNrCustomersByCountry();
    }

    public CustomerDTO getCustomerFavGenre(int customerId) {

        System.out.println(repository.getCustomersFavoriteGenre(customerId));

        return convertToDTO(repository.getCustomersFavoriteGenre(customerId));
    }

    public List<CustomerDTO> getBiggestSpenders(){
        List<CustomerSpender> customerSpenderList = repository.getBiggestCustomersSpenders();
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
        dto.setCounty(customer.getCountry());
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
        customer.setCountry(dto.getCounty());
        customer.setPostCode(dto.getPostCode());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        return customer;
    }

    @GetMapping("/home")
    public String getRandomMusicData(Model model){
        CustomerService service = new CustomerService();
        String randomSongs = "";
        for(String song : service.getRandomSongs(5)){
            randomSongs += song + ". ";
        }

        String randomArtists = "";
        for(String artist : service.getRandomArtists(5)){
            randomArtists += artist + ". ";
        }

        String randomGenres = "";
        for(String genres : service.getRandomGenres(5)){
            randomGenres += genres + ". ";
        }

        model.addAttribute("randomSongs",randomSongs);
        model.addAttribute("randomArtists",randomArtists);
        model.addAttribute("randomGenres",randomGenres);
        return "home";
    }

    String song = "";
    @PostMapping("result")
    public String searchSong(@RequestParam("song") String song, Model model){

        this.song = song;
        model.addAttribute("song",song);
        return "result";
    }

    @GetMapping("/result")
    public String resultPage(Model model)
    {
        model.addAttribute("song", song);
        return "result";
    }
}
