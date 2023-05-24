package uz.najottalim.customer.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.customer.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    ResponseEntity<OrderDTO> getOrderById(Long id);

    ResponseEntity<OrderDTO> addOrder(Long customerId, OrderDTO orderDTO);

    ResponseEntity<List<OrderDTO>> getAll();
}
