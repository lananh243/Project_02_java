package re.edu;

import re.edu.presentation.*;
import re.edu.validate.Validator;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            boolean isLoggedIn = AccountUI.displayAccountMenu();
            if (isLoggedIn) {
                mainMenu(scanner);
            }

        }
    }

    public static void mainMenu(Scanner scanner) {
        do {
            System.out.println("\u001B[34m =========================== MENU CHÍNH =========================\u001B[0m");
            System.out.println("\u001B[33m |   1. Quản lý sản phẩm điện thoại                             |\u001B[0m");
            System.out.println("\u001B[33m |   2. Quản lý khách hàng                                      |\u001B[0m");
            System.out.println("\u001B[33m |   3. Quản lý hóa đơn                                         |\u001B[0m");
            System.out.println("\u001B[33m |   4. Thống kê doanh thu                                      |\u001B[0m");
            System.out.println("\u001B[33m |   5. Đăng xuất                                               |\u001B[0m");
            System.out.println("\u001B[34m ================================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    ProductUI.displayCustomerMenu(scanner);
                    break;
                case 2:
                    CustomerUI.displayCustomerMenu(scanner);
                    break;
                case 3:
                    InvoiceUI.displayInvoiceMenu(scanner);
                    break;
                case 4:
                    StatisticUI.displayStatisticMenu(scanner);
                    break;
                case 5:
                    System.out.println("\u001B[33m Bạn có chắc chắn muốn đăng xuất không? (Y/N): \u001B[0m");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("\u001B[34m Đăng xuất thành công\u001B[0m");
                        return;
                    } else if (confirm.equalsIgnoreCase("N")) {
                        System.out.println("Hủy bỏ đăng xuất");
                    } else {
                        System.out.println("Vui lòng nhập 'Y' hoặc 'N'.");
                    }
                    break;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }
}
