package re.edu.presentation;

import java.util.Scanner;

public class CustomerUI {
    public static void displayCustomerMenu(Scanner scanner) {
        do {
            System.out.println("\u001B[34m ======================= QUẢN LÝ KHÁCH HÀNG =====================\u001B[0m");
            System.out.println("\u001B[34m 1. Hiển thị danh sách khách hàng\u001B[0m");
            System.out.println("\u001B[34m 2. Thêm mới khách hàng\u001B[0m");
            System.out.println("\u001B[34m 3. Cập nhật thông tin khách hàng\u001B[0m");
            System.out.println("\u001B[34m 4. Xóa khách hàng theo ID\u001B[0m");
            System.out.println("\u001B[34m 5. Quay lại menu chính\u001B[0m");
            System.out.println("\u001B[34m ================================================================\u001B[0m");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }

        } while (true);
    }
}
