package uz.najottalim.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.customer.dto.ErrorDTO;
import uz.najottalim.customer.dto.OrderDTO;
import uz.najottalim.customer.entity.Customer;
import uz.najottalim.customer.entity.Order;
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
        return optionalOrder.map(order -> ResponseEntity.ok(
                OrderMapping.toDto(order)
        )).orElseGet(() -> ResponseEntity.status(404)
                .body(
                        null
                ));
    }

    @Override
    public ResponseEntity<OrderDTO> addOrder(Long customerId, OrderDTO orderDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty() || orderDTO == null) {
            return ResponseEntity.status(404)
                    .body(
                            null
                    );
        }
        Order order = OrderMapping.toEntity(orderDTO);
        order.setCustomer(optionalCustomer.get());
        order.setId(null);
        order = orderRepository.save(order);
        return ResponseEntity.ok(
                OrderMapping.toDto(order)
        );
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getAll() {
        List<Order> all = orderRepository.findAll();
        if (all.isEmpty()){
            return ResponseEntity.status(404)
                    .body(
                            null
                    );
        }
        return ResponseEntity.ok(
                all.stream()
                        .map(OrderMapping::toDto)
                        .collect(Collectors.toList())
        );
    }
}
