package com.example.ambutrackapplication;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.ambutrackapplication.comman.NetworkChangeListener;

public class AdminLoginActivity extends AppCompatActivity {

    ImageView ivlogo;
    TextView tvLoginhere,tvnewuser;
    EditText etusername,etpassword;
    CheckBox checkBox;
    Button btnlogin;
    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        ivlogo=findViewById(R.id.ivAdminLogin);
        tvLoginhere=findViewById(R.id.tvAdminLoginMainTitle);
        etusername=findViewById(R.id.etAdminLoginUserName);
        etpassword=findViewById(R.id.etAdminLoginPassword);
        checkBox=findViewById(R.id.cbAdminLoginCheckbox);
        btnlogin=findViewById(R.id.btnAdminLogin);
        tvnewuser=findViewById(R.id.tvAdminLoginNewuser);
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
                    Toast.makeText(AdminLoginActivity.this, "Login Succesfully done", Toast.LENGTH_SHORT).show();

                }
            }
        });
        tvnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminLoginActivity.this, UserRegistrationActivity.class);
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