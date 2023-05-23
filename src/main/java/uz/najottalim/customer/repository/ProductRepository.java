package uz.najottalim.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najottalim.customer.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
