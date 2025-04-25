package re.edu.business.service.invoice_details;

import re.edu.business.dao.invoiceDetail.InvoiceDetailDao;
import re.edu.business.dao.invoiceDetail.InvoiceDetailDaoImp;
import re.edu.business.model.InvoiceDetails;

import java.util.List;

public class InvoiceDetailServiceImp implements InvoiceDetailService {
    private final InvoiceDetailDao invoiceDetailDao;

    public InvoiceDetailServiceImp() {
        invoiceDetailDao = new InvoiceDetailDaoImp();
    }

    @Override
    public boolean createInvoiceDetail(InvoiceDetails invoiceDetail) {
        return invoiceDetailDao.createInvoiceDetail(invoiceDetail);
    }

    @Override
    public List<InvoiceDetails> findInvoiceDetailByInvoiceId(int invoiceId) {
        return invoiceDetailDao.findInvoiceDetailByInvoiceId(invoiceId);
    }
}
