package re.edu.business.model;

import re.edu.utils.IApp;
import re.edu.validate.ProductValidator;
import re.edu.validate.StringRule;

import java.util.Scanner;

public class Product implements IApp {

    private static int auto_incrementId = 0;
    private int pro_id;
    private String name;
    private String brand;
    private double price;
    private int stock;

    public Product() {
        this.pro_id = ++auto_incrementId;
    }

    public Product(String name, double price, String brand, int stock) {
        this.pro_id = ++auto_incrementId;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.stock = stock;
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

    @Override
    public void inputData(Scanner scanner) {
        this.name = ProductValidator.validateName(scanner, "Nhập vào tên của sản phẩm: ", new StringRule(100));
        this.brand = ProductValidator.validateBrand(scanner, "Nhập vào nhãn hnagf của sản phẩm: ", new StringRule(50));
        this.price = ProductValidator.validatePrice(scanner, "Nhập vào giá bán của sản phẩm: ");
        this.stock = ProductValidator.validateStock(scanner, "Nhập vào số lượng tồn kho của sản phẩm: ");
    }

    @Override
    public String toString() {
        return "Product{" +
                "pro_id=" + pro_id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
