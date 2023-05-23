package uz.najottalim.customer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Integer tier;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String name, Integer tier) {
        this.name = name;
        this.tier = tier;
    }
}
