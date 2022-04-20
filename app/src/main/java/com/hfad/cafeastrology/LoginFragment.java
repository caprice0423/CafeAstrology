package com.hfad.cafeastrology;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class LoginFragment extends Fragment { // implements View.OnClickListener {
    NavController navController = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        navController = Navigation.findNavController(view);
//        view.findViewById(R.id.nextbtn).setOnClickListener(this);
//        view.findViewById(R.id.instructionsbtn).setOnClickListener(this);

    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.registerHere:
//                navController.navigate(R.id.action_loginFragment_to_registerFragment2);
//                break;
//        }
//    }
}