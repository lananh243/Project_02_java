package re.edu.business.model;

import re.edu.utils.IApp;
import re.edu.validate.CustomerValidator;
import re.edu.validate.StringRule;

import java.util.Scanner;

public class Customer implements IApp {
    private static int auto_incrementId = 0;
    private int cus_id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer() {
        this.cus_id = ++auto_incrementId;
    }

    public Customer(String name, String phone, String email, String address) {
        this.cus_id = ++auto_incrementId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getCus_id() {
        return cus_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.name = CustomerValidator.validateName(scanner, "Nhập vào họ tên của khách hàng: ", new StringRule(100));
        this.phone = CustomerValidator.validatePhone(scanner, "Nhập vào số điện thoại của khách hàng: ", new StringRule(20));
        this.email = CustomerValidator.validateEmail(scanner, "Nhập vào địa chỉ email: ", new StringRule(100));
        this.address = CustomerValidator.validateAddress(scanner, "Nhập vào địa chỉ: ", new StringRule(255));
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cus_id=" + cus_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
