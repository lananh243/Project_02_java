package re.edu.business.service.invoice_details;

import re.edu.business.model.InvoiceDetails;
import re.edu.business.service.AppService;

import java.util.List;

public interface InvoiceDetailService extends AppService<InvoiceDetails> {
    boolean createInvoiceDetail(InvoiceDetails invoiceDetail);
    List<InvoiceDetails> findInvoiceDetailByInvoiceId(int invoiceId);
}
