package re.edu.business.dao.invoiceDetail;

import re.edu.business.config.ConnectionDB;
import re.edu.business.model.InvoiceDetails;
import re.edu.business.model.Product;
import re.edu.business.model.invoice.Invoice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailDaoImp implements InvoiceDetailDao {

    @Override
    public boolean createInvoiceDetail(InvoiceDetails invoiceDetail) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call create_invoice_details(?,?,?,?)}");
            callSt.setInt(1, invoiceDetail.getInvoice_id());
            callSt.setInt(2, invoiceDetail.getProduct().getPro_id());
            callSt.setDouble(3, invoiceDetail.getQuantity());
            callSt.setDouble(4, invoiceDetail.getUnit_price());
            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thêm mới chi tiết hóa đơn" + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Có lỗi khi thực hiện rollback: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<InvoiceDetails> findInvoiceDetailByInvoiceId(int invoiceId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<InvoiceDetails> invoiceDetails = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_invoice_details_by_invoice_id(?)}");
            callSt.setInt(1, invoiceId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                InvoiceDetails detail = new InvoiceDetails();
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnit_price(rs.getDouble("unit_price"));

                Product product = new Product();
                product.setPro_id(rs.getInt("pro_id"));
                detail.setProduct(product);

                detail.setInvoice_id(rs.getInt("invoice_id"));

                invoiceDetails.add(detail);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy ra chi tiết hóa đơn" + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Có lỗi khi thực hiện rollback: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return invoiceDetails;
    }
}
