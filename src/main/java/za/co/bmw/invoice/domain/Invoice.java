package za.co.bmw.invoice.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Invoice extends AbstractAuditable{
    @Column
    private String client;

    @Column
    private Long vatRate;

    @Column
    private Date invoiceDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    protected Set<LineItem> lineItems = new HashSet<>();

    public BigDecimal getVat(){
        return round(getSubTotal().multiply(calculateVatPercentage()));
    }

    public BigDecimal getTotal(){
        return round(getSubTotal().add(getVat()));
    }

    public BigDecimal getSubTotal(){
        return round(lineItems.stream()
                .map(lineItem -> lineItem.getLineItemTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    private BigDecimal calculateVatPercentage(){
        return BigDecimal.valueOf(vatRate).divide(BigDecimal.valueOf(100));
    }
}
