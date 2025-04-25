package re.edu.business.dao.product;

import re.edu.business.config.ConnectionDB;
import re.edu.business.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao{

    @Override
    public boolean addProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call add_product(?,?,?,?)}");

            callSt.setString(1, product.getName());
            callSt.setString(2, product.getBrand());
            callSt.setDouble(3, product.getPrice());
            callSt.setInt(4, product.getStock());
            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thêm sản phẩm, dữ liệu đã được rollback");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định khi làm việc với db");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call update_product(?,?,?,?,?,?)}");

            callSt.setInt(1, product.getPro_id());
            callSt.setString(2, product.getName());
            callSt.setString(3, product.getBrand());
            callSt.setDouble(4, product.getPrice());
            callSt.setInt(5, product.getStock());
            callSt.setBoolean(6, product.isStatus());

            callSt.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình cập nhật, dữ liệu đã được rollback");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Có lỗi khi thực hiện rollback: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Product findProductById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setPro_id(rs.getInt("pro_id"));
                product.setName(rs.getString("pro_name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
            }

        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình lấy mã sản phẩm: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public boolean deleteProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call delete_product_if_not_exists_in_invoice_details(?, ?)}");
            callSt.setInt(1, product.getPro_id());
            callSt.registerOutParameter(2, Types.BOOLEAN);
            callSt.execute();
            boolean success = callSt.getBoolean(2);

            conn.commit();
            return success;

        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình xóa, dữ liệu đã được rollback");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi ko xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Product> searchProductByBrand(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_product_by_brand(?,?)}");
            callSt.setString(1, product.getBrand());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setPro_id(rs.getInt("pro_id"));
                pro.setName(rs.getString("pro_name"));
                pro.setBrand(rs.getString("brand"));
                pro.setPrice(rs.getDouble("price"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
                products.add(pro);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm sản phẩm theo nhãn hàng: " + e.getMessage());
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
        return products;
    }

    @Override
    public List<Product> searchProductByPriceRange(double price_min, double price_max) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);

            callSt = conn.prepareCall("{call search_product_by_price_range(?,?)}");
            callSt.setDouble(1,  price_min);
            callSt.setDouble(2, price_max);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setPro_id(rs.getInt("pro_id"));
                pro.setName(rs.getString("pro_name"));
                pro.setBrand(rs.getString("brand"));
                pro.setPrice(rs.getDouble("price"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
                products.add(pro);
            }

        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm sản phẩm theo khoảng giá: " + e.getMessage());
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
        return products;
    }

    @Override
    public List<Product> searchProductByStockAvailability(int start_stock, int end_stock) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call search_product_by_stock_availability(?,?)}");
            callSt.setInt(1, start_stock);
            callSt.setInt(2, end_stock);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setPro_id(rs.getInt("pro_id"));
                pro.setName(rs.getString("pro_name"));
                pro.setBrand(rs.getString("brand"));
                pro.setPrice(rs.getDouble("price"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
                products.add(pro);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình tìm kiếm sản phẩm theo tồn kho: " + e.getMessage());
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
        return products;
    }

    @Override
    public boolean isNameDuplicate(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call check_duplicate_pro_name(?,?)}");
            callSt.setString(1, name);
            callSt.registerOutParameter(2, Types.BOOLEAN);
            callSt.execute();
            conn.commit();
            return callSt.getBoolean(2);
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình kiểm tra tồn tại tên của sản phẩm: " + e.getMessage());
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
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_product()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setPro_id(rs.getInt("pro_id"));
                product.setName(rs.getString("pro_name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        }catch (SQLException e){
            System.err.println("Có lỗi sảy ra trong quá trình lấy ra tất cả sản phẩm" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return products;
    }
}
