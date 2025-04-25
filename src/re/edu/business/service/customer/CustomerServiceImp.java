package re.edu.business.service.customer;

import re.edu.business.dao.customer.CustomerDao;
import re.edu.business.dao.customer.CustomerDaoImp;
import re.edu.business.model.customer.Customer;

import java.util.List;

public class CustomerServiceImp implements CustomerService {
    private final CustomerDao customerDao;

    public CustomerServiceImp() {
        customerDao = new CustomerDaoImp();
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        return customerDao.addNewCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerDao.findCustomerById(id);
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        return customerDao.deleteCustomer(customer);
    }

    @Override
    public boolean isPhoneDuplicate(String phone) {
        return customerDao.isPhoneDuplicate(phone);
    }

    @Override
    public boolean isEmailDuplicate(String email) {
        return customerDao.isEmailDuplicate(email);
    }

    @Override
    public List<Customer> searchCustomerByName(String name) {
        return customerDao.searchCustomerByName(name);
    }

    @Override
    public List<Customer> searchCustomerByEmail(String email) {
        return customerDao.searchCustomerByEmail(email);
    }
}
