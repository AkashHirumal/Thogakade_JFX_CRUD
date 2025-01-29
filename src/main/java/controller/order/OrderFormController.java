package controller.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.CartTM;
import model.Customer;
import model.Item;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox cmbItemCode;
    public JFXComboBox cmbCustomerIds;
    public JFXTextField txtDescription;
    public JFXTextField txtStock;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Label lblNetTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadDateAndTime();
        loadCustomerIds();
        loadItemCodes();

        cmbCustomerIds.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, oldValue, newValue) -> {

                    if (newValue != null) {
                        setCustomerDataToText((String) newValue);
                        System.out.println(newValue);
                    }

                });
        cmbItemCode.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        setItemDataToText(newValue.toString());
                    }
                });


    }

    private void setItemDataToText(String code) {
        Item item = new ItemController().searchItem(code);
        txtDescription.setText(item.getDescription());
        txtStock.setText(item.getQty().toString());
        txtUnitPrice.setText(item.getUnitPrice().toString());
    }

    private void loadItemCodes() {
        cmbItemCode.setItems(new ItemController().getItemCodes());
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void loadCustomerIds() {
        cmbCustomerIds.setItems(CustomerController.getInstance().getCustomerIds());
    }

    private void setCustomerDataToText(String id) {
        Customer customer = CustomerController.getInstance().searchCustomer(id);
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(customer.getSalary().toString());

    }

    ObservableList<CartTM> cartTMObservableList = FXCollections.observableArrayList();

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        Double total = Double.parseDouble(txtUnitPrice.getText()) * Integer.parseInt(txtQty.getText());
        cartTMObservableList.add(
                new CartTM(
                        cmbItemCode.getValue().toString(),
                        txtDescription.getText(),
                        Integer.parseInt(txtQty.getText()),
                        Double.parseDouble(txtUnitPrice.getText()),
                        total
                )
        );

        tblCart.setItems(cartTMObservableList);
        calcNetTotal();
    }

    private void calcNetTotal(){
        Double netTotal=0.0;
        for (CartTM cartTM: cartTMObservableList){
            netTotal+=cartTM.getTotal();
        }
        lblNetTotal.setText(netTotal.toString());
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }
}