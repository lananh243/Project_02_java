package re.edu.business.dao.account;

import re.edu.business.config.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImp implements AccountDao {
    @Override
    public boolean logIn(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call log_in(?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                System.out.println("Đăng nhập thành công! Xin chào, " + rs.getString("username"));
                return true;
            } else {
                System.err.println("Đăng nhập thất bại. Vui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định khi làm việc với DB: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
