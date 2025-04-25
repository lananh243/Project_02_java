package re.edu.validate;

import re.edu.business.model.Product;
import re.edu.business.service.product.ProductService;
import re.edu.business.service.product.ProductServiceImp;

import java.util.Scanner;

public class InvoiceDetailValidator {

    public static int validateQuantity(Scanner scanner, String message, int productId) {
        ProductService productService = new ProductServiceImp();
        Product product = productService.findProductById(productId);
        int stockQuantity = product.getStock();
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Dữ liệu không được để trống. Vui lòng nhập lại.");
                continue;
            }

            try {
                int quantity = Integer.parseInt(input);
                if (quantity < 0) {
                    System.err.println("Số lượng mua phải lớn hơn 0");
                    continue;
                }

                if (quantity > stockQuantity) {
                    System.err.println("Số lượng mua vượt quá tồn kho. Vui lòng nhập lại.");
                    continue;
                }
                return quantity;
            } catch (NumberFormatException e) {
                System.err.println("Dữ liệu nhập vào không phải số nguyên vui lòng nhập lại");
            }
        }
    }
}
