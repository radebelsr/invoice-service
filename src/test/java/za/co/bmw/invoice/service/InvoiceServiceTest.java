package za.co.bmw.invoice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.bmw.invoice.InvoiceTestHelper;
import za.co.bmw.invoice.domain.Invoice;
import za.co.bmw.invoice.dto.InvoiceDTO;
import za.co.bmw.invoice.repository.InvoiceRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class InvoiceServiceTest {
    @Configuration
    static class InvoiceServiceTestConfiguration{
        @Bean
        public InvoiceService invoiceService(){
            return new InvoiceService();
        }
    }

    @Autowired
    private InvoiceService invoiceService;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @Test
    public void shouldAllGetInvoicesAsDto() throws IOException {
        Mockito.when(invoiceRepository.findAll()).thenReturn(new ArrayList<Invoice>(){{add(InvoiceTestHelper.getInvoice());}});
        List<InvoiceDTO> invoiceDTOList = invoiceService.getInvoices();

        assertNotNull(invoiceDTOList);
        assertEquals(1,invoiceDTOList.size());

        verify(invoiceRepository,times(1)).findAll();

    }

    @Test
    public void shouldGetAnInvoice() {
        Mockito.when(invoiceRepository.findOne(anyLong())).thenReturn(InvoiceTestHelper.getInvoice());
        InvoiceDTO invoiceDTO = invoiceService.getInvoice(1L);

        assertNotNull(invoiceDTO);
        verify(invoiceRepository,times(1)).findOne(anyLong());
    }

    @Test
    public void shouldSaveAnInvoice() throws IOException {
        Mockito.when(invoiceRepository.save(any(Invoice.class))).thenReturn(InvoiceTestHelper.getInvoice());
        InvoiceDTO invoiceDTO = invoiceService.save(InvoiceTestHelper.getObject("invoice.json", InvoiceDTO.class));


        assertNotNull(invoiceDTO);
        verify(invoiceRepository,times(1)).save(any(Invoice.class));
    }


}
