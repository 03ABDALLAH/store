package com.example.Store;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class main extends Application {

    File fileIcon = new File("src/main/resources/com/example/Store/store icon.png");
    Image icon = new Image(fileIcon.toURI().toString());


    public static ArrayList<Employee> Emp = new ArrayList<Employee>();
    public static ArrayList<Bien> lst_principale = new ArrayList<Bien>();
    public static ArrayList<Bien> lst = new ArrayList<Bien>();


    @Override
    public void start(Stage stage) throws IOException {
         Employee em1 = new Employee("anas tnx","0799954575","a","a");
         Employee em2 = new Employee("abdallah ougoummad","0787675645","abdallah","abdallah");
         Emp.add(em1);
         Emp.add(em2);
         Bien b1 = new Bien("Ab3489","apple",5,30);lst_principale.add(b1);


        stage.getIcons().add(icon);
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("Login.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 385, 385);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Your Store!");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}