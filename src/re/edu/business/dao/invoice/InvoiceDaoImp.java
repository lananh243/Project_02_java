package re.edu.business.dao.invoice;

import re.edu.business.config.ConnectionDB;
import re.edu.business.model.customer.Customer;
import re.edu.business.model.invoice.Invoice;
import re.edu.business.model.invoice.Status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImp implements InvoiceDao {
    @Override
    public boolean createInvoice(Invoice invoice) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call create_invoice(?,?,?)}");
            callSt.setInt(1, invoice.getCustomer().getCus_id());
            callSt.setDouble(2, invoice.getTotal_amount());
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int generatedId = callSt.getInt(3);
            invoice.setInvoice_id(generatedId);
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thêm mới hóa đơn" + e.getMessage());
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
    public List<Invoice> findAllInvoice() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoices = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_all_invoice()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoice_id(rs.getInt("invoice_id"));
                String fullDateTime = rs.getString("created_at");
                String onlyDate = fullDateTime.split(" ")[0];
                invoice.setCreated_at(LocalDate.parse(onlyDate));
                invoice.setTotal_amount(rs.getDouble("total_amount"));
                invoice.setInvoice_status(rs.getString("status"));

                Customer customer = new Customer();
                customer.setCus_id(rs.getInt("cus_id"));
                invoice.setCustomer(customer);

                invoices.add(invoice);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy ra tất cả hóa đơn" + e.getMessage());
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
        return invoices;
    }

    @Override
    public List<Invoice> searchInvoiveByCustomerId(int customerId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoices = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_invoive_by_customerId(?)}");
            callSt.setInt(1, customerId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoice_id(rs.getInt("invoice_id"));
                String fullDateTime = rs.getString("created_at");
                String onlyDate = fullDateTime.split(" ")[0];
                invoice.setCreated_at(LocalDate.parse(onlyDate));
                invoice.setTotal_amount(rs.getDouble("total_amount"));
                invoice.setInvoice_status(rs.getString("status"));

                Customer customer = new Customer();
                customer.setCus_id(rs.getInt("cus_id"));
                invoice.setCustomer(customer);

                invoices.add(invoice);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm hóa đơn theo mã khách hàng: " + e.getMessage());
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
        return invoices;
    }

    @Override
    public List<Invoice> searchInvoiceCreatedAt(LocalDate createdAt) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoices = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_invoice_by_createdAt(?)}");
            callSt.setString(1, String.valueOf(createdAt));
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoice_id(rs.getInt("invoice_id"));
                String fullDateTime = rs.getString("created_at");
                String onlyDate = fullDateTime.split(" ")[0];
                invoice.setCreated_at(LocalDate.parse(onlyDate));
                invoice.setTotal_amount(rs.getDouble("total_amount"));
                invoice.setInvoice_status(rs.getString("status"));

                Customer customer = new Customer();
                customer.setCus_id(rs.getInt("cus_id"));
                invoice.setCustomer(customer);

                invoices.add(invoice);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm hóa đơn theo mã khách hàng: " + e.getMessage());
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
        return invoices;
    }
}
