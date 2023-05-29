package uz.najottalim.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.najottalim.customer.dto.customer.CustomerMostValuable;
import uz.najottalim.customer.entity.Customer;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select c.ID id, c.NAME name, c.TIER tier, sum(p.PRICE) payed_price\n" +
            "from customer c\n" +
            "         join product_order po on c.id = po.CUSTOMER_ID\n" +
            "         join order_product_relationship opr on opr.ORDER_ID = po.ID\n" +
            "         join product p on p.ID = opr.PRODUCT_ID\n" +
            "WHERE order_date BETWEEN ?1\n" +
            "          AND ?2\n" +
            "group by c.ID, c.NAME, c.TIER\n" +
            "ORDER BY payed_price DESC\n" +
            "FETCH NEXT 5 ROWS ONLY", nativeQuery = true)
    List<CustomerMostValuable> getMostValuableWithDate(LocalDate date, LocalDate maxDate);
    @Query(value = "select max(ORDER_DATE) order_date from product_order", nativeQuery = true)
    Timestamp getMaxOrderDate();
}
