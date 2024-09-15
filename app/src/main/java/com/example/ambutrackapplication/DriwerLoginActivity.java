package com.example.ambutrackapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ambutrackapplication.comman.NetworkChangeListener;

public class DriwerLoginActivity extends AppCompatActivity {

    ImageView ivlogo;
    TextView tvLoginhere,tvnewuser;
    EditText etusername,etpassword;
    CheckBox checkBox;
    Button btnlogin;
    ProgressDialog progressDialog;
    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driwer_login);
        ivlogo=findViewById(R.id.ivDriwerLogin);
        tvLoginhere=findViewById(R.id.tvDriwerLoginMainTitle);
        etusername=findViewById(R.id.etDriwerLoginUserName);
        etpassword=findViewById(R.id.etDriwerLoginPassword);
        checkBox=findViewById(R.id.cbDriwerLoginCheckbox);
        btnlogin=findViewById(R.id.btnDriwerLogin);
        tvnewuser=findViewById(R.id.tvDriwerLoginNewuser);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    etpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    etpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etusername.getText().toString().isEmpty())
                {
                    etusername.setError("Enter your username");
                }
                else if(etusername.getText().toString().length()< 8)
                {
                    etusername.setError("Please enter 8 character");
                }
                else if(!etusername.getText().toString().matches(".*[A-Z].*"))
                {
                    etusername.setError("Used 1 Uppercase letter");
                } else if(!etusername.getText().toString().matches(".*[a-z].*"))
                {
                    etusername.setError("Used 1 lowercase letter ");
                }

                else if(etpassword.getText().toString().isEmpty())
                {
                    etpassword.setError("Enter your password");
                }
                else if(etpassword.getText().toString().length()< 8)
                {
                    etpassword.setError("Please enter 8 character");
                }
                else if(!etpassword.getText().toString().matches(".*[A-Z].*"))
                {
                    etpassword.setError("Used 1 Uppercase letter");
                } else if(!etpassword.getText().toString().matches(".*[a-z].*"))
                {
                    etpassword.setError("Used 1 lowercase letter ");
                }

                else if(!etpassword.getText().toString().matches(".*[0-9].*"))
                {
                    etpassword.setError("Used 1 number");
                }
                else if(!etpassword.getText().toString().matches(".*[#,@,%].*"))
                {
                    etpassword.setError("Used 1 special symbol");
                }
                else
                {
                    Toast.makeText(DriwerLoginActivity.this, "Login Succesfully done", Toast.LENGTH_SHORT).show();

                }
            }
        });
        tvnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DriwerLoginActivity.this, UserRegistrationActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,intentFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListener);
    }
}