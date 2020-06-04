package za.co.bmw.invoice;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.bmw.invoice.domain.Invoice;
import za.co.bmw.invoice.domain.LineItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class InvoiceTestHelper {
    public static <T> T getObject(String filename, Class<T> classname) throws IOException {
        return new ObjectMapper().readValue(InvoiceTestHelper.class.getClassLoader().getResourceAsStream(filename),classname);
    }

    public static Invoice getInvoice(){
        Calendar cal = Calendar.getInstance();
        Date date = new Date("2020/06/04");
        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(new java.sql.Date(date.getTime()));

        invoice.setClient("Test Client");
        invoice.setVatRate(3L);

        invoice.setLineItems(new HashSet<LineItem>(){{add(getLineItem());}});

        return invoice;
    }

    public static LineItem getLineItem(){
        LineItem lineItem = new LineItem();
        lineItem.setDescription("Test Description 1");
        lineItem.setQuantity(10L);
        lineItem.setUnitPrice(BigDecimal.valueOf(199.99));

        return lineItem;
    }
}
