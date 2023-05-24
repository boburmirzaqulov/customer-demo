package uz.najottalim.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String category;
    private String name;
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderDTO> orders;
}
