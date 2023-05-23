package uz.najottalim.customer.mapping;

import uz.najottalim.customer.dto.OrderDTO;
import uz.najottalim.customer.entity.Order;

public class OrderMapping {
    public static Order toEntity(OrderDTO orderDTO){
        return null;
    }

    public static OrderDTO toDto(Order order){
        return null;
    }

    public static OrderDTO toDtoForCustomer(Order order){
        if (order == null) return null;
        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getStatus()
        );
    }
}
