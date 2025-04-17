package re.edu.business.dao.product;

import re.edu.business.config.ConnectionDB;
import re.edu.business.model.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                product.setName(rs.getString("pro_name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        }catch (SQLException e){
            System.err.println("Có lỗi sảy ra trong quá trình lấy ra sản phẩm" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định khi làm việc với db: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return products;
    }
}
