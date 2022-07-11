package com.example.Store;

import com.pdfjet.Cell;
import com.pdfjet.*;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

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
    private TextField Tf_Quantity;

    @FXML
    private Button Btn_Add;

    @FXML
    private Button Btn_Print;

    @FXML
    private Button Btn_Delete;

    @FXML
    private Label lbl_total;

    @FXML
    private TableView<Bien> TableOrder;

    @FXML
    private TableColumn<Bien,String> ID;

    @FXML
    private TableColumn<Bien,String> Name;

    @FXML
    private TableColumn<Bien, Float> Price;

    @FXML
    private TableColumn<Bien,Integer>QTY;


    @FXML
    private TableView<Bien> TableStock;

    @FXML
    private TableColumn<Bien,String> ID1;

    @FXML
    private TableColumn<Bien,String> Name1;

    @FXML
    private TableColumn<Bien, Float> Price1;

    @FXML
    private TableColumn<Bien,Integer>QTY1;


    private Stage stage;
    private Scene scene;
    private Parent parent;


    ObservableList<Bien> list = FXCollections.observableArrayList(main.lst);
    ObservableList<Bien> list_stock = FXCollections.observableArrayList(main.lst_principale);

    String IDD,name;
    int qty;
    float price,p=0;





    @FXML
    private void Add(ActionEvent event){
        try {

            if(Tf_ID.getText()==""){
                IDD = TableStock.getSelectionModel().getSelectedItem().id;
            }
            else{
                IDD = Tf_ID.getText();
            }


            qty = Integer.parseInt(Tf_Quantity.getText());

            Bien b = new Bien();
            b = Shop.searchBienById(IDD, main.lst_principale);
            Bien t = new Bien();
            t = Shop.searchBienById(b.id,main.lst);

            if(b!=null) {
                if (b.qty < qty) {
                    Shop.Alert("La quantite de stock n'est pas suffisant");
                } else if (b.qty > qty) {
                    /*Bien t = new Bien();
                    t = Shop.searchBienById(b.id,main.lst);*/
                    if(t!=null) {
                        b.qty -= qty;
                        t.qty += qty;
                    }
                    else{

                        b.qty -= qty;
                        Bien c = new Bien();
                        c.id = IDD;
                        c.qty = qty;
                        c.price = b.price;
                        c.name = b.name;
                        Shop.addBien(main.lst, c);
                    }
                }
                else{
                    /*Bien t = new Bien();
                    t = Shop.searchBienById(b.id,main.lst);*/
                    if(t!=null) {
                        t.qty += qty;
                    }
                    else{
                        Bien c = new Bien();
                        c.id = IDD;
                        c.qty = qty;
                        c.price = b.price;
                        c.name = b.name;
                        Shop.addBien(main.lst, c);
                    }
                    main.lst_principale.remove(b);
                }
            }
            else{
                Shop.Alert("Le produit n'existe pas");
            }


            for (Bien i : main.lst){
                p += (i.price*i.qty);
                }


            switchTomyscene(event);



        } catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }
    }





    @FXML
    private void Delete(ActionEvent event){
        try {
            if(Tf_ID.getText()==""){
                IDD = TableOrder.getSelectionModel().getSelectedItem().id;
            }
            else{
                IDD = Tf_ID.getText();
            }
            Bien b = new Bien();
            b = Shop.searchBienById(IDD, main.lst);
            if(Tf_Quantity.getText() == ""){
                qty =b.qty ;
            }else{
                qty = Integer.parseInt(Tf_Quantity.getText());
            }


            Bien c = new Bien();
            c= Shop.searchBienById(IDD,main.lst_principale);

            if(b!=null) {
                if (b.qty <= qty) {
                    if(c!=null){
                        c.qty += b.qty;
                    }
                    else {
                        main.lst_principale.add(b);
                    }
                    main.lst.remove(b);
                } else if (b.qty > qty) {
                    if(c!=null){
                        c.qty += qty;
                    }
                    else{
                        Bien d = new Bien();
                        d.qty=qty;
                        d.id=b.id;
                        d.price=b.price;
                        d.name=b.name;
                        main.lst_principale.add(d);
                    }
                    b.qty -= qty;
                }
            }
            else{
                Shop.Alert("Le produit n'existe pas");
            }
            for (Bien i : main.lst){
                p += (i.price*i.qty);
            }
            switchTomyscene(event);

        } catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR");
        }
    }


    @FXML
    private void print(ActionEvent event){
        try {
            Btn_Print.setOnAction(actionEvent -> {
//				we will create the pdf and page
                File out = new File("Sample.pdf");
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(out);
                    PDF pdf = new PDF(fos);
                    Page page = new Page(pdf, A4.PORTRAIT);

//					font for the table heading
                    Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD);

//					font for the pdf table data
                    Font f2 = new Font(pdf, CoreFont.HELVETICA);

//					pdf table
                    Table table = new Table();
                    List<List<com.pdfjet.Cell>> tableData = new ArrayList<List<com.pdfjet.Cell>>();

//					table row
                    List<com.pdfjet.Cell> tableRow = new ArrayList<com.pdfjet.Cell>();

//					let's create the headers and add them to the table row
                    com.pdfjet.Cell cell = new com.pdfjet.Cell(f1, ID1.getText());
                    tableRow.add(cell);

                    cell = new com.pdfjet.Cell(f1, Name1.getText());
                    tableRow.add(cell);

                    cell = new com.pdfjet.Cell(f1, Price1.getText());
                    tableRow.add(cell);

                    cell = new com.pdfjet.Cell(f1, QTY1.getText());
                    tableRow.add(cell);

                    //add row to table
                    tableData.add(tableRow);

//					now let's get table view data, create a row for each and add row to table row
                    List<Bien> items = TableOrder.getItems();

                    for (Bien b : items) {
                        com.pdfjet.Cell id = new com.pdfjet.Cell(f2, b.getId());
                        com.pdfjet.Cell name = new com.pdfjet.Cell(f2, b.name);
                        com.pdfjet.Cell price = new com.pdfjet.Cell(f2, b.price+"");
                        com.pdfjet.Cell qty = new com.pdfjet.Cell(f2, b.qty+"");

                        tableRow = new ArrayList<Cell>();
                        tableRow.add(id);
                        tableRow.add(name);
                        tableRow.add(price);
                        tableRow.add(qty);

//						add row to table
                        tableData.add(tableRow);
                    }

                    table.setData(tableData);
                    table.setPosition(70f, 60f);
                    table.setColumnWidth(0, 100f);
                    table.setColumnWidth(1, 100f);
                    table.setColumnWidth(2, 140f);
                    table.setColumnWidth(3, 140f);

//					create a new page and add more table data if we get to the bottom of the current page
                    while (true) {
                        table.drawOn(page);
                        if (!table.hasMoreData()) {
                            table.resetRenderedPagesCount();
                            break;
                        }
                        page = new Page(pdf, A4.PORTRAIT);
                    }

                    pdf.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                System.out.println("Saved to " + out.getAbsolutePath());
            });

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    public void SwitchToProducts(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Products.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchTomyscene(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader(OrderController.class.getResource(("Order.fxml")));
            Parent root = fxmlLoader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            OrderController orderController=fxmlLoader.getController();
            orderController.lbl_total.setText(p+ "");
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
    public void initialize(URL url, ResourceBundle resourceBundle ) {
        ID.setCellValueFactory(new PropertyValueFactory<Bien,String>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<Bien,String>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<Bien,Float>("price"));
        QTY.setCellValueFactory(new PropertyValueFactory<Bien,Integer>("qty"));
        TableStock.setItems(list_stock);

        ID1.setCellValueFactory(new PropertyValueFactory<Bien,String>("id"));
        Name1.setCellValueFactory(new PropertyValueFactory<Bien,String>("name"));
        Price1.setCellValueFactory(new PropertyValueFactory<Bien,Float>("price"));
        QTY1.setCellValueFactory(new PropertyValueFactory<Bien,Integer>("qty"));
        TableOrder.setItems(list);
    }



    }

