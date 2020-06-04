package za.co.bmw.invoice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.bmw.invoice.domain.Invoice;
import za.co.bmw.invoice.dto.InvoiceDTO;
import za.co.bmw.invoice.repository.InvoiceRepository;
import za.co.bmw.invoice.service.InvoiceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @ApiOperation(value="Performes health checks")
    @GetMapping(path = "/ping", produces = "text/html")
    public String ping(){
        return "Pong";
    }

    @ApiOperation(value="Adds a new invoice")
    @PostMapping(produces = "application/json")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoice){
        InvoiceDTO result = invoiceService.save(invoice);
        return result;
    }

    @ApiOperation(value="Returns all invoices")
    @GetMapping(produces = "application/json")
    public List<InvoiceDTO> viewAllInvoices(){
        return invoiceService.getInvoices();
    }

    @ApiOperation(value="Returns an invoice as per specified invoiceId")
    @GetMapping(path="/{invoiceId}", produces = "application/json")
    public InvoiceDTO viewInvoice(@PathVariable("invoiceId") Long invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }
}