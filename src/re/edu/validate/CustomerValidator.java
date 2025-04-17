package re.edu.validate;

import java.util.Scanner;

public class CustomerValidator {
    public static String validateName(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.err.println("Tên khách hàng không được để trống, vui lòng nhập lại");
                continue;
            }

            if (!stringRule.isValidString(name)) {
                System.err.println("Tên khách hàng vượt quá độ dài cho phép, vui lòng nhập lại");
                continue;
            }
            return name;
        }
    }


    public static String validateEmail(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
         while (true) {
             String email = scanner.nextLine().trim();

             if (email.isEmpty()) {
                 System.err.println("Bạn chưa nhập email của khách hàng, vui lòng nhập lại");
                 continue;
             }
             if (Validator.isValidEmail(email)) {
                 return email;
             }
             System.err.println("Không đúng đinh dạng email, vui lòng nhập lại");
         }
    }

    public static String validatePhone(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String inputPhone = scanner.nextLine().trim();
            if (inputPhone.isEmpty()) {
                System.err.println("Bạn chưa nhập số điện thoại của sinh viên, vui lòng nhập lại");
                continue;
            }

            if (Validator.isValidPhoneNumberVN(inputPhone)) {
                return inputPhone;
            }
            System.err.println("Không đúng số điện thoại di động Việt Nam, vui lòng nhập lại");
        }
    }

    public static String validateAddress(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String inputAddress = scanner.nextLine().trim();
            if (inputAddress.isEmpty()) {
                System.err.println("Bạn chưa nhập địachir, vui lòng nhập lại");
            }

            if (!stringRule.isValidString(inputAddress)) {
                System.err.println("Địa chỉ không đúng độ dài cho phép, vui lòng nhập lại");
                continue;
            }

            return inputAddress;
        }
    }
}
