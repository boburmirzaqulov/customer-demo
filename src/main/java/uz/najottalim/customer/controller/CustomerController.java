package uz.najottalim.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.customer.dto.CustomerDTO;
import uz.najottalim.customer.service.CustomerService;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return customerService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return customerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return customerService.addCustomer(customerDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable Long id){
        return customerService.updateCustomer(customerDTO, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return customerService.deleteById(id);
    }

}
