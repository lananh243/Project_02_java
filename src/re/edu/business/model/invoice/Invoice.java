package re.edu.business.model.invoice;

import re.edu.business.model.customer.Customer;
import re.edu.business.service.customer.CustomerService;
import re.edu.business.service.customer.CustomerServiceImp;
import re.edu.utils.IApp;
import re.edu.validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Invoice implements IApp {
    private int invoice_id;
    private Customer customer;
    private LocalDate created_at;
    private double total_amount;
    private String invoice_status;

    public Invoice() {
        this.invoice_status = "COMPLETE";
    }

    public Invoice(int invoice_id, Customer customer, LocalDate created_at, double total_amount, String invoice_status) {
        this.invoice_id = invoice_id;
        this.customer = customer;
        this.created_at = created_at;
        this.total_amount = total_amount;
        this.invoice_status = invoice_status;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.customer = choiceCustomer(scanner);
        this.created_at = LocalDate.now();
        this.invoice_status = "COMPLETE";
    }

    private Customer choiceCustomer(Scanner scanner) {
        CustomerService customerService = new CustomerServiceImp();
        List<Customer> customers = customerService.findAll();

        do {
            List<Customer> activeCustomers = customers.stream()
                    .filter(customer -> customer.isStatus() == true)
                    .collect(Collectors.toList());

            if (activeCustomers.isEmpty()) {
                System.err.println("Không có khách hàng nào có trạng thái active để chọn");
                return null;
            }
            System.out.println("\u001B[34m =============== DANH SÁCH KHÁCH HÀNG ================\u001B[0m");
            activeCustomers.forEach(customer -> System.out.printf(" | %-5d | %-20s | %-18s | \n", customer.getCus_id(), customer.getName(), customer.getPhone()));
            System.out.println("\u001B[34m =====================================================\u001B[0m");
            int choiceCustomerId = Validator.validateInputInteger(scanner, "Chọn khách hàng : ");

            Optional<Customer> selectedCustomer = customers.stream().filter(customer -> customer.getCus_id() == choiceCustomerId).findFirst();

            if (selectedCustomer.isPresent()) {
                return selectedCustomer.get();
            } else {
                System.err.println("Vui lòng chọn đúng khách hàng");
            }
        } while (true);
    }

   public void displayData() {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       String createdDateStr = created_at != null ? created_at.format(formatter) : "N/A";
       System.out.printf(" | %-10d | %-17d | %-22s | %-16.2f | %-18s |\n", invoice_id, customer.getCus_id(), createdDateStr, total_amount, invoice_status);
   }
}

