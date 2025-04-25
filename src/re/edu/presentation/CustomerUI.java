package re.edu.presentation;

import re.edu.business.model.customer.Customer;
import re.edu.business.model.customer.Gender;
import re.edu.business.service.customer.CustomerService;
import re.edu.business.service.customer.CustomerServiceImp;
import re.edu.validate.CustomerValidator;
import re.edu.validate.StringRule;
import re.edu.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    private final CustomerService customerService;

    public CustomerUI() {
        customerService = new CustomerServiceImp();
    }

    public static void displayCustomerMenu(Scanner scanner) {
        CustomerUI customerUI = new CustomerUI();
        do {
            System.out.println("\u001B[34m ======================= QUẢN LÝ KHÁCH HÀNG =====================\u001B[0m");
            System.out.println("\u001B[33m |   1. Hiển thị danh sách khách hàng                           |\u001B[0m");
            System.out.println("\u001B[33m |   2. Thêm mới khách hàng                                     |\u001B[0m");
            System.out.println("\u001B[33m |   3. Cập nhật thông tin khách hàng                           |\u001B[0m");
            System.out.println("\u001B[33m |   4. Xóa khách hàng theo ID                                  |\u001B[0m");
            System.out.println("\u001B[33m |   5. Tìm kiếm                                                |\u001B[0m");
            System.out.println("\u001B[33m |   6. Quay lại menu chính                                     |\u001B[0m");
            System.out.println("\u001B[34m ================================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    customerUI.displayListCustomer();
                    break;
                case 2:
                    customerUI.addCustomer(scanner);
                    break;
                case 3:
                    customerUI.updateCustomer(scanner);
                    break;
                case 4:
                    customerUI.deleteCustomer(scanner);
                    break;
                case 5:
                    customerUI.displaySearchMenu(scanner);
                case 6:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }

        } while (true);
    }

    public void displayListCustomer() {
        List<Customer> listCustomers = customerService.findAll();
        if (listCustomers.isEmpty()) {
            System.err.println("Danh sách khách hàng vẫn chưa có khách hàng");
        } else {
            System.out.println("\u001B[34m =================================================================== DANH SÁCH KHÁCH HÀNG ====================================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Khách Hàng  |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
            listCustomers.forEach(Customer::displayData);
            System.out.println("\u001B[34m =============================================================================================================================================================\u001B[0m");
        }
    }

    public void addCustomer(Scanner scanner) {
        int size = Validator.validateInputInteger(scanner, "Nhập vào số lượng khách hàng cần thêm mới: ");
        boolean checkAdd = true;
        List<Customer> listAddCustomers = new ArrayList<>();
        for (int i  = 0; i < size; i++) {
            System.out.println("==== Nhập thông tin khách hàng thứ " +(i+1)+ " ====");
            Customer customer = new Customer();
            customer.inputData(scanner);

            if (customerService.addNewCustomer(customer)) {
                listAddCustomers.add(customer);
            } else {
                checkAdd = false;
            }
        }

        if (checkAdd && !listAddCustomers.isEmpty()) {
            System.out.println("\u001B[34m ======================================================= THÊM MỚI KHÁCH HÀNG THÀNH CÔNG ====================================================\u001B[0m");
            System.out.println("\u001B[33m |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
            listAddCustomers.forEach(customer -> System.out.printf(" | %-29s | %-19s | %-19s | %-19s | %-16s| %-20s|\n",
                    customer.getName(), customer.getPhone(), customer.getEmail(), customer.getAddress(), customer.getGender(), (customer.isStatus() ? "Hoạt động" : "Không hoạt động")));
            System.out.println("\u001B[34m ===========================================================================================================================================\u001B[0m");
        } else {
            System.err.println("Có lỗi sảy ra khi thêm khách hàng");
        }
    }

    public void updateCustomer(Scanner scanner) {
        int cus_id = Validator.validateInputInteger(scanner, "Nhập vào mã khách hàng cần cập nhật: ");
        if (customerService.findCustomerById(cus_id) != null) {
            Customer customer = customerService.findCustomerById(cus_id);
            System.out.println("\u001B[34m ============================================================= THÔNG TIN KHÁCH HÀNG CÓ ID : " +cus_id+ " ===========================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Khách Hàng  |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
            customer.displayData();
            System.out.println("\u001B[34m =============================================================================================================================================================\u001B[0m");

            do {
                System.out.println("\u001B[34m ================== Chọn thông tin khách hàng bạn muốn cập nhật ================\u001B[0m");
                System.out.println("\u001B[33m |    1. Cập nhật họ tên khách hàng                                            |\u001B[0m ");
                System.out.println("\u001B[33m |    2. Cập nhật số điện thoại của khách hàng                                 |\u001B[0m");
                System.out.println("\u001B[33m |    3. Cập nhật email của khách hàng                                         |\u001B[0m");
                System.out.println("\u001B[33m |    4. Cập nhật địa chỉ của khách hàng                                       |\u001B[0m");
                System.out.println("\u001B[33m |    5. Cập nhật giới tính của khách hàng                                     |\u001B[0m");
                System.out.println("\u001B[33m |    6. Cập nhật trạng thái của khách hàng                                    |\u001B[0m");
                System.out.println("\u001B[33m |    7. Thoát                                                                 |\u001B[0m");
                System.out.println("\u001B[34m ===============================================================================\u001B[0m");
                int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
                switch (choice) {
                    case 1:
                        String CustomerName = CustomerValidator.validateName(scanner, "Nhập vào tên khách hàng mới: ", new StringRule(100));
                        customer.setName(CustomerName);
                        break;
                    case 2:
                        String phoneNumber = CustomerValidator.validatePhone(scanner, "Nhập vào số điện thoại mới:  ", new StringRule(20));
                        customer.setPhone(phoneNumber);
                        break;
                    case 3:
                        String email = CustomerValidator.validateEmail(scanner, "Nhập vào email mới: ", new StringRule(100));
                        customer.setEmail(email);
                        break;
                    case 4:
                        String address = CustomerValidator.validateAddress(scanner, "Nhập vào địa chỉ mới: ", new StringRule(225));
                        customer.setAddress(address);
                        break;
                    case 5:
                        Gender gender = Validator.validateEnumInput(scanner, "Nhập vào giới tính mới: ", Gender.class);
                        customer.setGender(gender);
                        break;
                    case 6:
                        boolean status = Validator.validateInputBoolean(scanner, "Nhập vào trạng thái mới của khách hàng (true | fasle): ");
                        customer.setStatus(status);
                        break;
                    case 7:
                        return;
                    default:
                        System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
                }

                boolean result = customerService.updateCustomer(customer);
                if (result) {
                    System.out.println("\u001B[34m =================================================== CẬP NHẬT THÀNH CÔNG THÔNG TIN CHO KHÁCH HÀNG CÓ ID : " +cus_id+ " ===============================================\u001B[0m");
                    System.out.println("\u001B[33m |  Mã Khách Hàng  |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
                    customer.displayData();
                    System.out.println("\u001B[34m =============================================================================================================================================================\u001B[0m");
                } else {
                    System.err.println("Có lỗi trong quá trình cập nhật khách hàng");
                }
            }while (true);
        } else {
            System.err.println("Không tồn tại mã khách hàng: "+cus_id);
        }
    }

    public void deleteCustomer(Scanner scanner) {
        int cus_id = Validator.validateInputInteger(scanner, "Nhập vào mã sản phẩm cần xóa: ");
        if (customerService.findCustomerById(cus_id) != null) {
            Customer customer = customerService.findCustomerById(cus_id);

            if (customer.isStatus() == false) {
                System.err.println("Khách hàng có Id = " + cus_id+ " đã bị xóa");
                return;
            }

            System.out.println("\u001B[34m =========================================================== THÔNG TIN KHÁCH HÀNG CÓ ID : " +cus_id+ " =======================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Khách Hàng  |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
            customer.displayData();
            System.out.println("\u001B[34m =============================================================================================================================================================\u001B[0m");

            while (true) {
                System.out.println("Bạn có chắc chắn muốn xóa khách hàng có ID = " +cus_id+" không ?");
                String input  = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("y")) {
                    boolean result = customerService.deleteCustomer(customer);
                    if (result) {
                        System.out.println("Xóa thành công khách hàng có ID = " +cus_id);
                        List<Customer> listCustomers = customerService.findAll();
                        System.out.println("\u001B[34m ========================================================== DANH SÁCH KHÁCH HÀNG SAU KHI XÓA ID = " +cus_id+ " ===========================================================\u001B[0m");
                        System.out.println("\u001B[33m |  Mã Khách Hàng  |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
                        listCustomers.forEach(Customer::displayData);
                        System.out.println("\u001B[34m =============================================================================================================================================================\u001B[0m");
                        break;
                    } else {
                        System.err.println("Có lỗi trong quá trình xóa khách hàng");
                        break;
                    }
                } else if (input.equalsIgnoreCase("n")) {
                    System.out.println("Hủy xóa sản phẩm");
                    break;
                } else {
                    System.err.println("Vui lòng nhập 'y' hoặc 'n'.");
                    break;
                }
            }
        } else {
            System.err.println("Không tồn tại khách hàng có ID:  "+cus_id);
        }
    }

    public void displaySearchMenu(Scanner scanner) {
        do {
            System.out.println("\u001B[34m ==================== MENU TÌM KIẾM KHÁCH HÀNG =========================\u001B[0m");
            System.out.println("\u001B[33m |    1. Tìm kiếm khách hàng theo tên                                  |\u001B[0m");
            System.out.println("\u001B[33m |    2. Tìm kiếm khách hàng theo email                                |\u001B[0m");
            System.out.println("\u001B[33m |    3. Thoát                                                         |\u001B[0m");
            System.out.println("\u001B[34m =============================================================\u001B[0m");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    searchCustomerByName(scanner);
                    break;
                case 2:
                    searchCustomerByEmail(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Lựa chọn của bạn không hợp lệ, vui lòng chọn lại");
            }
        } while (true);
    }

    public void searchCustomerByName(Scanner scanner) {
        String cus_name = CustomerValidator.validateName(scanner, "Nhập vào tên khách hàng cần tìm: ", new StringRule(100));
        List<Customer> result = customerService.searchCustomerByName(cus_name);

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy khách hàng nào có tên: " + cus_name);
        } else {
            System.out.println("\u001B[34m ========================================================= KẾT QUẢ TÌM KIẾM KHÁCH HÀNG THEO TÊN ==============================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Khách Hàng  |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
            result.forEach(Customer::displayData);
            System.out.println("\u001B[34m =============================================================================================================================================================\u001B[0m");
        }
    }

    public void searchCustomerByEmail (Scanner scanner) {
        String email = CustomerValidator.validateSearchEmail(scanner, "Nhập vào email cần tìm kiếm: ", new StringRule(100));
        List<Customer> result = customerService.searchCustomerByEmail(email);

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy khách hàng nào có email là: " + email);
        } else {
            System.out.println("\u001B[34m ======================================================== KẾT QUẢ TÌM KIẾM KHÁCH HÀNG THEO EMAIL =============================================================\u001B[0m");
            System.out.println("\u001B[33m |  Mã Khách Hàng  |       Họ Tên Khách Hàng       |    Số điện thoại    |        Email        |       Địa Chỉ       |    Giới Tính    |      Trạng Thái     |\u001b[0m");
            result.forEach(Customer::displayData);
            System.out.println("\u001B[34m =============================================================================================================================================================\u001B[0m");
        }
    }
}
