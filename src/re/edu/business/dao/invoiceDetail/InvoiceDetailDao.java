package re.edu.business.dao.invoiceDetail;

import re.edu.business.dao.AppDao;
import re.edu.business.model.InvoiceDetails;

import java.util.List;

public interface InvoiceDetailDao extends AppDao<InvoiceDetails> {
    boolean createInvoiceDetail(InvoiceDetails invoiceDetail);
    List<InvoiceDetails> findInvoiceDetailByInvoiceId(int invoiceId);
}
