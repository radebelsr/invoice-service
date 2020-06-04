package za.co.bmw.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.bmw.invoice.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
