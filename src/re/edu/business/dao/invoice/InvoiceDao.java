package re.edu.business.dao.invoice;

import re.edu.business.dao.AppDao;
import re.edu.business.model.invoice.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDao extends AppDao<Invoice> {
    boolean createInvoice(Invoice invoice);
    List<Invoice> findAllInvoice();
    List<Invoice> searchInvoiveByCustomerId(int  customerId);
    List<Invoice> searchInvoiceCreatedAt(LocalDate createdAt);
}
