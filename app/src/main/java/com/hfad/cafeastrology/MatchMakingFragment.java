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


public class MatchMakingFragment extends Fragment implements View.OnClickListener {

    
    NavController navController = null;
    Boolean cool = false;

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
        view.findViewById(R.id.aries1).setOnClickListener(this);
        view.findViewById(R.id.taurus1).setOnClickListener(this);
        view.findViewById(R.id.gemini1).setOnClickListener(this);
        view.findViewById(R.id.cancer1).setOnClickListener(this);
        view.findViewById(R.id.leo1).setOnClickListener(this);
        view.findViewById(R.id.virgo1).setOnClickListener(this);
        view.findViewById(R.id.libra1).setOnClickListener(this);
        view.findViewById(R.id.Scorpio1).setOnClickListener(this);
        view.findViewById(R.id.sag1).setOnClickListener(this);
        view.findViewById(R.id.capricorn1).setOnClickListener(this);
        view.findViewById(R.id.aquarius1).setOnClickListener(this);
        view.findViewById(R.id.pisces1).setOnClickListener(this);


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View view) {
        String zodiac = getActivity().getIntent().getExtras().getString("sign");
        View v = getView();
        TextView tex = (TextView) v.findViewById(R.id.matchResults);


        switch (view.getId()) {
            case R.id.InfoImg:
                navController.navigate(R.id.action_matchMakingFragment_to_matchInfoFragment);
                break;


            case R.id.aries1:

                //API connection
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Aries")
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
                                        String[] item2 = item[3].split("'\'" + "u");
                                        
                                        tex.setText("Aries: " + item2[0] + '"');
                                        

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });
                break;

            case R.id.taurus1:

                //API connection
                OkHttpClient client2 = new OkHttpClient();

                Request request2 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Taurus")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client2.newCall(request2).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Taurus: " + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.gemini1:

                //API connection
                OkHttpClient client3 = new OkHttpClient();

                Request request3 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Gemini")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client3.newCall(request3).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Gemini: " + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.cancer1:

                //API connection
                OkHttpClient client4 = new OkHttpClient();

                Request request4 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Cancer")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client4.newCall(request4).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Cancer: " + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.leo1:

                //API connection
                OkHttpClient client5 = new OkHttpClient();

                Request request5 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Leo")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client5.newCall(request5).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Leo:" + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

        case R.id.virgo1:

        //API connection
        OkHttpClient client6 = new OkHttpClient();

        Request request6 = new Request.Builder()
                .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Virgo")
                .get()
                .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                .build();


        client6.newCall(request6).enqueue(new Callback() {
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
                                String[] item2 = item[3].split("'\'" + "u");

                                tex.setText("Virgo" + item2[0] + '"');

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });

        break;

            case R.id.libra1:

                //API connection
                OkHttpClient client7 = new OkHttpClient();

                Request request7 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Libra")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client7.newCall(request7).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Libra" + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.Scorpio1:

                //API connection
                OkHttpClient client8 = new OkHttpClient();

                Request request8 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Scorpio")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client8.newCall(request8).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Scorpio" + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.sag1:

                //API connection
                OkHttpClient client9 = new OkHttpClient();

                Request request9 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Sagittarius")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client9.newCall(request9).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Sagittarius" + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.capricorn1:

                //API connection
                OkHttpClient client10 = new OkHttpClient();

                Request request10 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Capricorn")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client10.newCall(request10).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Capricorn" + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.aquarius1:

                //API connection
                OkHttpClient client11 = new OkHttpClient();

                Request request11 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Aquarius")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client11.newCall(request11).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("Aquarius" + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;

            case R.id.pisces1:

                //API connection
                OkHttpClient client12 = new OkHttpClient();

                Request request12 = new Request.Builder()
                        .url("https://devbrewer-horoscope.p.rapidapi.com/match/" + zodiac + "/Pisces")
                        .get()
                        .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                        .build();


                client12.newCall(request12).enqueue(new Callback() {
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
                                        String[] item2 = item[3].split("'\'" + "u");

                                        tex.setText("pisces" + item2[0] + '"');

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                    }
                });

                break;
        }
    }

}