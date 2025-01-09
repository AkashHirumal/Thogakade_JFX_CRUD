package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;


public class MenuFromController {

    @FXML
    private AnchorPane loadFormContent;


    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        URL resource =this.getClass().getResource("/view/customerformview.fxml");


        assert  resource != null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);


    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        URL resource =this.getClass().getResource("/view/itemformview.fxml");

        assert  resource != null;

        Parent load = FXMLLoader.load(resource);
        this.loadFormContent.getChildren().clear();
        this.loadFormContent.getChildren().add(load);
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) {
    }
}
