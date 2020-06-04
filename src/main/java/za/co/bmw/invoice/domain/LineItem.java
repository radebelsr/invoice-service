package za.co.bmw.invoice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
public class LineItem extends AbstractAuditable{

    @Column
    private Long quantity;

    @Column
    private String description;

    @Column
    private BigDecimal unitPrice;

    public BigDecimal getLineItemTotal(){
        return round(unitPrice.multiply(BigDecimal.valueOf(quantity)));
    }
}
