package com.hfad.cafeastrology;

import android.app.DatePickerDialog;
import android.os.Bundle;

import android.widget.DatePicker;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.text.DateFormat;
import java.util.Calendar;

public class RegisterFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    NavController navController = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.temp).setOnClickListener(this);
        view.findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.temp:
                navController.navigate(R.id.action_registerFragment_to_categoryFragment);
                break;

            case R.id.button:
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
                break;
        }
    }

    @Override
    public void onDateSet( DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) view.findViewById(R.id.birthdayInput);
        textView.setText(currentDateString);
    }
}