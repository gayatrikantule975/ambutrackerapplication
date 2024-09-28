package com.example.ambutrackapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHomActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
BottomNavigationView bottomNavigationView;
Toolbar tbHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hom);

bottomNavigationView=findViewById(R.id.homeBottonNaviagtionView);
bottomNavigationView.setOnNavigationItemSelectedListener(this);
bottomNavigationView.setSelectedItemId(R.id.homebottomnaviagtionMap);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.homeMenuMyProfile)
        {
            Intent i=new Intent(UserHomActivity.this,UserMyProfileActivity.class);
            startActivity(i);
        }
        return true;
    }

    userHistoryFragment mapFragment=new userHistoryFragment();
    UserHospitalFragment hospitalFragment=new UserHospitalFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.homebottomnaviagtionMap)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,mapFragment).commit();
        }
        else if(item.getItemId()==R.id.homebottomnaviagtionHospital)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,hospitalFragment).commit();
        }
        return true;
    }
}