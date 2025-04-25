package re.edu.business.model;

import re.edu.business.model.invoice.Invoice;
import re.edu.business.service.product.ProductService;
import re.edu.business.service.product.ProductServiceImp;
import re.edu.utils.IApp;
import re.edu.validate.InvoiceDetailValidator;
import re.edu.validate.Validator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InvoiceDetails implements IApp {
    private int invoiceDetail_id;
    private int invoice_id;
    private Product product;
    private int quantity;
    private double unit_price;

    public InvoiceDetails() {
    }

    public InvoiceDetails(int invoiceDetail_id, int invoice_id, Product product, int quantity, double unit_price) {
        this.invoiceDetail_id = invoiceDetail_id;
        this.invoice_id = invoice_id;
        this.product = product;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getInvoiceDetail_id() {
        return invoiceDetail_id;
    }

    public void setInvoiceDetail_id(int invoiceDetail_id) {
        this.invoiceDetail_id = invoiceDetail_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.product = choiceProduct(scanner);
        int productId = this.product.getPro_id();
        this.quantity = InvoiceDetailValidator.validateQuantity(scanner, "Nhập vào số lượng mua: ", productId);
        this.unit_price = this.product.getPrice();
    }

    private Product choiceProduct(Scanner scanner) {
        ProductService productService = new ProductServiceImp();
        List<Product> products = productService.findAll().stream().filter(product -> product.getStock() > 0).collect(Collectors.toList());

        do {
            if (products.isEmpty()) {
                System.err.println("Không có sản phẩm nào để chọn, danh sách sản phẩm trống");
                return null;
            }
            System.out.println("\u001B[34m =============== DANH SÁCH SẢN PHẨM ================\u001B[0m");
            products.forEach(product -> System.out.printf(" | %-5d | %-22s | %-18d |\n", product.getPro_id(), product.getName(), product.getStock()));
            System.out.println("\u001B[34m ===================================================\u001B[0m");
            int choiceProductId = Validator.validateInputInteger(scanner, "Chọn sản phẩm: ");

            Optional<Product> selectedProduct = products.stream().filter(p -> p.getPro_id() == choiceProductId).findFirst();
            if (selectedProduct.isPresent()) {
                return selectedProduct.get();
            } else {
                System.out.println("Vui lòng chọn đúng sản phẩm");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf(" | %-14d | %-15d | %-16d | %-18.2f | %-20.2f |\n", getInvoice_id(), product.getPro_id(), quantity, unit_price, quantity * unit_price);
    }
}
