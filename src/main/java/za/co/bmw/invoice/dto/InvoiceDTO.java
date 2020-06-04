package za.co.bmw.invoice.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;

@Data
public class InvoiceDTO {
    private String client;
    private Long vatRate;
    @NotBlank
    private Date invoiceDate;
    private HashSet<LineItemDTO> lineItems;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private BigDecimal total;
}
