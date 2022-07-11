package com.example.Store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField Tf_Name;

    @FXML
    private TextField Tf_Phone;

    @FXML
    private TextField Tf_User;

    @FXML
    private PasswordField Tf_Pass;

    @FXML
    private Button Btn_Register;

    @FXML
    private Button Btn_Login;



    public Employee em = new Employee();

    public void Register(ActionEvent ev) {
        try{
            em.FullName=Tf_Name.getText();
            em.PhNum=Tf_Phone.getText();
            em.UserName=Tf_User.getText();
            em.Password=Tf_Pass.getText();
            main.Emp.add(em);

            SwitchToLogin(ev);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }



    private Stage stage;
    private Scene scene;


    public void SwitchToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
