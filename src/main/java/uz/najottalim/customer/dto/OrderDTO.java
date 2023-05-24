package uz.najottalim.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.najottalim.customer.entity.Customer;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate orderDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deliveryDate;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CustomerDTO customer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProductDTO> products;

    public OrderDTO(Long id, LocalDate orderDate, LocalDate deliveryDate, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public OrderDTO(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, CustomerDTO customer) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
    }
}
