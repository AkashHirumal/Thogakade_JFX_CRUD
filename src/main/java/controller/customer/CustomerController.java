package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.*;
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

    public ObservableList<String> getCustomerIds(){
        ObservableList<String> custIdlist = FXCollections.observableArrayList();
        getAll().forEach(customer -> {
            custIdlist.add(customer.getId());
        });
        return custIdlist;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        PreparedStatement psTm = null;
        try {

            psTm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");

            psTm.setString(1,customer.getId());
            psTm.setString(2,customer.getName());
            psTm.setString(3,customer.getAddress());
            psTm.setDouble(4,customer.getSalary());
            return psTm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        PreparedStatement statement = null;
        try {
            statement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE customer SET name=?,address=?,salary=? WHERE id=?");
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setDouble(3, customer.getSalary());
            statement.setString(4, customer.getId());
            return statement.executeUpdate() >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException {
        try {
            return DBConnection.getInstance().getConnection().createStatement().executeUpdate("DELETE FROM customer WHERE id = '" + customerId + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomer(String customerId) {
        try {
            ResultSet res = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM customer WHERE id = '" + customerId + "'");
            res.next();
            return new Customer(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    Double.parseDouble(res.getString(4))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
