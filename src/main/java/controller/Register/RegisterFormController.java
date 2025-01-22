package controller.Register;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {


    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

//        String key = "#5541Asd";
//
//        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
//
//        basicTextEncryptor.setPassword(key);
//
//
//        String SQL = "INSERT INTO users (username,email,password) VALUES(?,?,?)";
//
//        if (txtPassword.getText().equals(txtConfirmPassword.getText())){
//
//            Connection connection = DBConnection.getInstance().getConnection();
//
//            ResultSet resultSet = connection
//                    .createStatement()
//                    .executeQuery("SELECT * FROM users WHERE email=" + "'" + txtEmail.getText() + "'");
//
//            if (!resultSet.next()){
//                User user = new User(
//                        txtUserName.getText(),
//                        txtEmail.getText(),
//                        txtPassword.getText()
//                );
//
//                PreparedStatement psTm = connection.prepareStatement(SQL);
//                psTm.setString(1,user.getUsername());
//                psTm.setString(2,user.getEmail());
//                psTm.setString(3,basicTextEncryptor.encrypt(user.getPassword()));
//                psTm.executeUpdate();
//
//            }else{
//                new Alert(Alert.AlertType.ERROR,"user found!").show();
//            }
//        }else{
//            new Alert(Alert.AlertType.ERROR,"Check your password!!").show();
//        }

        if (txtUserName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtEmail.getText().isEmpty() || txtConfirmPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incomplete Information");
            alert.setHeaderText("Please fill in all fields to add the User...");
            alert.show();
        } else {
            if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
                try {
                    Connection connection = DBConnection.getInstance().getConnection();
                    ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email="+"'"+txtEmail.getText().toString()+"'");
                    if(!resultSet.next()){
                        PreparedStatement psTm = connection.prepareStatement("INSERT INTO users (username,email,password) VALUES (?,?,?)");
                        psTm.setString(1,txtUserName.getText());
                        psTm.setString(2,txtEmail.getText());
                        psTm.setString(3,txtPassword.getText());
                        psTm.executeUpdate();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("User Added");
                        alert.setHeaderText("User Successfully Added...");
                        alert.show();
                        clearFields();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText("User Already Added...");
                        alert.show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Alert");
                alert.setContentText("There was an issue Password Not Matched..");
                alert.show();

            }
        }
    }

    private void clearFields() {
        txtUserName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
