package re.edu.presentation;

import re.edu.business.model.Product;
import re.edu.business.service.product.ProductService;
import re.edu.business.service.product.ProductServiceImp;

import java.util.List;
import java.util.Scanner;

public class ProductUI {
    private final ProductService  productService;

    public ProductUI() {
        productService = new ProductServiceImp();
    }

    public static void displayCustomerMenu(Scanner scanner) {
        do {
            ProductUI productUI = new ProductUI();
            System.out.println("\u001B[34m ======================= QUẢN LÝ SẢN PHẨM =====================\u001B[0m");
            System.out.println("\u001B[34m 1. Hiển thị danh sách sản phẩm\u001B[0m");
            System.out.println("\u001B[34m 2. Thêm mới sản phẩm\u001B[0m");
            System.out.println("\u001B[34m 3. Cập nhật thông tin sản phẩm\u001B[0m");
            System.out.println("\u001B[34m 4. Xóa sản phẩm theo ID\u001B[0m");
            System.out.println("\u001B[34m 5. Quay lại menu chính\u001B[0m");
            System.out.println("\u001B[34m ================================================================\u001B[0m");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    productUI.displayListProduct();
                    break;
                case 2:
                    productUI.createProduct(scanner);
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

    public void displayListProduct() {
        List<Product> listProducts = productService.findAll();
        if (listProducts.isEmpty()) {
            System.err.println("Danh sách sản phẩm chưa có sản phẩm nào");
        } else {
            System.out.println("\u001B[34m ============= DANH SÁCH SẢN PHẨM ===================\u001B[0m");
            listProducts.forEach(System.out::println);
        }

    }

    public void createProduct(Scanner scanner) {
        System.out.println("Nhập vfao số lượng sản phẩm cần thêm mới: ");
        int size = Integer.parseInt(scanner.nextLine());
        boolean allAdded = true;
        for (int i = 0; i < size; i++) {
            System.out.println("=== Nhập thông tin sản phẩm thứ " + (i + 1) + " ===");
            Product product = new Product();
            product.inputData(scanner);

            if (!productService.addProduct(product)) {
                allAdded = false;
            }
        }

        if (allAdded) {
            System.out.println("Thêm mới sản phẩm thành công!");
        } else {
            System.err.println("Có lỗi xảy ra khi thêm sản phẩm.");
        }
    }
}
