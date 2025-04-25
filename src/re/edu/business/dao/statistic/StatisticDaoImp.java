package re.edu.business.dao.statistic;

import re.edu.business.config.ConnectionDB;
import re.edu.business.model.Statistic;
import re.edu.business.model.invoice.Invoice;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatisticDaoImp implements StatisticDao {

    @Override
    public List<Statistic> getRevenueByDay(LocalDate created_at) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Statistic> listRevenue = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_revenue_by_day(?)}");
            callSt.setDate(1, Date.valueOf(created_at));
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Statistic statistic = new Statistic();
                statistic.setRevenue(rs.getDate("revenue_day").toLocalDate());
                statistic.setTotal_revenue(rs.getDouble("total_revenue"));
                listRevenue.add(statistic);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thống kê doanh thu theo ngày: " + e.getMessage());
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
        return listRevenue;
    }

    @Override
    public List<Statistic> getRevenueByMonth(LocalDate start_month, LocalDate end_month) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Statistic> listRevenue = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_revenue_by_month(?,?)}");
            callSt.setDate(1, Date.valueOf(start_month));
            callSt.setDate(2, Date.valueOf(end_month));
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Statistic statistic = new Statistic();
                int month = rs.getInt("revenue_month");
                int year = rs.getInt("revenue_year");
                statistic.setRevenue(LocalDate.of(year, month, 1));
                statistic.setTotal_revenue(rs.getDouble("total_revenue"));
                listRevenue.add(statistic);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thống kê doanh thu theo tháng: " + e.getMessage());
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
        return listRevenue;
    }

    @Override
    public List<Statistic> getRevenueByYear(int start_year, int end_year) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Statistic> listRevenue = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callSt = conn.prepareCall("{call get_revenue_by_year(?,?)}");
            callSt.setInt(1, start_year);
            callSt.setInt(2, end_year);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Statistic statistic = new Statistic();
                int year = rs.getInt("revenue_year");
                statistic.setRevenue(LocalDate.of(year, 1, 1));
                statistic.setTotal_revenue(rs.getDouble("total_revenue"));
                listRevenue.add(statistic);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Có lỗi sảy ra trong quá trình thống kê doanh thu theo năm: " + e.getMessage());
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
        return listRevenue;
    }
}
