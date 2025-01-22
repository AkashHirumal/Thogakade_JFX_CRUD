package controller.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, SQLException {
        String key = "#5541Asd";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

        basicTextEncryptor.setPassword(key);

        String SQL = "SELECT * FROM users WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()){
            User user = new User(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );



            if (basicTextEncryptor.decrypt(user.getPassword()).equals(txtPassword.getText())){
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
                stage.show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Check your Password!!").show();
            }
            System.out.println(user);

        }else{
            new Alert(Alert.AlertType.ERROR,"User Not Found!!").show();
        }
    }

    @FXML
    void btnUserRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerform.fxml"))));
        stage.setTitle("Register Form");
        stage.show();
        stage.setResizable(false);
    }

}
