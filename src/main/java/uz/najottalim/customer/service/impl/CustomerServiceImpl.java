package uz.najottalim.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.customer.dto.CustomerDTO;
import uz.najottalim.customer.dto.ErrorDTO;
import uz.najottalim.customer.entity.Customer;
import uz.najottalim.customer.mapping.CustomerMapping;
import uz.najottalim.customer.repository.CustomerRepository;
import uz.najottalim.customer.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> getAll() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()){
            return ResponseEntity.status(404)
                    .body(
                            ErrorDTO.builder()
                                    .errors("Not found")
                                    .build()
                    );
        }
        return ResponseEntity.ok(customers);
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(
                    CustomerMapping.toDto(
                            optionalCustomer.get()
                    )
            );
        }
        return ResponseEntity.status(404)
                .body(
                        ErrorDTO.builder()
                                .errors("Not found")
                                .build()
                );
    }

    @Override
    public ResponseEntity<?> addCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapping.toEntity(customerDTO);
        try {
            customer = customerRepository.save(customer);
            customerDTO = CustomerMapping.toDto(customer);
            return ResponseEntity.ok(customerDTO);
        }catch (Exception e){
            return ResponseEntity.status(404)
                    .body(ErrorDTO.builder().errors(e.getMessage()).build());
        }
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            customerRepository.delete(optionalCustomer.get());
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(404)
                .body(
                        ErrorDTO.builder()
                                .errors("Not found")
                                .build()
                );
    }

    @Override
    public ResponseEntity<?> updateCustomer(CustomerDTO customerDTO, Long id) {
        return null;
    }
}
