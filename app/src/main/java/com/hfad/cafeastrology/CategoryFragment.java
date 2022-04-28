package com.hfad.cafeastrology;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class CategoryFragment extends Fragment implements View.OnClickListener {
    NavController navController = null;

    public CategoryFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_category, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.DailyHoroscopeBtn).setOnClickListener(this);
        view.findViewById(R.id.MatchBtn).setOnClickListener(this);

//        System.out.println(getActivity().getIntent().getExtras().getString("key"));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DailyHoroscopeBtn:
                navController.navigate(R.id.action_categoryFragment_to_dailyHoroscopeFragment);
                break;

            case R.id.MatchBtn:
                navController.navigate(R.id.action_categoryFragment_to_matchMakingFragment);
                break;
        }
    }
}