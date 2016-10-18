package com.example.raviteja.inclass8_expenseapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/*
ExpenseAdapter.java
Ravi Teja Yarlagadda
 */


/**
 * Created by RAVITEJA on 10/17/2016.
 */
public class ExpenseAdapter extends ArrayAdapter<Expense> {

    List<Expense> expenseList;
    Context mContext;
    int mResource;
    ImageView imageView;
    int flag=0;


    public ExpenseAdapter(Context context, int resource, List<Expense> objects) {
        super(context, resource,objects);
        this.mContext=context;
        this.mResource=resource;
        this.expenseList=objects;
        Log.d("inside","inside adap");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("demo","inside adap getview");
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
        Expense expense = expenseList.get(position);
        Log.d("expense in adapter",expense.toString());
        TextView textView_expensename = (TextView) convertView.findViewById(R.id.expenseName_rowlayout);
        TextView textView_amount = (TextView) convertView.findViewById(R.id.amount_rowlayout);
        textView_expensename.setText(expense.getName());
        textView_amount.setText(String.valueOf(expense.getAmount()));
        Log.d("expense in adapter",expense.getName());
        Log.d("expense in adapter",String.valueOf(expense.getAmount()));
        return convertView;
    }
}
