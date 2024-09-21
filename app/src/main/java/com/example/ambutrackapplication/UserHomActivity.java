package com.example.ambutrackapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHomActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hom);
bottomNavigationView=findViewById(R.id.homeBottonNaviagtionView);
bottomNavigationView.setOnNavigationItemSelectedListener(this);
bottomNavigationView.setSelectedItemId(R.id.homebottomnaviagtionMap);
    }
MapFragment mapFragment=new MapFragment();
    AmbulanceFragment ambulanceFragment=new AmbulanceFragment();
    HospitalFragment hospitalFragment=new HospitalFragment();
    HelplineFragment helplineFragment=new HelplineFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.homebottomnaviagtionMap)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,mapFragment).commit();
        }else if(item.getItemId()==R.id.homebottomnaviagtionAmbulance)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,ambulanceFragment).commit();
        }
        else if(item.getItemId()==R.id.homebottomnaviagtionHospital)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,hospitalFragment).commit();
        }
        else if(item.getItemId()==R.id.homebottomnaviagtionHelpline)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,helplineFragment).commit();
        }
        return true;
    }
}