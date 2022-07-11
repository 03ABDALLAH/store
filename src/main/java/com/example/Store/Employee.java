package com.example.Store;

public class Employee extends Person{

    public Float Salary;

    public Employee(String name, String Phone, String User, String Pass){
        this.FullName = name;
        this.PhNum = Phone;
        this.UserName = User;
        this.Password = Pass;
    }
    public Employee(){

    }
}
