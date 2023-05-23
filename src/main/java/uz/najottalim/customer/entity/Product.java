package uz.najottalim.customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "category_name", length = 150)
    private String category;
    private String name;
    @Column(columnDefinition = "Number(15,0) default 0")
    private Double price;
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
