package com.example.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Btn_Order;

    @FXML
    private Button Btn_Products;

    @FXML
    private Button Btn_Category;

    @FXML
    private TextField Tf_ID;

    @FXML
    private TextField Tf_Name;

    @FXML
    private TextField Tf_Price;

    @FXML
    private TextField Tf_Quantity;

    @FXML
    private Button Btn_Add;

    @FXML
    private Button Btn_Update;

    @FXML
    private Button Btn_Delete;

    @FXML
    private TableView<Bien> Table_produit;

    @FXML
    private TableColumn<Bien,String> ID2;

    @FXML
    private TableColumn<Bien,String> Name2;

    @FXML
    private TableColumn<Bien, Float> Price2;

    @FXML
    private TableColumn<Bien,Integer>QTY2;


    private Stage stage;
    private Scene scene;


    ObservableList<Bien> list_produit = FXCollections.observableArrayList(main.lst_principale);


    String IDD,name;
    int qty;
    float price;


    @FXML
    private void Add_produit(ActionEvent event){
        try {

            IDD = Tf_ID.getText();
            name = Tf_Name.getText();
            price = Float.parseFloat(Tf_Price.getText());
            qty = Integer.parseInt(Tf_Quantity.getText());

            Bien b = new Bien();
            b = Shop.searchBienById(IDD, main.lst_principale);

            if(b!=null) {
                Shop.Alert("Le produit est deja existe dans le stock");
            }
            else{
                Bien c = new Bien();
                c.id = IDD;
                c.qty = qty;
                c.name = name;
                c.price = price;
                Shop.addBien(main.lst_principale,c);
            }

            switchTomyscene(event);

        } catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }
    }

    @FXML
    private void Update_produit(ActionEvent event){
        try {
            if(Tf_ID.getText()=="" ){
                IDD = Table_produit.getSelectionModel().getSelectedItem().id;
            }
            else{
                IDD = Tf_ID.getText();
            }
            Bien b = new Bien();
            b = Shop.searchBienById(IDD, main.lst_principale);

            if(Tf_Price.getText()==""){
                price = b.price;
            }
            else{
                price = Float.parseFloat(Tf_Price.getText());
            }
            if(Tf_Quantity.getText()=="") {
                qty = 0;
            }
            else{
                qty = Integer.parseInt(Tf_Quantity.getText());
            }


            if(b!=null) {
                b.qty += qty;
                b.price = price;
            }
            else{
                Shop.Alert("Le produit n'existe pas dans le stock");
            }

            switchTomyscene(event);

        } catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }
    }

    @FXML
    private void Delete_produit(ActionEvent event){
        try {

            if(Tf_ID.getText()==""){
                IDD = Table_produit.getSelectionModel().getSelectedItem().id;
            }
            else{
                IDD = Tf_ID.getText();
            }

            Bien b = new Bien();
            b = Shop.searchBienById(IDD, main.lst_principale);

            if(b!=null) {
                main.lst_principale.remove(b);
            }
            else{
                Shop.Alert("Le produit n'existe pas");
            }

            switchTomyscene(event);

        } catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }
    }



    public void SwitchToOrder(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTomyscene(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SwitchToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID2.setCellValueFactory(new PropertyValueFactory<Bien,String>("id"));
        Name2.setCellValueFactory(new PropertyValueFactory<Bien,String>("name"));
        Price2.setCellValueFactory(new PropertyValueFactory<Bien,Float>("price"));
        QTY2.setCellValueFactory(new PropertyValueFactory<Bien,Integer>("qty"));
        Table_produit.setItems(list_produit);

    }
}
