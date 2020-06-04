package za.co.bmw.invoice.mapper;

import org.springframework.beans.BeanUtils;
import za.co.bmw.invoice.domain.Invoice;
import za.co.bmw.invoice.dto.InvoiceDTO;

import java.util.HashSet;
import java.util.stream.Collectors;

public class InvoiceMapper {
    public static Invoice fromDTO(InvoiceDTO invoiceDTO){
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDTO, invoice,"lineItems");
        invoice.setInvoiceDate(new java.sql.Date(invoiceDTO.getInvoiceDate().getTime()));

        invoice.setLineItems(
                invoiceDTO.getLineItems()
                        .stream()
                        .map(LineItemMapper::fromDTO)
                        .collect(Collectors.toCollection(HashSet::new))
        );

        return invoice;
    }

    public static InvoiceDTO toDTO(Invoice invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        BeanUtils.copyProperties(invoice,invoiceDTO);
        invoiceDTO.setTotal(invoice.getTotal());
        invoiceDTO.setSubTotal(invoice.getSubTotal());
        invoiceDTO.setVat(invoice.getVat());

        invoiceDTO.setLineItems(invoice.getLineItems()
                .stream()
                .map(LineItemMapper::toDTO)
                .collect(Collectors.toCollection(HashSet::new)));

        return invoiceDTO;
    }
}
