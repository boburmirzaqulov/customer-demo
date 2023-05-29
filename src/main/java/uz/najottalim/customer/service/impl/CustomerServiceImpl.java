package uz.najottalim.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.customer.dto.CustomerDTO;
import uz.najottalim.customer.dto.ErrorDTO;
import uz.najottalim.customer.dto.customer.CustomerMostValuable;
import uz.najottalim.customer.entity.Customer;
import uz.najottalim.customer.exception.NoResourceFoundException;
import uz.najottalim.customer.mapping.CustomerMapping;
import uz.najottalim.customer.repository.CustomerRepository;
import uz.najottalim.customer.service.CustomerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(
                customers.stream()
                        .map(CustomerMapping::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<CustomerDTO> getById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NoResourceFoundException("customer not found by id");
        }
        return ResponseEntity.ok(
                CustomerMapping.toDto(
                        optionalCustomer.get()
                )
        );
    }

    @Override
    public ResponseEntity<CustomerDTO> addCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapping.toEntity(customerDTO);
        try {
            customer = customerRepository.save(customer);
            customerDTO = CustomerMapping.toDto(customer);
            return ResponseEntity.ok(customerDTO);
        }catch (Exception e){
            throw new NoResourceFoundException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new NoResourceFoundException("no deleted. not found customer by id");
        }
        customerRepository.delete(optionalCustomer.get());
        return ResponseEntity.ok("success");
    }

    @Override
    public ResponseEntity<CustomerDTO> updateCustomer(CustomerDTO customerDTO, Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new NoResourceFoundException("no updated. customer not found by id");
        }
        Customer customer = optionalCustomer.get();
        if (customerDTO.getName() != null) customer.setName(customerDTO.getName());
        if (customerDTO.getTier() != null) customer.setTier(customerDTO.getTier());
        customer = customerRepository.save(customer);
        return ResponseEntity.ok(
                CustomerMapping.toDto(customer)
        );
    }

    @Override
    public List<CustomerMostValuable> getMostValuable(Integer month) {
        LocalDate maxDate = customerRepository.getMaxOrderDate().toLocalDateTime().toLocalDate();
        LocalDate date = maxDate.minusDays(maxDate.getDayOfMonth() - 1).minusMonths(month-1);
        System.out.println(date);
        System.out.println(maxDate);
        return customerRepository.getMostValuableWithDate(date, maxDate);
    }
}
