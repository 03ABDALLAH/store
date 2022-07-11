package com.example.Store;

import java.util.Scanner;

public class Bien {
    Scanner input = new Scanner(System.in);
    public String id;
    public String name;
    public float price;
    public int qty;

    public Bien(String id, String name, float price, int qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
    public Bien(){
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public Scanner getInput() {
        return input;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }
}
