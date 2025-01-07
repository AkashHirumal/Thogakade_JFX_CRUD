package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuFromController {
    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customerformview.fxml"))));
        stage.setTitle("Customer Form");
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/itemformview.fxml"))));
        stage.setTitle("Item Form");
        stage.show();
        stage.setResizable(false);
    }
}
