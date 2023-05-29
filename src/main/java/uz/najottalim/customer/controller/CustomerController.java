package uz.najottalim.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.customer.dto.CustomerDTO;
import uz.najottalim.customer.dto.customer.CustomerMostValuable;
import uz.najottalim.customer.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll(){
        return customerService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return customerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }

    @GetMapping("most-valuable/{month}")
    public List<CustomerMostValuable> getMostValuable(@PathVariable Integer month){
        return customerService.getMostValuable(month);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable Long id){
        return customerService.updateCustomer(customerDTO, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return customerService.deleteById(id);
    }

}
