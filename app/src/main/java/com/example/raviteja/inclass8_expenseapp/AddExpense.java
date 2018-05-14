package com.example.raviteja.inclass8_expenseapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/*
AddExpense.java
Ravi Teja Yarlagadda
 */


public class AddExpense extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AddExpense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_expense,container,false);
        final Spinner spinner_catergory = (Spinner) view.findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.catergories,R.layout.support_simple_spinner_dropdown_item);
        spinner_catergory.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Expense");

        final Spinner spinner_catergory = (Spinner) getActivity().findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.catergories,R.layout.support_simple_spinner_dropdown_item);
        spinner_catergory.setAdapter(adapter);

        final EditText expenseName = (EditText) getActivity().findViewById(R.id.expenseName_et);
        final EditText Amount = (EditText) getActivity().findViewById(R.id.amount_et);

        Button addExpense = (Button) getActivity().findViewById(R.id.addExpenseButton);
        Button cancelExpense = (Button) getActivity().findViewById(R.id.cancelButton);
        final String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expenseName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Expense name is required", Toast.LENGTH_SHORT).show();
                }
                else if(Amount.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "Amount is required", Toast.LENGTH_SHORT).show();
                }

               else {
                    try {
                        Double.parseDouble(Amount.getText().toString());
                        Expense expense = new Expense();
                        expense.setName(expenseName.getText().toString());
                        expense.setAmount(Double.parseDouble(Amount.getText().toString()));
                        expense.setCategory(spinner_catergory.getSelectedItem().toString());
                        expense.setDate(timeStamp);
                        mListener.addedExpenses(expense);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        cancelExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancelExpenses();
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void addedExpenses(Expense expense);
        public void cancelExpenses();
    }
}
