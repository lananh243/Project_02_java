package re.edu.business.service.invoice;

import re.edu.business.dao.invoice.InvoiceDao;
import re.edu.business.dao.invoice.InvoiceDaoImp;
import re.edu.business.model.InvoiceDetails;
import re.edu.business.model.invoice.Invoice;

import java.time.LocalDate;
import java.util.List;

public class InvoiceServiceImp implements InvoiceService {
    private final InvoiceDao invoiceDao;

    public InvoiceServiceImp() {
        invoiceDao = new InvoiceDaoImp();
    }

    @Override
    public boolean createInvoice(Invoice invoice) {
        return invoiceDao.createInvoice(invoice);
    }

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceDao.findAllInvoice();
    }

    @Override
    public List<Invoice> searchInvoiveByCustomerId(int customerId) {
        return invoiceDao.searchInvoiveByCustomerId(customerId);
    }

    @Override
    public List<Invoice> searchInvoiceCreatedAt(LocalDate createdAt) {
        return invoiceDao.searchInvoiceCreatedAt(createdAt);
    }

    @Override
    public boolean addInvoiceDetail(InvoiceDetails invoiceDetail) {
        return false;
    }

}
