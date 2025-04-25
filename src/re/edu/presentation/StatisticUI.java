package re.edu.presentation;

import re.edu.business.model.Statistic;
import re.edu.business.model.invoice.Invoice;
import re.edu.business.service.invoice.InvoiceService;
import re.edu.business.service.invoice.InvoiceServiceImp;
import re.edu.business.service.statistic.StatisticService;
import re.edu.business.service.statistic.StatisticServiceImp;
import re.edu.validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StatisticUI {
    private final StatisticService statisticService;

    public StatisticUI() {
        statisticService = new StatisticServiceImp();
    }

    public static void displayStatisticMenu(Scanner scanner){
        StatisticUI statisticUI = new StatisticUI();
        do {
            System.out.println("\u001B[34m ============================ MENU THỐNG KÊ THEO DOANH THU ============================\u001B[0m");
            System.out.println("\u001B[33m 1. Tổng doanh thu của tất cả các ngày mà cửa hàng kinh doanh                         |\u001B[0m");
            System.out.println("\u001B[33m 2. Tổng doanh thu của tất cả các tháng mà cửa hàng kinh doanh                        |\u001B[0m");
            System.out.println("\u001B[33m 3. Tổng doanh thu của tất cả các năm mà cửa hàng kinh doanh                          |\u001B[0m");
            System.out.println("\u001B[33m 4. Thoát                                                                             |\u001B[0m");
            System.out.println("\u001B[34m ======================================================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    statisticUI.getRevenueByDay(scanner);
                    break;
                case 2:
                    statisticUI.getRevenueByMonth(scanner);
                    break;
                case 3:
                    statisticUI.getRevenueByYear(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public void getRevenueByDay(Scanner scanner) {
        LocalDate day = Validator.validateInputDate(scanner, "Nhập vào ngày muốn hiển thị thống kê: ");

        if (day.isAfter(LocalDate.now())) {
            System.err.println("Ngày không được lớn hơn ngày hiện tại");
            return;
        }
        List<Statistic> result = statisticService.getRevenueByDay(day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (result.isEmpty()) {
            System.err.println("Không có thống kê tổng doanh thu trong ngày: "+day.format(formatter));
        } else {
            result.forEach(statis -> System.out.printf("Ngày: %s  -  Tổng doanh thu: %.2f\n", statis.getRevenue().format(formatter), statis.getTotal_revenue()));
        }
    }


    public void getRevenueByYear(Scanner scanner) {
        int start_year = Validator.validateInputInteger(scanner, "Nhập vào năm bắt đầu: ");
        int end_year = Validator.validateInputInteger(scanner, "Nhập vào năm kết thúc: ");

        if (start_year > end_year) {
            System.err.println("Năm bắt đầu thống kê không được lớn hơn năm kết thúc");
            return;
        }

        if (start_year > LocalDate.now().getYear() && end_year > LocalDate.now().getYear()) {
            System.err.println("Năm bắt đầu và năm kết thúc không được lớn hơn năm hiện tại");
            return;
        }

        List<Statistic> result = statisticService.getRevenueByYear(start_year, end_year);
        if (result.isEmpty()) {
            System.err.println("Không có thống kê tổng doanh thu từ năm " +start_year+" - "+end_year);
        } else {
            result.forEach(statis -> System.out.printf("Năm: %d  -  Tổng doanh thu: %.2f\n", statis.getRevenue().getYear(),  statis.getTotal_revenue()));
        }

    }

    public void getRevenueByMonth(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDate start_month = Validator.validateInputMonthYear(scanner, "Nhập vào tháng và năm bắt đầu: ");
        LocalDate end_month = Validator.validateInputMonthYear(scanner, "Nhập vào tháng năm kết thúc: ");

        if (start_month.isAfter(end_month)) {
            System.err.println("Tháng năm bắt đầu không được lớn hơn tháng năm kết thúc");
            return;
        }

        if (start_month.isAfter(LocalDate.now())) {
            System.err.println("Tháng năm bắt đầu không được lớn hơn tháng năm hiện tại.");
            return;
        }

        if (end_month.isAfter(LocalDate.now())) {
            System.err.println("Tháng năm kết thúc không được lớn hơn tháng năm hiện tại.");
            return;
        }

        List<Statistic> result = statisticService.getRevenueByMonth(start_month, end_month);
        if (result.isEmpty()) {
            System.err.println("Không có thống kê tổng doanh thu từ tháng " +start_month.format(formatter)+" - "+end_month.format(formatter));
        } else {
            result.forEach(statis -> {
                System.out.printf("Tháng: %s  -  Tổng doanh thu: %.2f\n",
                        statis.getRevenue().format(formatter), statis.getTotal_revenue());
            });
        }
    }
}
