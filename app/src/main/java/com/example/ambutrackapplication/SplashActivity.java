package com.example.ambutrackapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    ImageView ivLogo;
    TextView tvTitle1;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivLogo=findViewById(R.id.ivAmbuLogo);
        tvTitle1=findViewById(R.id.tvSplashTitleone);

        animation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.anim);
        ivLogo.setAnimation(animation);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this, UserLoginActivity.class);
                startActivity(i);
            }
        },3000);
        }
}