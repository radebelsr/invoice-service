package za.co.bmw.invoice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class LineItemDTO {

    @NotNull
    private Long quantity;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal unitPrice;
    private BigDecimal total;
}
