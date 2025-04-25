package re.edu.business.model;

import re.edu.utils.IApp;
import re.edu.validate.ProductValidator;
import re.edu.validate.StringRule;
import re.edu.validate.Validator;

import java.util.Scanner;

public class Product implements IApp {
    private int pro_id;
    private String name;
    private String brand;
    private double price;
    private int stock;
    private boolean status;

    public Product() {
        this.status = true;
    }

    public Product(int pro_id, String name, String brand, double price, int stock, boolean status) {
        this.pro_id = pro_id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.name = ProductValidator.validateName(scanner, "Nhập vào tên của sản phẩm: ", new StringRule(100));
        this.brand = ProductValidator.validateBrand(scanner, "Nhập vào nhãn hàng của sản phẩm: ", new StringRule(50));
        this.price = ProductValidator.validatePrice(scanner, "Nhập vào giá bán của sản phẩm: ");
        this.stock = ProductValidator.validateStock(scanner, "Nhập vào số lượng tồn kho của sản phẩm: ");
        this.status = true;
    }


    public void displayData() {
        System.out.printf(" |  %-12d | %-33s | %-21s | %-15.2f | %-16d | %-16s|\n", pro_id, name, brand, price, stock,(this.status ? "Còn hàng" : "Hết hàng"));
    }
}
