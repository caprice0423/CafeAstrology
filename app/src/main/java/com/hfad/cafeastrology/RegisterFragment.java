package com.hfad.cafeastrology;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class RegisterFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    NavController navController = null;

    EditText username, password, repassword;
    TextView date;
    Button signup, signin;
    DatabaseHelper DB;




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
//        view.findViewById(R.id.temp).setOnClickListener(this);
//        view.findViewById(R.id.button).setOnClickListener(this);
        view.findViewById(R.id.registerSubmit).setOnClickListener(this);

        username = (EditText) getActivity().findViewById(R.id.newUsernameInput);
        password = (EditText) getActivity().findViewById(R.id.newPasswordInput);
//        date = (TextView) getActivity().findViewById(R.id.birthdayInput);
        signup = (Button) getActivity().findViewById(R.id.registerSubmit);
        signin = (Button) getActivity().findViewById(R.id.loginSubmit);
        DB = new DatabaseHelper(getActivity());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
//                String birthdate = date.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(pass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass); //, birthdate);
                            if(insert==true){
                                navController.navigate(R.id.action_registerFragment_to_loginFragment);
                            }else{
                                Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.temp:
//                navController.navigate(R.id.action_registerFragment_to_loginFragment);
//                break;
            case R.id.registerSubmit:
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
                break;
//            case R.id.button:
//                DialogFragment newFragment = new SelectDateFragment();
//                newFragment.show(getFragmentManager(), "DatePicker");
//                break;
        }
    }

    @Override
    public void onDateSet( DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

//        TextView textView = (TextView) view.findViewById(R.id.birthdayInput);
//        textView.setText(currentDateString);
    }
}