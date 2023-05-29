package uz.najottalim.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.customer.dto.ErrorDTO;
import uz.najottalim.customer.dto.OrderDTO;
import uz.najottalim.customer.entity.Customer;
import uz.najottalim.customer.entity.Order;
import uz.najottalim.customer.exception.NoResourceFoundException;
import uz.najottalim.customer.mapping.OrderMapping;
import uz.najottalim.customer.repository.CustomerRepository;
import uz.najottalim.customer.repository.OrderRepository;
import uz.najottalim.customer.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<OrderDTO> getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) throw new NoResourceFoundException("order not found by id");
        return ResponseEntity.ok(
                OrderMapping.toDto(optionalOrder.get())
        );
    }

    @Override
    public ResponseEntity<OrderDTO> addOrder(Long customerId, OrderDTO orderDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty() || orderDTO == null) {
            throw new NoResourceFoundException("customer not found by id or order empty");
        }
        Order order = OrderMapping.toEntity(orderDTO);
        order.setCustomer(optionalCustomer.get());
        try {
            order = orderRepository.save(order);
            return ResponseEntity.ok(
                    OrderMapping.toDto(order)
            );
        }catch (Exception e){
            throw new NoResourceFoundException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getAll() {
        List<Order> all = orderRepository.findAll();
        return ResponseEntity.ok(
                all.stream()
                        .map(OrderMapping::toDtoForAll)
                        .collect(Collectors.toList())
        );
    }
}
