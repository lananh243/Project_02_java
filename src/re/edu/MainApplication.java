package re.edu;

import re.edu.presentation.AccountUI;
import re.edu.presentation.ChoiceValidator;
import re.edu.presentation.CustomerUI;
import re.edu.presentation.ProductUI;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountUI.displayAccountMenu();
        do {
            System.out.println("\u001B[34m ======================= MENU CHÍNH =====================\u001B[0m");
            System.out.println("\u001B[34m 1. Quản lý sản phẩm điện thoại\u001B[0m");
            System.out.println("\u001B[34m 2. Quản lý khách hàng\u001B[0m");
            System.out.println("\u001B[34m 3. Quản lý hóa đơn\u001B[0m");
            System.out.println("\u001B[34m 4. Thống kê doanh thu\u001B[0m");
            System.out.println("\u001B[34m 5. Đăng xuất\u001B[0m");
            System.out.println("\u001B[34m ================================================================\u001B[0m");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    ProductUI.displayCustomerMenu(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("\u001B[33m Bạn có chắc chắn muốn đăng xuất không? (Y/N): \u001B[0m");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("\u001B[34m Đăng xuất thành công\u001B[0m");
                        return;
                    } else {
                        System.out.println("Hủy bỏ đăng xuất");
                    }
                    break;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }
}
