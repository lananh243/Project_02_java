package re.edu.business.dao.customer;

import re.edu.business.config.ConnectionDB;
import re.edu.business.model.customer.Customer;
import re.edu.business.model.customer.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImp implements CustomerDao {
    @Override
    public List<Customer> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> customers = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_all_customer()}");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCus_id(rs.getInt("cus_id"));
                customer.setName(rs.getString("cus_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setGender(Gender.valueOf(rs.getString("gender")));
                customer.setStatus(rs.getBoolean("status"));
                customers.add(customer);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy ra tất cả khách hàng" + e.getMessage());
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
        return customers;
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call create_customer(?,?,?,?,?)}");
            callSt.setString(1, customer.getName());
            callSt.setString(2, customer.getPhone());
            callSt.setString(3, customer.getEmail());
            callSt.setString(4, customer.getAddress());
            callSt.setString(5, String.valueOf(customer.getGender()));

            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thêm mới khách hàng" + e.getMessage());
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
    public boolean updateCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_customer(?,?,?,?,?,?,?)}");
            callSt.setInt(1, customer.getCus_id());
            callSt.setString(2, customer.getName());
            callSt.setString(3, customer.getPhone());
            callSt.setString(4, customer.getEmail());
            callSt.setString(5, customer.getAddress());
            callSt.setString(6, String.valueOf(customer.getGender()));
            callSt.setBoolean(7, customer.isStatus());

            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình cập nhật khách hàng" + e.getMessage());
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
    public Customer findCustomerById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Customer customer = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call find_customer_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCus_id(rs.getInt("cus_id"));
                customer.setName(rs.getString("cus_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setGender(Gender.valueOf(rs.getString("gender")));
                customer.setStatus(rs.getBoolean("status"));
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy ra mã khách hàng" + e.getMessage());
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
        return customer;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call delete_customer(?)}");
            callSt.setInt(1, customer.getCus_id());
            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình xóa khách hàng theo mã khách hàng" + e.getMessage());
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
    public boolean isPhoneDuplicate(String phone) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call check_duplicate_phone(?,?)}");
            callSt.setString(1, phone);
            callSt.registerOutParameter(2, Types.BOOLEAN);
            callSt.execute();
            conn.commit();

            return callSt.getBoolean(2);
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình kiểm tra sự tồn tại số điện thoại của khách hàng" + e.getMessage());
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
    public boolean isEmailDuplicate(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call check_duplicate_email(?,?)}");
            callSt.setString(1, email);
            callSt.registerOutParameter(2, Types.BOOLEAN);
            callSt.execute();
            conn.commit();
            return callSt.getBoolean(2);
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình kiểm tra sự tồn tại của email" + e.getMessage());
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
    public List<Customer> searchCustomerByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> customers = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_customer_by_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCus_id(rs.getInt("cus_id"));
                customer.setName(rs.getString("cus_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setGender(Gender.valueOf(rs.getString("gender")));
                customer.setStatus(rs.getBoolean("status"));
                customers.add(customer);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm khách hàng theo tên: " + e.getMessage());
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
        return customers;
    }

    @Override
    public List<Customer> searchCustomerByEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> customers = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_customer_by_email(?)}");
            callSt.setString(1, email);
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCus_id(rs.getInt("cus_id"));
                customer.setName(rs.getString("cus_name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setGender(Gender.valueOf(rs.getString("gender")));
                customer.setStatus(rs.getBoolean("status"));
                customers.add(customer);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm khách hàng theo email: " + e.getMessage());
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
        return customers;
    }
}
