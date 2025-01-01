package controller.customer;

import model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();

    boolean saveCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(String customerId);

    Customer searchCustomer(String customerId);


}
