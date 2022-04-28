package com.hfad.cafeastrology;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class LoginFragment extends Fragment implements View.OnClickListener {
    NavController navController = null;

    EditText username, password;
    Button btnlogin;
    DatabaseHelper DB;
    String currUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.registerHere).setOnClickListener(this);
        view.findViewById(R.id.loginSubmit).setOnClickListener(this);

        username = (EditText) getActivity().findViewById(R.id.usernameInput);
        password = (EditText) getActivity().findViewById(R.id.passwordInput);
        btnlogin = (Button) getActivity().findViewById(R.id.loginSubmit);
        DB = new DatabaseHelper(getActivity());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = DB.checkUsernamePassword(user, pass);

                    if(checkUserPass==true){
                        getActivity().getIntent().putExtra("key", user);
                        Toast.makeText(getActivity(),  user + " has signed in successfully", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_loginFragment_to_categoryFragment);
                    }else{
                        Toast.makeText(getActivity(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerHere:
                navController.navigate(R.id.action_loginFragment_to_registerFragment2);
                break;
        }
    }

    // Getter
    public String getCurrentUser() {
        return currUser;
    }

    // Setter
    public void setCurrentUser(String user) {
        this.currUser = user;
    }



}
