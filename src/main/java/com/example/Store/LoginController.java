package com.example.Store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField Tf_User;

    @FXML
    private PasswordField Tf_Pass;

    @FXML
    private Button Btn_Login;

    @FXML
    private Button Btn_Register;



    private Stage stage;
    private Scene scene;

    public void SwitchToOrder(ActionEvent e) {
        try {
            if (Shop.searchEmp(Tf_User.getText(), Tf_Pass.getText(), main.Emp) == null) {
                Alert("Username or Password Incorrect");
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void SwitchToRegister(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static void Alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
