package re.edu;

import re.edu.presentation.AccountUI;
import re.edu.presentation.ChoiceValidator;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\u001B[34m ********************************MENU CHÍNH****************************\u001B[0m");
            System.out.println("\u001B[34m 1. Tài khoản\u001B[0m");
            System.out.println("\u001B[34m 0. Thoát\u001B[0m");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    AccountUI.displayAccountMenu();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }
}
