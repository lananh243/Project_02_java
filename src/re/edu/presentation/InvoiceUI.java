package re.edu.presentation;

import re.edu.business.model.InvoiceDetails;
import re.edu.business.model.Product;
import re.edu.business.model.invoice.Invoice;
import re.edu.business.service.invoice.InvoiceService;
import re.edu.business.service.invoice.InvoiceServiceImp;
import re.edu.business.service.invoice_details.InvoiceDetailService;
import re.edu.business.service.invoice_details.InvoiceDetailServiceImp;
import re.edu.business.service.product.ProductService;
import re.edu.business.service.product.ProductServiceImp;
import re.edu.validate.InvoiceDetailValidator;
import re.edu.validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceUI {
    private final InvoiceService invoiceService;
    private final InvoiceDetailService invoiceDetailService;
    private final ProductService productService;

    public InvoiceUI() {
        invoiceService = new InvoiceServiceImp();
        invoiceDetailService = new InvoiceDetailServiceImp();
        productService = new ProductServiceImp();
    }

    public static void displayInvoiceMenu(Scanner scanner) {
        InvoiceUI invoiceUI = new InvoiceUI();
        do {
            System.out.println("\u001B[34m ======================= QUẢN LÝ HÓA ĐƠN ========================\u001B[0m");
            System.out.println("\u001B[33m |   1. Hiển thị danh sách hóa đơn                              |\u001B[0m");
            System.out.println("\u001B[33m |   2. Thêm mới hóa đơn                                        |\u001B[0m");
            System.out.println("\u001B[33m |   3. Hiển thị chi tiết hóa đơn                               |\u001B[0m");
            System.out.println("\u001B[33m |   4. Tìm kiếm hóa đơn                                        |\u001B[0m");
            System.out.println("\u001B[33m |   5. Quay lại menu chính                                     |\u001B[0m");
            System.out.println("\u001B[34m ================================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    invoiceUI.displayListInvoice();
                    break;
                case 2:
                    invoiceUI.addInvoice(scanner);
                    break;
                case 3:
                    invoiceUI.findInvoiceDetailByInvoiceId(scanner);
                    break;
                case 4:
                    invoiceUI.displaySearchMenu(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public void displayListInvoice() {
        List<Invoice> listInvoives = invoiceService.findAllInvoice();
        Scanner scanner = new Scanner(System.in);
        if (listInvoives.isEmpty()) {
            System.err.println("Danh sách hóa đơn vẫn chưa có hóa đơn");
        } else {
            System.out.println("\u001B[34m ========================================== DANH SÁCH HÓA ĐƠN ======================================\u001B[0m");
            System.out.println("\u001B[33m | Mã hóa đơn |   Mã khách hàng   |    Ngày tạo hóa đơn    |    Tổng tiền     |     Trạng thái     |\u001B[0m");
            listInvoives.forEach(Invoice::displayData);
            System.out.println("\u001B[34m ===================================================================================================\u001B[0m");
        }
    }

    public void findInvoiceDetailByInvoiceId(Scanner scanner) {
        int invoiceId = Validator.validateInputInteger(scanner, "Nhập vào mã hóa đơn cần hiển thị chi tiết hóa đơn: ");
        List<InvoiceDetails> invoiceDetails = invoiceDetailService.findInvoiceDetailByInvoiceId(invoiceId);
        if (!invoiceDetails.isEmpty()) {
            System.out.printf("\u001B[34m ============================= CHI TIẾT HÓA ĐƠN CỦA HÓA ĐƠN CÓ ID: " +invoiceId+ " ===============================\n\u001B[0m", invoiceId);
            System.out.println("\u001B[33m |   Mã hóa đơn   |   Mã sản phẩm   |   Số lượng mua   |       Đơn giá      |      Thành tiền      |\u001B[0m");
            invoiceDetails.forEach(InvoiceDetails::displayData);
            System.out.println("\u001B[34m ===================================================================================================\u001B[0m");
        } else {
            System.err.println("Không tìm thấy hóa đơn có ID: "+invoiceId+ "để hiển thị chi tiết hóa đơn");
        }
    }

    public void addInvoice(Scanner scanner) {

        System.out.println("===== Nhập thông tin hóa đơn cần thêm mới =====");
//        Map<Integer,Integer> selectProducts = new HashMap<>();
        Invoice invoice = new Invoice();
        invoice.inputData(scanner);
        invoiceService.createInvoice(invoice);
        int invoiceId = invoice.getInvoice_id();
        System.out.println("Thêm mới hóa đơn hàng thành công, mã hóa đơn: "+invoice.getInvoice_id());
        while (true) {
            addInvoiceDetail(scanner, invoiceId);
            String choice = Validator.validateInputString(scanner, "Bạn có muốn thêm sản phẩm khác không? (y/n): ");
            if (choice.equalsIgnoreCase("n")) {
                break;
            }
//            System.out.println("\u001B[34m =============== DANH SÁCH SẢN PHẨM ================\u001B[0m");
//            List<Product> products = productService.findAll();
//            products.forEach(product -> System.out.printf(" | %-5d | %-20s | %-18s | \n", product.getPro_id(), product.getName(), product.getPrice()));
//            System.out.println("\u001B[34m ===================================================\n\u001B[0m");
//            int productId = Validator.validateInputInteger(scanner, "Chọn sản phẩm (hoặc 0 để kết thúc): ");
//            if (productId == 0) {
//                break;
//            }
//
//            Optional<Product> selectedProduct = products.stream().filter(product -> product.getPro_id() == productId).findFirst();
//            if (!selectedProduct.isPresent()) {
//                System.err.println("Sản phẩm không tồn tại. Vui lòng chọn lại.");
//                continue;
//            }
//            int quantity = InvoiceDetailValidator.validateQuantity(scanner, "Nhập vào số lượng sản phẩm: ", productId);
//            selectProducts.put(productId, quantity);
//
//            String choice = Validator.validateInputString(scanner, "Bạn có muốn thêm sản phẩm khác không? (y/n): ");
//            if (choice.equalsIgnoreCase("n")) {
//                break;
//            }
        }
//        boolean isCreated = invoiceService.createInvoice(invoice);
//        if (isCreated) {
//            System.out.println("Thêm mới hóa đơn thành công");
//            int invoiceId = invoice.getInvoice_id();
//            for (Map.Entry<Integer,Integer> entry : selectProducts.entrySet()) {
//                Product product = productService.findProductById(entry.getKey());
//                int quantity = entry.getValue();
//                double price = product.getPrice();
//
//                InvoiceDetails invoiceDetail = new InvoiceDetails();
//                invoiceDetail.setProduct(product);
//                invoiceDetail.setQuantity(quantity);
//                invoiceDetail.setInvoiceDetail_id(invoiceId);
//                invoiceDetail.setUnit_price(price);
//                addInvoiceDetail(invoiceDetail);
//            }
//        }
    }
    public void addInvoiceDetail(Scanner scanner, int invoiceId) {
        InvoiceDetails invoiceDetails = new InvoiceDetails();
        invoiceDetails.setInvoice_id(invoiceId);
        invoiceDetails.inputData(scanner);
        invoiceDetailService.createInvoiceDetail(invoiceDetails);
    }

    public void displaySearchMenu(Scanner scanner) {
        do {
            System.out.println("\u001B[34m =========================== MENU TÌM KIẾM =============================\u001B[0m");
            System.out.println("\u001B[33m |1. Tìm kiếm theo mã khách hàng                                       |\u001B[0m");
            System.out.println("\u001B[33m |2. Tìm kiếm theo ngày tháng năm xuất hóa đơn                         |\u001B[0m");
            System.out.println("\u001B[33m |3. Thoát                                                             |\u001B[0m");
            System.out.println("\u001B[34m =======================================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    searchInvoiveByCustomerId(scanner);
                    break;
                case 2:
                    searchInvoiceCreatedAt(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public void searchInvoiveByCustomerId(Scanner scanner) {
        int customerId = Validator.validateInputInteger(scanner, "Nhập vào mã khách hàng cần tìm: ");
        Invoice invoice = new Invoice();
        List<Invoice> result = invoiceService.searchInvoiveByCustomerId(customerId);

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy hóa đơn đơn nào có mã khách hàng: "+customerId);
        } else {
            System.out.println("\u001B[34m ========================== KẾT QUẢ TÌM KIẾM HÓA ĐƠN THEO MÃ KHÁCH HÀNG ============================\u001B[0m");
            System.out.println("\u001B[33m | Mã hóa đơn |   Mã khách hàng   |    Ngày tạo hóa đơn    |    Tổng tiền     |     Trạng thái     |\u001B[0m");
            result.forEach(Invoice::displayData);
            System.out.println("\u001B[34m ===================================================================================================\u001B[0m");
        }
    }

    public void searchInvoiceCreatedAt(Scanner scanner) {
        LocalDate created_at = Validator.validateInputDate(scanner, "Nhập vào ngày tháng năm xuất hóa đơn cần tìm (dd/MM/yyyy): ");
        Invoice invoice = new Invoice();
        List<Invoice> result = invoiceService.searchInvoiceCreatedAt(created_at);

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy hóa đơn nào có ngày tháng năm là: "+created_at);
        } else {
            System.out.println("\u001B[34m ================= KẾT QUẢ TÌM KIẾM HÓA ĐƠN THEO NGÀY, THÁNG, NĂM XUẤT HÓA ĐƠN =====================\u001B[0m");
            System.out.println("\u001B[33m | Mã hóa đơn |   Mã khách hàng   |    Ngày tạo hóa đơn    |    Tổng tiền     |     Trạng thái     |\u001B[0m");
            result.forEach(Invoice::displayData);
            System.out.println("\u001B[34m ===================================================================================================\u001B[0m");
        }
    }
}
