package uz.najottalim.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.najottalim.customer.dto.OrderDTO;
import uz.najottalim.customer.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll(){
        return orderService.getAll();
    }

    @PostMapping("register/customerId/{customerId}")
    public ResponseEntity<OrderDTO> addOrder(@PathVariable Long customerId, @RequestBody OrderDTO orderDTO){
        return orderService.addOrder(customerId, orderDTO);
    }

}
