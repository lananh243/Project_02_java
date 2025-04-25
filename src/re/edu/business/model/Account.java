package re.edu.business.model;

import re.edu.utils.IApp;
import re.edu.validate.AccountValidator;
import re.edu.validate.StringRule;

import java.util.Scanner;

public class Account implements IApp {
    private int acc_id;
    private String username;
    private String password;

    public Account() {
    }

    public Account(int acc_id, String username, String password) {
        this.acc_id = acc_id;
        this.username = username;
        this.password = password;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public void inputData(Scanner scanner) {
        this.username = AccountValidator.validateUsername(scanner, "Nhập vào tên người dùng: ", new StringRule(50));
        this.password = AccountValidator.validatePassword(scanner, "Nhập vào mật khẩu: ", new StringRule(255));
    }

    @Override
    public String toString() {
        return "Tên người dùng: " +this.username+ " - Mật khẩu: " + this.password;
    }
}
