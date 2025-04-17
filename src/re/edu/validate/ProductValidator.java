package re.edu.validate;

import java.util.Scanner;

public class ProductValidator {
    public static String validateName(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống, vui lòng nhập lại");
                continue;
            }

            if (!stringRule.isValidString(name)) {
                System.err.println("Tên sản phẩm vượt quá độ dài cho phép, vui lòng nhập lại");
                continue;
            }
            return name;
        }
    }

    public static String validateBrand(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            String brand = scanner.nextLine().trim();
            if (brand.isEmpty()) {
                System.err.println("Nhãn hàng của sản phẩm không được để trống, vui lòng nhập lại");
                continue;
            }
            if (!stringRule.isValidString(brand)) {
                System.err.println("Nhãn hàng của sản phẩm không đúng với độ dài cho phép, vui lòng nhập lại");
            }
            return brand;
        }
    }

    public static double validatePrice(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Giá bán của sản phẩm không được để trống, vui lòng nhập lại");
                continue;
            }

            double price = Double.parseDouble(value);
            if (price > 0) {
                return price;
            }
            System.err.println("Giá bán của sản phẩm phải lớn hơn 0, vui lòng nhập lại");
        }
    }

    public static int validateStock(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Số lượng tồn kho của sản phẩm không được để trống, vui lòng nhập lại");
                continue;
            }

            int stock = Integer.parseInt(value);
            if (stock > 0) {
                return stock;
            }
            System.out.println("Sản phẩm tồn kho của sản phẩm phải lớn hơn 0, vui lòng nhập lại");

        }
    }
}
