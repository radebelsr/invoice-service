package za.co.bmw.invoice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

@Data
public class InvoiceDTO {
    private String client;

    @Min(value = 1,message = "Vat Rate must be greater than 0")
    private Long vatRate;
    @NotNull
    private Date invoiceDate;
    private HashSet<LineItemDTO> lineItems;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal total;
}
