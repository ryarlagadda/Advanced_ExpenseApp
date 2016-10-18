package com.example.raviteja.inclass8_expenseapp;

import java.io.Serializable;


/*
Expense.java
Ravi Teja Yarlagadda
 */



/**
 * Created by RAVITEJA on 10/17/2016.
 */
public class Expense implements Serializable {
    String name;
     String category;
    double amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
