package com.hfad.cafeastrology;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.navigation.NavigationView;


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

        String user = getActivity().getIntent().getExtras().getString("key");
        String date = getActivity().getIntent().getExtras().getString("birthday");
        String name = getActivity().getIntent().getExtras().getString("fullname");
        String zodiac = getActivity().getIntent().getExtras().getString("sign");

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.DailyHoroscopeBtn).setOnClickListener(this);
        view.findViewById(R.id.MatchBtn).setOnClickListener(this);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);

        TextView navName = (TextView) headerView.findViewById(R.id.appCompatTextView);
        navName.setText(name);

        TextView navUsername = (TextView) headerView.findViewById(R.id.appCompatTextView2);
        navUsername.setText(user);

        TextView navBirth = (TextView) headerView.findViewById(R.id.appCompatTextView3);
        navBirth.setText(date);

        TextView navZodiac = (TextView) headerView.findViewById(R.id.signname);
        navZodiac.setText(zodiac);

        ImageView myImgView = (ImageView) headerView.findViewById(R.id.appCompatImageView);

        if (zodiac.equals("Capricorn")){
            myImgView.setImageResource(R.drawable.capricorn);
        } else if (zodiac.equals("Aquarius")) {
            myImgView.setImageResource(R.drawable.aquarius);
        } else if (zodiac.equals("Taurus")) {
            myImgView.setImageResource(R.drawable.taurusicon2);
        } else if (zodiac.equals("Gemini")) {
            myImgView.setImageResource(R.drawable.gemini);
        } else if (zodiac.equals("Cancer")) {
            myImgView.setImageResource(R.drawable.cancer);
        } else if (zodiac.equals("Leo")) {
            myImgView.setImageResource(R.drawable.leo);
        } else if (zodiac.equals("Virgo")) {
            myImgView.setImageResource(R.drawable.virgo);
        } else if (zodiac.equals("Libra")) {
            myImgView.setImageResource(R.drawable.libra);
        } else if (zodiac.equals("Scorpio")) {
            myImgView.setImageResource(R.drawable.scorpio);
        } else if (zodiac.equals("Sagittarius")) {
            myImgView.setImageResource(R.drawable.sagittarius);
        } else if (zodiac.equals("Pisces")) {
            myImgView.setImageResource(R.drawable.pisces);
        } else if (zodiac.equals("Aries")) {
            myImgView.setImageResource(R.drawable.aries);
        }


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