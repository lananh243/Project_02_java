package re.edu.validate;

import re.edu.business.dao.customer.CustomerDao;
import re.edu.business.dao.customer.CustomerDaoImp;

import java.util.Scanner;

public class CustomerValidator {
    public static final CustomerDao customerDao = new CustomerDaoImp();

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
             if (!Validator.isValidEmail(email)) {
                 System.err.println("Không đúng đinh dạng email, vui lòng nhập lại");
                 continue;
             }

             if (customerDao.isEmailDuplicate(email)) {
                 System.err.println("Email đã tồn tại trong hệ thống, vui lòng nhập lại");
                 continue;
             }
             return email;
         }
    }


    public static String validateSearchEmail(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String email = scanner.nextLine().trim();

            if (email.isEmpty()) {
                System.err.println("Bạn chưa nhập email của khách hàng, vui lòng nhập lại");
                continue;
            }
            if (!Validator.isValidEmail(email)) {
                System.err.println("Không đúng đinh dạng email, vui lòng nhập lại");
                continue;
            }
            return email;
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

            if (!Validator.isValidPhoneNumberVN(inputPhone)) {
                System.err.println("Không đúng số điện thoại di động Việt Nam, vui lòng nhập lại");
                continue;
            }

            if (customerDao.isPhoneDuplicate(inputPhone)) {
                System.err.println("Số điện thoại đã tồn tại trong hệ thống, vui lòng nhập số khác");
                continue;
            }
            return inputPhone;
        }
    }

    public static String validateAddress(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String inputAddress = scanner.nextLine().trim();
            if (inputAddress.isEmpty()) {
                System.err.println("Bạn chưa nhập địa chỉ, vui lòng nhập lại");
                continue;
            }

            if (!stringRule.isValidString(inputAddress)) {
                System.err.println("Địa chỉ không đúng độ dài cho phép, vui lòng nhập lại");
                continue;
            }

            return inputAddress;
        }
    }

    public static int validateStatusCus(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Trạng thái của khách hàng không để trống, vui lòng nhập lại");
                continue;
            }

            try {
                int status = Integer.parseInt(value);
                if (status == 0 || status == 1) {
                    return status;
                }
                System.err.println("Trạng thái của khách hàng phải nhập là 0 hoặc 1, vui lòng nhập lại");
            } catch (NumberFormatException e) {
                System.err.println("Trạng thái của sản phẩm không phải là số nguyên, vui lòng nhập lại");
            }
        }
    }

}
