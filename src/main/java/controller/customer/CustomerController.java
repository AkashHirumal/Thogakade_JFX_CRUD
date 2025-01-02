package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import model.Customer;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerController implements CustomerService{

    private static CustomerController instance;

    private CustomerController(){

    }
    public static CustomerController getInstance(){
        return instance==null?instance=new CustomerController():instance;
    }


    @Override
    public List<Customer> getAll() {
        List <Customer> customerList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                customerList.add(
                        new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4)
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        return false;
    }

    @Override
    public Customer searchCustomer(String customerId) {
        return null;
    }

    public  List<String> getCustomerIds(){
        List<Customer> allCustomers = getAll();
        List<String> idList = FXCollections.observableArrayList();

        allCustomers.forEach(customer -> {
            idList.add(customer.getId());
        });
        return idList;
    }
}
