package za.co.bmw.invoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import za.co.bmw.invoice.dto.InvoiceDTO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@AutoConfigureMockMvc
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private HttpHeaders httpHeaders;

    @Before
    public void setup() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add("REQUEST-SYSTEM-ID", "UnitTest");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void ping() throws Exception{
        MvcResult result = mockMvc.perform(get("/invoices/ping"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addInvoice() throws Exception{
        MvcResult result = mockMvc.perform(post("/invoices")
                .content("{\n" +
                        "  \"client\": \"Test\",\n" +
                        "  \"invoiceDate\": \"2020-06-04\",\n" +
                        "  \"lineItems\": [\n" +
                        "    {\n" +
                        "      \"description\": \"Test Description 2\",\n" +
                        "      \"new\": true,\n" +
                        "      \"quantity\": 10,\n" +
                        "      \"unitPrice\": 159.99\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"description\": \"Test Description 2\",\n" +
                        "      \"new\": true,\n" +
                        "      \"quantity\": 3,\n" +
                        "      \"unitPrice\": 159.99\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"description\": \"Test Description 2\",\n" +
                        "      \"new\": true,\n" +
                        "      \"quantity\": 5,\n" +
                        "      \"unitPrice\": 79.99\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"new\": true,\n" +
                        "  \"vatRate\": 15\n" +
                        "}")
        .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        InvoiceDTO invoiceDTO = objectMapper.readValue(result.getResponse().getContentAsString(),InvoiceDTO.class);
        // assert
        assertNotNull(invoiceDTO);
        assertEquals(3, invoiceDTO.getLineItems().size());
    }

    @Test
    public void viewAllInvoices() throws Exception{
        MvcResult result = mockMvc.perform(get("/invoices"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void viewInvoice() throws Exception{
        MvcResult result = mockMvc.perform(get("/invoices")
                .param("invoiceId","1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}