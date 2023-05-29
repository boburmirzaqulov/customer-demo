package uz.najottalim.customer.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.customer.dto.CustomerDTO;
import uz.najottalim.customer.dto.customer.CustomerMostValuable;

import java.util.List;

public interface CustomerService {

    ResponseEntity<List<CustomerDTO>> getAll();

    ResponseEntity<CustomerDTO> getById(Long id);

    ResponseEntity<CustomerDTO> addCustomer(CustomerDTO customerDTO);

    ResponseEntity<?> deleteById(Long id);

    ResponseEntity<CustomerDTO> updateCustomer(CustomerDTO customerDTO, Long id);

    List<CustomerMostValuable> getMostValuable(Integer month);
}
