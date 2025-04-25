package re.edu.presentation;

import re.edu.business.model.Product;
import re.edu.business.service.product.ProductService;
import re.edu.business.service.product.ProductServiceImp;
import re.edu.validate.ProductValidator;
import re.edu.validate.StringRule;
import re.edu.validate.Validator;

import java.util.ArrayList;
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
            System.out.println("\u001B[34m ======================= QUẢN LÝ SẢN PHẨM =======================\u001B[0m");
            System.out.println("\u001B[33m |   1. Hiển thị danh sách sản phẩm                             |\u001B[0m");
            System.out.println("\u001B[33m |   2. Thêm mới sản phẩm                                       |\u001B[0m");
            System.out.println("\u001B[33m |   3. Cập nhật thông tin sản phẩm                             |\u001B[0m");
            System.out.println("\u001B[33m |   4. Xóa sản phẩm theo ID                                    |\u001B[0m");
            System.out.println("\u001B[33m |   5. Tìm kiếm                                                |\u001B[0m");
            System.out.println("\u001B[33m |   6. Quay lại menu chính                                     |\u001B[0m");

            System.out.println("\u001B[34m ================================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    productUI.displayListProduct();
                    break;
                case 2:
                    productUI.createProduct(scanner);
                    break;
                case 3:
                    productUI.updateProduct(scanner);
                    break;
                case 4:
                    productUI.deleteProduct(scanner);
                    break;
                case 5:
                    productUI.displaySearchMenu(scanner);
                    break;
                case 6:
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
            System.out.println("\u001B[34m ======================================================== DANH SÁCH SẢN PHẨM ========================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
            listProducts.forEach(Product::displayData);
            System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
        }

    }

    public void createProduct(Scanner scanner) {
        int size = Validator.validateInputInteger(scanner, "Nhập vào số lượng sản phẩm cần thêm mới: ");
        boolean allAdded = true;
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            System.out.println("=== Nhập thông tin sản phẩm thứ " + (i + 1) + " ===");
            Product product = new Product();
            product.inputData(scanner);

            if (productService.addProduct(product)) {
                products.add(product);
            } else {
                allAdded = false;
            }
        }

        if (allAdded) {
            System.out.println("\u001B[34m ========================================= THÊM MỚI SẢN PHẨM THÀNH CÔNG =============================================\u001B[0m");
            System.out.println("\u001B[33m |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
            products.forEach(product -> System.out.printf(" | %-33s | %-21s | %-15.2f | %-16d | %-16s|\n", product.getName(), product.getBrand(), product.getPrice(), product.getStock(), product.isStatus() ? "Còn hàng" : "Hết hàng"));
            System.out.println("\u001B[34m ====================================================================================================================\u001B[0m");
        }
    }

    public void updateProduct(Scanner scanner) {
        int pro_id = Validator.validateInputInteger(scanner, "Nhập vào mã sản phẩm cần cập nhật: ");
        if (productService.findProductById(pro_id) != null) {
            Product product = productService.findProductById(pro_id);
            System.out.println("\u001B[34m ======================================================== THÔNG TIN SẢN PHẨM CÓ ID: " +pro_id+ " ========================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
            product.displayData();
            System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
            do {
                System.out.println("\u001B[34m ================== Chọn thông tin sản phẩm bạn muốn cập nhật ================\u001B[0m");
                System.out.println("\u001B[33m |    1. Cập nhật tên sản phẩm                                               |\u001B[0m ");
                System.out.println("\u001B[33m |    2. Cập nhật nhãn hàng của sản phẩm                                     |\u001B[0m");
                System.out.println("\u001B[33m |    3. Cập nhật giá bán của sản phẩm                                       |\u001B[0m");
                System.out.println("\u001B[33m |    4. Cập nhật số lượng tồn kho của sản phẩm                              |\u001B[0m");
                System.out.println("\u001B[33m |    5. Cập nhật trạng thái của sản phẩm                                    |\u001B[0m");
                System.out.println("\u001B[33m |    6. Thoát                                                               |\u001B[0m");
                System.out.println("\u001B[34m =============================================================================\u001B[0m");
                int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
                switch (choice) {
                    case 1:
                        String productName = ProductValidator.validateName(scanner, "Nhập vào tên sản phẩm mới: ", new StringRule(100));
                        product.setName(productName);
                        break;
                    case 2:
                        String productBrand = ProductValidator.validateBrand(scanner, "Nhập vào nhãn hàng mới của sản phẩm: ", new StringRule(50));
                        product.setBrand(productBrand);
                        break;
                    case 3:
                        double productPrice = ProductValidator.validatePrice(scanner, "Nhập vào giá bán mới của sản phẩm: ");
                        product.setPrice(productPrice);
                        break;
                    case 4:
                        int productStock = ProductValidator.validateStock(scanner, "Nhập vào số lượng tồn kho mới của sản phẩm: ");
                        product.setStock(productStock);
                        break;
                    case 5:
                        boolean status = Validator.validateInputBoolean(scanner, "Nhập vào trạng thái mới của sản phẩm (true | false): ");
                        product.setStatus(status);
                        break;
                    case 6:
                        return;
                    default:
                        System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
                }

                boolean result = productService.updateProduct(product);
                if (result) {
                    System.out.println("\u001B[34m ======================================================== CẬP NHẬT THÀNH CÔNG THÔNG TIN CHO SẢN PHẨM CÓ ID: "+pro_id+ " ========================================================\u001B[0m");
                    System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
                    product.displayData();
                    System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
                } else {
                    System.err.println("Có lỗi trong quá trình cập nhật sản phẩm");
                }
            } while (true);
        } else {
            System.err.println("Không tồn tại mã sản phẩm: "+pro_id);
        }
    }

    public void deleteProduct(Scanner scanner) {
        int pro_id = Validator.validateInputInteger(scanner, "Nhập vào mã sản phẩm cần xóa: ");
        Product product = productService.findProductById(pro_id);

        if (product != null) {
            if (product.isStatus() == false) {
                System.err.println("Sản phẩm có ID = " +pro_id+ " đã bị xóa hoặc đã được mua hàng thành công");
                return;
            }
            System.out.println("\u001B[34m ======================================================== THÔNG TIN CỦA SẢN PHẨM CÓ ID: "+pro_id+ " ========================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
            product.displayData();
            System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
            while (true) {
                System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này không ? (y/n): ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("y")) {
                    boolean result = productService.deleteProduct(product);
                    if (result) {
                        System.out.println("Xóa sản phẩm thành công sản phẩm có ID = "+pro_id);
                        List<Product> listProducts = productService.findAll();
                        System.out.println("\u001B[34m ======================================================== DANH SÁCH SẢN PHẨM SAU KHI XÓA ID: "+pro_id+ " ========================================================\u001B[0m");
                        System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
                        listProducts.forEach(Product::displayData);
                        System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
                    } else {
                        System.out.println("\033[0;31m Không thể xóa vì sản phầm đã từng được mua (đã tồn tại trong bảng chi tiết hóa đơn)\u001B[0m");
                    }
                    break;
                } else if (input.equalsIgnoreCase("n")) {
                    System.out.println("Hủy xóa sản phẩm.");
                    break;
                } else {
                    System.err.println("Vui lòng nhập 'y' hoặc 'n'.");
                }
            }
        } else {
            System.err.println("Không tồn tại sản phẩm có Id: "+pro_id);
        }
    }

    public void displaySearchMenu(Scanner scanner) {
        do {
            System.out.println("\u001B[34m ==================== MENU TÌM KIẾM ==========================\u001B[0m");
            System.out.println("\u001B[33m |    1. Tìm kiếm sản phẩm theo nhãn hàng                    |\u001B[0m");
            System.out.println("\u001B[33m |    2. Tìm kiếm sản phẩm theo khoảng giá                   |\u001B[0m");
            System.out.println("\u001B[33m |    3. Tìm kiếm sản phẩm theo tồn kho                      |\u001B[0m");
            System.out.println("\u001B[33m |    4. Thoát                                               |\u001B[0m");
            System.out.println("\u001B[34m =============================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    searchProductByBrand(scanner);
                    break;
                case 2:
                    searchProductByPriceRange(scanner);
                    break;
                case 3:
                    searchProductByStockAvailability(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng nhập lại");
            }
        } while (true);
    }

    public void searchProductByBrand(Scanner scanner) {
        String pro_brand = ProductValidator.validateName(scanner, "Nhập vào nhãn hàng cần tìm: ", new StringRule(50));
        Product product = new Product();
        product.setBrand(pro_brand);
        List<Product> result = productService.searchProductByBrand(product);

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy nhãn hàng nào là: "+pro_brand);
        } else {
            System.out.println("******************* Kết quả tìm kiếm theo nhãn hàng ******************");
            System.out.println();
            System.out.println("\u001B[34m ======================================================== DANH SÁCH SẢN PHẨM ========================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
            result.forEach(Product::displayData);
            System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
        }
    }

    public void searchProductByPriceRange(Scanner scanner) {
        double min_price = ProductValidator.validatePrice(scanner, "Nhập vào giá bắt đầu: ");
        double max_price = ProductValidator.validatePrice(scanner, "Nhập vào giá kết thúc: ");
        if (min_price > max_price) {
            System.err.println("Giá bắt đầu không được lớn giá kết thúc");
            return;
        }
        List<Product> result = productService.searchProductByPriceRange(min_price, max_price);

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy sản phẩm nào nằm trong khoảng giá từ " +min_price+ " - " +max_price);
        } else {
            System.out.println("**************** Kết quả tìm kiếm theo khoảng giá *****************");
            System.out.println();
            System.out.println("\u001B[34m ======================================================== DANH SÁCH SẢN PHẨM ========================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
            result.forEach(Product::displayData);
            System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
        }

    }

    public void searchProductByStockAvailability(Scanner scanner) {
        int start_stock = ProductValidator.validateStock(scanner, "Nhập vào giá trị tồn kho bắt đầu: ");
        int end_stock = ProductValidator.validateStock(scanner, "Nhập vào giá trị tồn kho kết thúc: ");

        if (start_stock > end_stock) {
            System.err.println("Giá trị tồn kho bắt đầu không được lớn hơn giá trị tồn kho kết thúc");
            return;
        }
        List<Product> result = productService.searchProductByStockAvailability(start_stock, end_stock);

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy sản phẩm nằm trong giá trị tồn kho từ " + start_stock + " - " + end_stock);
        } else {
            System.out.println("***************** Kết quả tìm kiếm theo tồn kho ******************");
            System.out.println();
            System.out.println("\u001B[34m ======================================================== DANH SÁCH SẢN PHẨM ========================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Sản Phẩm  |            Tên Sản Phẩm           |      Thương Hiệu      |       Giá       |      Tồn Kho     |    Trạng thái   |\u001b[0m");
            result.forEach(Product::displayData);
            System.out.println("\u001B[34m ====================================================================================================================================\u001B[0m");
        }
    }
}
