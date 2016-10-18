package com.example.raviteja.inclass8_expenseapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/*
ExpenseApp.java
Ravi Teja Yarlagadda
 */




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpenseApp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ExpenseApp extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ExpenseApp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_expense_app, container, false);
    }

    public void setNoexpense()
    {

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getActivity().setTitle("Expense App");
        super.onActivityCreated(savedInstanceState);
        ListView listView=null;
        MainActivity activity = (MainActivity) getActivity();
        final ArrayList<Expense> expenses=activity.getAllExpenses();
        Log.d("expenses",expenses.toString());
        if(expenses.isEmpty()) {
            TextView noexpenseText = (TextView) getActivity().findViewById(R.id.NoExpense_tv);
            noexpenseText.setText("There is no expense to show, Please add your expenses from the menu.");
        }
        else{
           listView = (ListView) getActivity().findViewById(R.id.expenseslistView);
            ExpenseAdapter adapter = new ExpenseAdapter(getActivity(), R.layout.row_layout, expenses);
            listView.setAdapter(adapter);
            adapter.setNotifyOnChange(true);
            Log.d("demo","adapter is set");
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    expenses.remove(i);
                    ListView listView = (ListView) getActivity().findViewById(R.id.expenseslistView);
                    ExpenseAdapter adapter1 = new ExpenseAdapter(getActivity(), R.layout.row_layout, expenses);
                    listView.setAdapter(adapter1);
                    adapter1.setNotifyOnChange(true);
                    Log.d("demo","adapter is set");
                    Toast.makeText(getActivity(), "Expense Deleted", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mListener.gotoDetailExpenseFragment(expenses.get(i));
                }
            });
        }
        getActivity().findViewById(R.id.ImagenewExpense).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoAddExpenseFragment();
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.gotoAddExpenseFragment();
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void gotoAddExpenseFragment();
        public void gotoDetailExpenseFragment(Expense expense);
    }


}
