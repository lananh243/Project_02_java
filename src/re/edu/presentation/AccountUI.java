package re.edu.presentation;

import re.edu.business.model.Account;
import re.edu.business.service.account.AccountService;
import re.edu.business.service.account.AccountServiceImp;
import re.edu.validate.Validator;

import java.util.Scanner;

public class AccountUI {

    public static void displayAccountMenu() {
        AccountService accountService = new AccountServiceImp();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\u001B[34m ***********************HỆ THỐNG QUẢN LÝ CỬA HÀNG***********************\u001B[0m");
            System.out.println("\u001B[34m 1. Đăng nhập Admin\u001B[0m");
            System.out.println("\u001B[34m 2. Thoát\u001B[0m");
            int choice = ChoiceValidator.validateChoice(scanner);

            switch (choice) {
                case 1:
                    boolean loginSuccess = logIn(scanner, accountService);
                    if (loginSuccess) {
                        displayLoginMenu(scanner);
                    }
                    break;
                case 2:
                    return;
            }
        } while (true);
    }

    public static boolean logIn(Scanner scanner, AccountService accountService) {
        Account account = new Account();
        account.inputData(scanner);
        return accountService.logIn(account.getUsername(), account.getPassword());
    }

    public static void displayLoginMenu(Scanner scanner) {
        do {
            System.out.println("\u001B[34m *********************MENU SAU ĐĂNG NHẬP***********************\u001B[0m");
            System.out.println("\u001B[34m 1. Đăng xuất\u001B[0m");
            System.out.println("\u001B[34m 2. Trở về menu chính\u001B[0m");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    System.out.println("\u001B[33m Bạn có chắc chắn muốn đăng xuất không? (Y/N): \u001B[0m");
                    String confirm = scanner.nextLine().trim();
                    if (confirm.equalsIgnoreCase("Y")) {
                        System.out.println("\u001B[34m Đăng xuất thành công\u001B[0m");
                        return;
                    } else {
                        System.out.println("Hủy bỏ đăng xuất");
                    }
                    break;
                case 2:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }
}
