package com.hfad.cafeastrology;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.Preference;
import android.preference.PreferenceManager;
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

    EditText username, password, fullynamed;
    TextView date;
    Button signup, signin;
    DatabaseHelper DB;
    String aries = "03/21/year",
            taurus, gemini, cancer, leo, virgo, libra, scorpio, sagittarius, capricorn, aquarius, pisces;



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

        view.findViewById(R.id.birthdayInput).setOnClickListener(this);

        view.findViewById(R.id.registerSubmit).setOnClickListener(this);

        view.findViewById(R.id.newUsernameInput).setOnClickListener(this);

        view.findViewById(R.id.usersName).setOnClickListener(this);

        username = (EditText) getActivity().findViewById(R.id.newUsernameInput);
        password = (EditText) getActivity().findViewById(R.id.newPasswordInput);
        fullynamed = (EditText) getActivity().findViewById(R.id.usersName);

        date = (TextView) getActivity().findViewById(R.id.birthdayInput);

        signup = (Button) getActivity().findViewById(R.id.registerSubmit);
        signin = (Button) getActivity().findViewById(R.id.loginSubmit);
        DB = new DatabaseHelper(getActivity());


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String birthdate = date.getText().toString();
                String fullName = fullynamed.getText().toString();
                String name;

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(pass)){
                        Boolean checkuser = DB.checkUsername(user);

                        if(checkuser==false) {

                            String myString = (String) date.getText();
                            String[] item = myString.split("/");

                            int day = Integer.parseInt(item[1]);
                            String month = item[0];

                            String sign = "N/A";

                            if (month.equals("1")) {
                                if (day < 20)
                                    sign = "Capricorn";
                                else
                                    sign = "Aquarius";
                            } else if (month.equals("2")) {
                                if (day < 19)
                                    sign = "Aquarius";
                                else
                                    sign = "Pisces";
                            } else if (month.equals("3")) {
                                if (day < 21)
                                    sign = "Pisces";
                                else
                                    sign = "Aries";
                            } else if (month.equals("4")) {
                                if (day < 20)
                                    sign = "Aries";
                                else
                                    sign = "Taurus";
                            } else if (month.equals("5")) {
                                if (day < 21)
                                    sign = "Taurus";
                                else
                                    sign = "Gemini";
                            } else if (month.equals("6")) {
                                if (day < 21)
                                    sign = "Gemini";
                                else
                                    sign = "Cancer";
                            } else if (month.equals("7")) {
                                if (day < 23)
                                    sign = "Cancer";
                                else
                                    sign = "Leo";
                            } else if (month.equals("8")) {
                                if (day < 23)
                                    sign = "Leo";
                                else
                                    sign = "Virgo";
                            } else if (month.equals("9")) {
                                if (day < 23)
                                    sign = "Virgo";
                                else
                                    sign = "Libra";
                            } else if (month.equals("10")) {
                                if (day < 23)
                                    sign = "Libra";
                                else
                                    sign = "Scorpio";
                            } else if (month.equals("11")) {
                                if (day < 22)
                                    sign = "Scorpio";
                                else
                                    sign = "Sagittarius";
                            } else if (month.equals("12")) {
                                if (day < 22)
                                    sign = "Sagittarius";
                                else
                                    sign = "Capricorn";
                            }

                            Boolean insert = DB.insertData(fullName, user, pass, birthdate, sign); //, birthdate);

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
            case R.id.registerSubmit:
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
                break;

            case R.id.birthdayInput:
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


       // TextView textView = (TextView) view.findViewById(R.id.birthdayInput);
        date.setText(currentDateString);
    }

}