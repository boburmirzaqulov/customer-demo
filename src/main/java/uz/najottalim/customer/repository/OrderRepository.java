package uz.najottalim.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najottalim.customer.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
