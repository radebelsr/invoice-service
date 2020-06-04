package za.co.bmw.invoice.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import za.co.bmw.invoice.InvoiceTestHelper;
import za.co.bmw.invoice.domain.Invoice;
import za.co.bmw.invoice.dto.InvoiceDTO;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnit4.class)
public class InvoiceMapperTest {

    @Test
    public void shouldMapFromInvoiceDTO() throws IOException {
        InvoiceDTO invoiceDTO = InvoiceTestHelper.getObject("invoice.json", InvoiceDTO.class);
        Invoice invoice = InvoiceMapper.fromDTO(invoiceDTO);

        assertNotNull(invoice);
        assertEquals("Test", invoice.getClient());
        assertEquals("2020-06-04", invoice.getInvoiceDate().toString());
        assertEquals(15L, invoice.getVatRate().longValue());
        assertEquals(3, invoice.getLineItems().size());
        assertEquals("Test", invoice.getClient());
    }

    @Test
    public void shouldMapToInvoiceDTO() throws IOException {
        InvoiceDTO invoiceDTO = InvoiceMapper.toDTO(InvoiceTestHelper.getInvoice());

        assertEquals("Test Client", invoiceDTO.getClient());
        assertEquals("2020-06-04", invoiceDTO.getInvoiceDate().toString());
        assertEquals(3L, invoiceDTO.getVatRate().longValue());
        assertEquals(1, invoiceDTO.getLineItems().size());
    }


}
