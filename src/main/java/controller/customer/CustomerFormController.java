package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO customer VALUES(?,?,?,?)";
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setString(1,txtId.getText());
            psTm.setString(2,txtName.getText());
            psTm.setString(3,txtAddress.getText());
            psTm.setDouble(4,Double.parseDouble(txtSalary.getText()));
            if(psTm.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Added Customer !!").show();
                loadTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        String SQL = "DELETE FROM customer WHERE id = '"+txtId.getText()+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            boolean isDelete= connection.createStatement().executeUpdate(SQL)>0;
            if(isDelete){
                new Alert(Alert.AlertType.WARNING,"Customer Deleted!!").show();
                loadTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String SQL = "UPDATE customers SET id = '"+txtId.getText()+"' WHERE name='"+txtName.getText()+"' name='"+txtAddress.getText()+"' name='"+txtSalary.getText()+"'   ";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            boolean isUpdate= connection.createStatement().executeUpdate(SQL)>0;
            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Updateed!!").show();
                loadTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewItemFormOnAction(ActionEvent event) {

    }

    private void setTextToValues(Customer customer) {
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    private void loadTable() {


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));


        CustomerController customercontroller=CustomerController.getInstance();
        List<Customer> all = customercontroller.getAll();

        ObservableList<Customer> customerObservableList= FXCollections.observableArrayList();

        all.forEach(customer -> {
            customerObservableList.add(customer);

        });

        tblCustomers.setItems(customerObservableList);

        tblCustomers.setItems(customerObservableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadTable();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newValue) -> {
            if (newValue != null){
                setTextToValues((Customer) newValue);
            }
        });
    }


}





