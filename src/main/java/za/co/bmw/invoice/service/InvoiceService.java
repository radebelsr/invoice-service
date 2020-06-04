package za.co.bmw.invoice.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.bmw.invoice.domain.Invoice;
import za.co.bmw.invoice.domain.LineItem;
import za.co.bmw.invoice.dto.InvoiceDTO;
import za.co.bmw.invoice.dto.LineItemDTO;
import za.co.bmw.invoice.mapper.InvoiceMapper;
import za.co.bmw.invoice.mapper.LineItemMapper;
import za.co.bmw.invoice.repository.InvoiceRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<InvoiceDTO> getInvoices(){
        return invoiceRepository.findAll()
                .stream()
                .map(InvoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InvoiceDTO getInvoice(Long invoiceId) {
        return InvoiceMapper.toDTO(invoiceRepository.findOne(invoiceId));
    }

    public InvoiceDTO save(InvoiceDTO invoiceDTO) {
        return InvoiceMapper.toDTO(invoiceRepository.save(InvoiceMapper.fromDTO(invoiceDTO)));
    }
}
