package uz.najottalim.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotBlank(message = "name cannot be null")
    private String name;
    @Min(value = 1, message = "tier cannot be less than 1")
    @Max(value = 4, message = "tier cannot be more than 4")
    private Integer tier;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderDTO> orders;

    public CustomerDTO(Long id, String name, Integer tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }
}
