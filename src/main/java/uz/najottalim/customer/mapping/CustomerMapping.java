package uz.najottalim.customer.mapping;

import uz.najottalim.customer.dto.CustomerDTO;
import uz.najottalim.customer.entity.Customer;

import java.util.stream.Collectors;

public class CustomerMapping {
    public static Customer toEntity(CustomerDTO customerDTO){
        if (customerDTO == null) return null;
        return new Customer(
                customerDTO.getName(),
                customerDTO.getTier()
        );
    }

    public static CustomerDTO toDto(Customer customer){
        if (customer == null) return null;
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getTier(),
                customer.getOrders()==null?null:customer.getOrders().stream()
                        .map(OrderMapping::toDtoForCustomer)
                        .collect(Collectors.toList())
        );
    }
}
