package com.example.Store;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Shop {


    public static  void addBien(ArrayList<Bien> lst, Bien b){
        lst.add(b);
    }


    public static Bien searchBienById(String id, ArrayList<com.example.Store.Bien> lst) {

        Bien b = new Bien();
        for (Bien i : lst){
            b = i;
            if (b.id.equals(id)) {
                return b;
            }
        }
        return null;
    }

    public static void Alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static Employee searchEmp(String User, String Pass,ArrayList<Employee> lst) {
        Employee em = new Employee();
        for (Employee i : lst){
            em = i;
            if (i.UserName.equals(User) && i.Password.equals(Pass)) {
                return em;
            }
        }
        return null;
    }




}

