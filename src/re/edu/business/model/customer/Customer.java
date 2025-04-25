package re.edu.business.model.customer;

import re.edu.utils.IApp;
import re.edu.validate.CustomerValidator;
import re.edu.validate.StringRule;
import re.edu.validate.Validator;

import java.util.Scanner;

public class Customer implements IApp {
    private int cus_id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Gender gender;
    private boolean status;

    public Customer() {
        this.status = true;

    }

    public Customer(int cus_id, String name, String phone, String email, String address, Gender gender, boolean status) {
        this.cus_id = cus_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.status = status;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.name = CustomerValidator.validateName(scanner, "Nhập vào họ tên của khách hàng: ", new StringRule(100));
        this.phone = CustomerValidator.validatePhone(scanner, "Nhập vào số điện thoại của khách hàng: ", new StringRule(20));
        this.email = CustomerValidator.validateEmail(scanner, "Nhập vào địa chỉ email: ", new StringRule(100));
        this.address = CustomerValidator.validateAddress(scanner, "Nhập vào địa chỉ: ", new StringRule(255));
        this.gender = Validator.validateEnumInput(scanner, "Nhập vào giới tính của khách hàng ('MALE' | 'FEMALE' | 'OTHER)': ", Gender.class);
        this.status = true;
    }

    public void displayData() {
        System.out.printf(" |  %-14d | %-29s | %-19s | %-19s | %-19s | %-16s| %-20s|\n",cus_id,name,phone,email,address,gender,(this.status ? "Hoạt động" : "Không hoạt động"));
    }
}
