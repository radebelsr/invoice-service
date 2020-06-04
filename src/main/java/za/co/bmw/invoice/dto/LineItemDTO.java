package za.co.bmw.invoice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class LineItemDTO {

    @Min(value = 1,message = "Quantity must be greater than 0")
    private Long quantity;

    @NotBlank
    private String description;

    @Min(value = 1,message = "Unit Price must be greater than 0")
    private BigDecimal unitPrice;
    private BigDecimal total;
}
