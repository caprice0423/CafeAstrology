package com.hfad.cafeastrology;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavController navController;
    NavigationView navigationView;
    Toolbar toolbar;
    Fragment fragment;
    Boolean playing = true;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
//        NavigationUI.setupWithNavController(navigationView,navController);
        //navigationView.setNavigationItemSelectedListener(this);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.mainFragment,R.id.instructionsFragment)
                        .setDrawerLayout(drawerLayout)
                        .build();


        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
        navigationView.setNavigationItemSelectedListener(this);

        Drawable d = getResources().getDrawable(R.drawable.constellations);
        actionBar.setBackgroundDrawable(d);
        View main = findViewById(R.id.mainFragment);
        PlayBackgroundSound(main);

//        int id = (int)getIntent().getIntExtra("category_id", 0);
//        MatchMakingFragment frag = (MatchMakingFragment) getSupportFragmentManager().findFragmentById(R.id.frag_detail);
//        frag.setWorkId(id);

    }

    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,drawerLayout);
    }

    //Define action for options in toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        BackgroundSoundService
        View main = findViewById(R.id.mainFragment);
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                if(playing == true) {
                    StopBackgroundSound(main);
                    Toast.makeText(this, "Music is Off", Toast.LENGTH_SHORT).show();
                } else {
                    PlayBackgroundSound(main);
                    Toast.makeText(this, "Music is On", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("API used: https://rapidapi.com/zedjeep/api/devbrewer-horoscope. \n Developers: Caprice and Sadjell");
                builder.setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();
                break;

            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Check out our app!");
                intent.putExtra(Intent.EXTRA_TEXT, "Our app link here");
                startActivity(Intent.createChooser(intent, "Share via"));
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        //view that displays user info in drawer header
        TextView usern = (TextView) this.findViewById(R.id.appCompatTextView2);
        TextView fulln = (TextView) this.findViewById(R.id.appCompatTextView);
        TextView birthd = (TextView) this.findViewById(R.id.appCompatTextView3);
        TextView signN = (TextView) this.findViewById(R.id.signname);
///
        Class fragmentClass;
        switch(item.getItemId()) {
            case R.id.nav_information:
                fragmentClass = instructionsFragment.class;
                navController.navigate(R.id.instructionsFragment);
                break;
            case R.id.nav_sign_out:
                fragmentClass = MainFragment.class;
                usern.setText(" ");
                fulln.setText(" ");
                birthd.setText(" ");
                signN.setText(" ");
                navController.navigate(R.id.mainFragment);
                break;
            default:
                fragmentClass = MainFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();


        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }



//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////        int id = item.getItemId();
////        TextView usern = (TextView) this.findViewById(R.id.appCompatTextView2);
////        TextView fulln = (TextView) this.findViewById(R.id.appCompatTextView);
////        TextView birthd = (TextView) this.findViewById(R.id.appCompatTextView3);
////        TextView signN = (TextView) this.findViewById(R.id.signname);
////
////        NavigationView navigationView = (NavigationView) this.findViewById(R.id.navigationView);
////        View headerView = navigationView.getHeaderView(0);
////        ImageView myImgView = (ImageView) headerView.findViewById(R.id.appCompatImageView);
////
////        Fragment fragment = null;
////        if(id == R.id.nav_home){
////            fragment = new MainFragment();
////            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////            ft.replace(R.id.nav_host_fragment, fragment);
////            ft.commit();
////
////        }
////        else if(id == R.id.nav_information){
////            fragment = new instructionsFragment();
////            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
////            ft1.replace(R.id.nav_host_fragment, fragment);
////            ft1.commit();
////
////        }
////
////        else if(id == R.id.nav_sign_out){
////            fragment = new LoginFragment();
////            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
////            ft2.replace(R.id.nav_host_fragment, fragment);
////            ft2.commit();
////
////            usern.setText(" ");
////            fulln.setText(" ");
////            birthd.setText(" ");
////            signN.setText(" ");
////            myImgView.setImageResource(R.drawable.profileicon1);
////
////
////
////
////        }
////
////        drawerLayout.closeDrawer(GravityCompat.START);
////        return true;
////
//   }

    //Starts and Stops background music

    public void PlayBackgroundSound(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        playing = true;
        startService(intent);
    }

    public void StopBackgroundSound(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundSoundService.class);
        playing = false;
        stopService(intent);
    }
}