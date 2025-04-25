package re.edu.business.service.invoice;

import re.edu.business.model.InvoiceDetails;
import re.edu.business.model.invoice.Invoice;
import re.edu.business.service.AppService;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService extends AppService<Invoice> {
    boolean createInvoice(Invoice invoice);
    List<Invoice> findAllInvoice();
    List<Invoice> searchInvoiveByCustomerId(int  customerId);
    List<Invoice> searchInvoiceCreatedAt(LocalDate createdAt);
    boolean addInvoiceDetail(InvoiceDetails invoiceDetail);
}
