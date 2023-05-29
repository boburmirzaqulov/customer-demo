package uz.najottalim.customer.mapping;

import uz.najottalim.customer.dto.OrderDTO;
import uz.najottalim.customer.entity.Order;

public class OrderMapping {
    public static Order toEntity(OrderDTO orderDTO){
        if (orderDTO == null) return null;
        return new Order(
                null,
                orderDTO.getOrderDate(),
                orderDTO.getDeliveryDate(),
                orderDTO.getStatus(),
                CustomerMapping.toEntity(orderDTO.getCustomer()),
                null
        );
    }

    public static OrderDTO toDto(Order order){
        if (order == null) return null;
        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getStatus(),
                CustomerMapping.toDtoForOrder(order.getCustomer())
        );
    }

    public static OrderDTO toDtoForAll(Order order){
        if (order == null) return null;
        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getStatus(),
                null
        );
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
