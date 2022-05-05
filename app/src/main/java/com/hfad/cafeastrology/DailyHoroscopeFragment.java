package com.hfad.cafeastrology;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DailyHoroscopeFragment extends Fragment {

    EditText username;
    LoginFragment userr;
    DatabaseHelper DB;
    String column1 = " ", link;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_horoscope, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();  //give you access to the view XML template

        String user = getActivity().getIntent().getExtras().getString("key");


        TextView textView1 = (TextView) view.findViewById(R.id.dailyWords);

        DB = new DatabaseHelper(getActivity());
        DB.getReadableDatabase();
        Cursor c = DB.getReadableDatabase().rawQuery("SELECT zodiac, username FROM Users WHERE username = ?", new String[]{user});

        if (c.moveToFirst()) {
            do {
                // Passing values
                column1 = c.getString(0);
                System.out.println(column1);
                // Do something Here with values
            } while (c.moveToNext());
        }
        c.close();

        //API connection
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://devbrewer-horoscope.p.rapidapi.com/today/long/" + column1)
//                .url(link)
                .get()
                .addHeader("X-RapidAPI-Host", "devbrewer-horoscope.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "50a31e52e7msh2d536d59c13a5c1p1414b9jsn14b3f1f13394")
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

                                JSONObject json = new JSONObject(myResponse);
                                textView1.setText(json.getJSONObject(column1).getString("Daily"));
//                                textView1.setText(myResponse);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });

    }
}