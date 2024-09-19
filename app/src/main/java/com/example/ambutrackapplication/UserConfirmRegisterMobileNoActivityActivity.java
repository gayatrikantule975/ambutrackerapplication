package com.example.ambutrackapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class UserConfirmRegisterMobileNoActivityActivity extends AppCompatActivity {
    ImageView ivConfirmRegisterMobileNoImage;
    EditText etConfirmRegisterMobileNo;
    AppCompatButton btnConfirmRegisterMobileNo;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm_register_mobile_no_activity);
        ivConfirmRegisterMobileNoImage=findViewById(R.id.ivUserMobilenoConfirmationimg);
        etConfirmRegisterMobileNo=findViewById(R.id.etUserMobilenoverifyMobileNo);
        btnConfirmRegisterMobileNo=findViewById(R.id.btnmobilenoVerificationVerify);
        btnConfirmRegisterMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etConfirmRegisterMobileNo.getText().toString().isEmpty())
                {
                    etConfirmRegisterMobileNo.setError("Please Enter Mobile Number");
                }
                else if(etConfirmRegisterMobileNo.getText().toString().length()!=10)
                {
                    etConfirmRegisterMobileNo.setError("Please Enter Valid Mobile Number");
                }
                else {
                    progressDialog=new ProgressDialog(UserConfirmRegisterMobileNoActivityActivity.this);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91"+etConfirmRegisterMobileNo.getText().toString(),//on which mobile no otp received
                            60,//in how many seconds otp to be sent
                            TimeUnit.SECONDS,//mention time unit
                            UserConfirmRegisterMobileNoActivityActivity.this,//Current java class
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks()//callback:verification complete or not
                            {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserConfirmRegisterMobileNoActivityActivity.this,"Verification Successful",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserConfirmRegisterMobileNoActivityActivity.this,"Verification Failed",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationCode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)//1 st arg:verificationCode:firebase sended free otp received here
                                {
                                    Intent i=new Intent(UserConfirmRegisterMobileNoActivityActivity.this, UserForgetPasswordActivity.class);
                                    i.putExtra("verificationcode",verificationCode);//key:string and value
                                    //To transfer recieved code all user info to verificationActivity,in verification Activity there is no edittext so we sent this data from here and recieve there
                                    i.putExtra("mobileno",etConfirmRegisterMobileNo.getText().toString());
                                    startActivity(i);
                                }
                            }
                    );
                }
            }
        });
    }
    }
