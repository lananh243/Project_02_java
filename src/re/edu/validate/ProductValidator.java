package re.edu.validate;

import re.edu.business.dao.product.ProductDao;
import re.edu.business.dao.product.ProductDaoImp;

import java.util.Scanner;

public class ProductValidator {
    private static final ProductDao productDao = new ProductDaoImp();
    public static String validateName(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            try {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.err.println("Tên sản phẩm không được để trống, vui lòng nhập lại");
                    continue;
                }

                if (!stringRule.isValidString(name)) {
                    System.err.println("Tên sản phẩm vượt quá độ dài cho phép, vui lòng nhập lại");
                    continue;
                }

                if (productDao.isNameDuplicate(name)) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập tên khác");
                    continue;
                }
                return name;
            } catch (Exception e) {
                System.err.println("Lỗi không xác định khi nhập, vui lòng thử lại");
            }
        }
    }

    public static String validateBrand(Scanner scanner, String message, StringRule stringRule) {
        System.out.println(message);
        while (true) {
            try {
                String brand = scanner.nextLine().trim();
                if (brand.isEmpty()) {
                    System.err.println("Nhãn hàng của sản phẩm không được để trống, vui lòng nhập lại");
                    continue;
                }
                if (!stringRule.isValidString(brand)) {
                    System.err.println("Nhãn hàng của sản phẩm không đúng với độ dài cho phép, vui lòng nhập lại");
                }
                return brand;
            } catch (Exception e) {
                System.err.println("Lỗi không xác định khi nhập, vui lòng thử lại");
            }
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

            try {
                double price = Double.parseDouble(value);
                if (price > 0) {
                    return price;
                }
                System.err.println("Giá bán của sản phẩm phải lớn hơn 0, vui lòng nhập lại");
            } catch (NumberFormatException e) {
                System.err.println("Giá bán không phải là kiểu số thực, vui lòng nhập lại");
            }

        }
    }

    public static int validateStock(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Tồn kho của sản phẩm không được để trống, vui lòng nhập lại");
                continue;
            }

            try {
                int stock = Integer.parseInt(value);
                if (stock > 0) {
                    return stock;
                }
                System.err.println("Tồn kho của sản phẩm phải lớn hơn 0, vui lòng nhập lại");
            } catch (NumberFormatException e) {
                System.err.println("Tồn kho của sản phẩm không phải là số nguyên, vui lòng nhập lại");
            }

        }
    }

}
