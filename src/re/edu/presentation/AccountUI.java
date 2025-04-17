package re.edu.presentation;

import re.edu.business.model.Account;
import re.edu.business.service.account.AccountService;
import re.edu.business.service.account.AccountServiceImp;

import java.util.Scanner;

public class AccountUI {

    public static boolean displayAccountMenu() {
        AccountService accountService = new AccountServiceImp();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\u001B[34m ======================= HỆ THỐNG QUẢN LÝ CỬA HÀNG =====================\u001B[0m");
            System.out.println("\u001B[34m 1. Đăng nhập Admin\u001B[0m");
            System.out.println("\u001B[34m 2. Thoát\u001B[0m");
            System.out.println("\u001B[34m =======================================================================\u001B[0m");
            int choice = ChoiceValidator.validateChoice(scanner);

            switch (choice) {
                case 1:
                    boolean loginSuccess = logIn(scanner, accountService);
                    if (loginSuccess) {
                        return true;
                    }
                case 2:
                    System.exit(0);
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public static boolean logIn(Scanner scanner, AccountService accountService) {
        Account account = new Account();
        account.inputData(scanner);
        return accountService.logIn(account.getUsername(), account.getPassword());
    }

}
