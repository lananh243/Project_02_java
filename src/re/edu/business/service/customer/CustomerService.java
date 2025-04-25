package re.edu.business.service.customer;

import re.edu.business.model.customer.Customer;
import re.edu.business.service.AppService;

import java.util.List;

public interface CustomerService extends AppService<Customer> {
    List<Customer> findAll();
    boolean addNewCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    Customer findCustomerById(int id);
    boolean deleteCustomer(Customer customer);
    boolean isPhoneDuplicate(String phone);
    boolean isEmailDuplicate(String email);
    List<Customer> searchCustomerByName (String name);
    List<Customer> searchCustomerByEmail (String email);
}
