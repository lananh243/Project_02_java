package re.edu.validate;

import java.io.Console;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;

public class AccountValidator {

    public static String validateUsername (Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                System.err.println("Bạn chưa nhập tên người dùng, vui lòng nhập lại");
                continue;
            }

            if (!stringRule.isValidString(username)) {
                System.err.println("Tên người dùng vượt quá độ dài cho phép, vui lòng nhập lại");
                continue;
            }
            return username;
        }
    }

    public static String validatePassword (Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);

        while (true) {
            String password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.err.println("Bạn chưa nhập mật khẩu, vui lòng nhập lại");
                continue;
            }

            if (!stringRule.isValidString(password)) {
                System.err.println("Mật khẩu vượt quá độ dài cho phép, vui lòng nhập lại");
                continue;
            }
            return password;
        }
    }

}
