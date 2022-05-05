package com.hfad.cafeastrology;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MatchMakingFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String aries,taurus,gemini,cancer,leo,virgo,libra,scorpio,sagittarius,capricorn,aquarius,pisces;
     String txt = " ", link = "https://devbrewer-horoscope.p.rapidapi.com/match/" + "Aries" + "/" + "Aries";
    NavController navController = null;
    Boolean cool = false;
    private static int a;
    private static int b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_match_making, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.InfoImg).setOnClickListener(this);



    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();  //give you access to the view XML template

        TextView textView1 = (TextView) view.findViewById(R.id.matchResults);

        Spinner spinner = view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.zodiac1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        String zodiac = getActivity().getIntent().getExtras().getString("sign");

        txt  = spinner.getSelectedItem().toString();


//            if (txt.equals("Taurus")) {
//                link = "https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/" + "Taurus";
//            }


            //API connection
            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url(link)
                    .get()
                    .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                    .build();


            client.newCall(request).enqueue(new Callback() {
                //Parsing of the JSON file
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String myResponse = response.body().string();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject obj1 = new JSONObject(myResponse);

                                    String myString = (String) myResponse;
                                    String[] item = myString.split(":");
                                    String[] item2 = item[3].split("20");

                                    textView1.setText(item2[0] + '"');
                                    String boi = getActivity().getIntent().getExtras().getString("yay");
                                    System.out.println(boi);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                }
            });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text1 = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text1, Toast.LENGTH_SHORT).show();

        getActivity().getIntent().putExtra("yay", text1);



//        if(text1.equals("Taurus")){
//            getActivity().recreate();
//            System.out.println("selected");

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.InfoImg:
//                navController.navigate(R.id.action_matchMakingFragment_to_matchInfoFragment);
//                txt = "http";

                break;
        }
    }

}