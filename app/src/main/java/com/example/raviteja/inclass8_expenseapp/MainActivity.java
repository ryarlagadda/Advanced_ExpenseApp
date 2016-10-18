package com.example.raviteja.inclass8_expenseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;


/*
MainActivity.java
Ravi Teja Yarlagadda
 */


public class MainActivity extends AppCompatActivity implements ExpenseApp.OnFragmentInteractionListener,AddExpense.OnFragmentInteractionListener,ShowExpense.OnFragmentInteractionListener{

    ArrayList<Expense> expenses = new ArrayList<Expense>();
    Expense showExpense=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Expense App");
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.container,new ExpenseApp(), "ExpenseApp").commit();
        if(expenses==null){
            ExpenseApp expenseApp = (ExpenseApp) getSupportFragmentManager().findFragmentByTag("ExpenseApp");
            expenseApp.setNoexpense();
        }
    }
public ArrayList<Expense> getAllExpenses(){
    return expenses;
}
    @Override
    public void gotoAddExpenseFragment()  {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new AddExpense(),"AddExpense").addToBackStack(null).commit();
    }

    public Expense getShowExpense(){
        return showExpense;
    }
    @Override
    public void gotoDetailExpenseFragment(Expense expense) {
        showExpense=expense;
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ShowExpense(),"ShowExpense").addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void addedExpenses(Expense expense) {
            expenses.add(expense);
        Log.d("demo",expenses.get(0).toString());
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            Log.d("count","coun>0");
            getSupportFragmentManager().popBackStack();
        }
        else {
            Log.d("count","not greater");
            super.onBackPressed();
        }
    }

    @Override
    public void cancelExpenses() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void returnfromShow() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
