package za.co.bmw.invoice.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import za.co.bmw.invoice.InvoiceTestHelper;
import za.co.bmw.invoice.domain.Invoice;
import za.co.bmw.invoice.domain.LineItem;
import za.co.bmw.invoice.dto.InvoiceDTO;
import za.co.bmw.invoice.dto.LineItemDTO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LineItemMapperTest {
    @Test
    public void shouldMapFromDTO() throws IOException {
        InvoiceDTO invoiceDTO = InvoiceTestHelper.getObject("invoice.json", InvoiceDTO.class);
        LineItem lineItem = LineItemMapper.fromDTO(invoiceDTO.getLineItems().stream().findFirst().get());

        assertNotNull(lineItem);
        assertEquals("Test Description 2", lineItem.getDescription());
        assertEquals(3L, lineItem.getQuantity().longValue());
        assertEquals(BigDecimal.valueOf(159.99), lineItem.getUnitPrice());
    }

    @Test
    public void shouldMapFromEntityToDTO(){
        LineItemDTO lineItemDTO = LineItemMapper.toDTO(getLineItem());
        assertEquals("Test Description 1", lineItemDTO.getDescription());
        assertEquals(10L, lineItemDTO.getQuantity().longValue());
        assertEquals(BigDecimal.valueOf(199.99), lineItemDTO.getUnitPrice());
    }

    private Invoice getInvoice(){
        Calendar cal = Calendar.getInstance();
        Date date = new Date("2020-06-04");
        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(new java.sql.Date(date.getTime()));

        invoice.setClient("Test Client");
        invoice.setVatRate(3L);

        invoice.setLineItems(new HashSet<LineItem>(){{add(getLineItem());}});

        return invoice;
    }

    private LineItem getLineItem(){
        LineItem lineItem = new LineItem();
        lineItem.setDescription("Test Description 1");
        lineItem.setQuantity(10L);
        lineItem.setUnitPrice(BigDecimal.valueOf(199.99));

        return lineItem;
    }
}
