package com.hfad.cafeastrology;

import android.os.Bundle;
import android.os.Bundle;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class MatchMakingFragment extends Fragment implements AdapterView.OnItemSelectedListener{


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
    public void onStart() {
        super.onStart();

        View view = getView();  //give you access to the view XML template

        TextView textView1 = (TextView) view.findViewById(R.id.matchResults);


        Spinner spinner = view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.zodiac1,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

//        Spinner spinner2 = view.findViewById(R.id.spinner2);
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.zodiac1,
//                android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter2);
//        spinner2.setOnItemSelectedListener(this);

//        String name1 = getActivity().getIntent().getExtras().getString("named");
//        System.out.println(name1);
//        System.out.println(getter());

        //API connection
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://devbrewer-horoscope.p.rapidapi.com/match/Leo/Aries")
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
                                textView1.setText(item[3] +"." + item[4]);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });

    }
    // Getter
//    public String getter() {
////        String name1 = getActivity().getIntent().getExtras().getString("named");
////
////        return name1;
//        return txt;
//    }
//
//    // Setter
//    public void setter(String txt) {
//        this.txt = txt;
//    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text1 = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text1, Toast.LENGTH_SHORT).show();

//        getActivity().getIntent().putExtra("named", text1);
//        frag.setter(text1);

//
//       switch (text1){
//           case "Aries":
//               sign = "Aries";
//               break;
//
//           case "Taurus":
//               sign = "Taurus";
//               break;
//
//           case "Gemini":
//
//               break;
//
//           case "Cancer":
//
//               break;
//
//           case "Leo":
//
//               break;
//
//           case "Virgo":
//
//               break;
//
//           case "Libra":
//
//               break;
//
//           case "Scorpio":
//
//               break;
//
//           case "Sagittarius":
//
//               break;
//
//           case "Capricorn":
//
//               break;
//
//           case "Aquarius":
//
//               break;
//
//           case "Pisces":
//
//               break;
//       }

//        if (text.equals("Aries")){
//            System.out.println("s");
//        } else if (text.equals("Medium")){
//            System.out.println("m");
//        } else if (text.equals("Large")){
//            System.out.println("l");
//        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}