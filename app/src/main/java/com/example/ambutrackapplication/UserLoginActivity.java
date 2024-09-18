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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.ambutrackapplication.comman.NetworkChangeListener;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserLoginActivity extends AppCompatActivity {

    ImageView ivlogo;
    TextView tvLoginhere,tvnewuser;
    EditText etusername,etpassword;
    CheckBox checkBox;
    Button btnlogin;
    NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    ProgressDialog progressDialog;
    GoogleSignInOptions googleSignInOptions;//when we click on button then gmail option from which we can login shown
    GoogleSignInClient googleSignInClient;//option which has selcted is stored there
    AppCompatButton btnLoginSigninwithgoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        ivlogo=findViewById(R.id.ivUserLogin);
        tvLoginhere=findViewById(R.id.tvUserLoginMainTitle);
        etusername=findViewById(R.id.etUserLoginUserName);
        etpassword=findViewById(R.id.etUserLoginPassword);
        checkBox=findViewById(R.id.cbUserLoginCheckbox);
        btnlogin=findViewById(R.id.btnUserLogin);
        tvnewuser=findViewById(R.id.tvUserLoginNewuser);
        btnLoginSigninwithgoogle=findViewById(R.id.btnUserLoginGoogleLogin);
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
        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient=GoogleSignIn.getClient(UserLoginActivity.this,googleSignInOptions);
        btnLoginSigninwithgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();//user defined:made by user,predefined:provied by system or package
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
                    progressDialog=new ProgressDialog(UserLoginActivity.this);
                    progressDialog.setTitle("Please wait");
                    progressDialog.setMessage("Login under process");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    UserLoginDetails();
                }
            }
        });
        tvnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
                startActivity(i);
            }
        });

    }

    private void UserLoginDetails() {
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("username",etusername.getText().toString());
        params.put("password",etpassword.getText().toString());

        client.post("http://192.168.163.27:80/AmbuTrackerAPI/UserLoginDetails",params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        progressDialog.dismiss();
                        try {
                            String status=response.getString("success");
                            if (response.equals("1"))
                            {
                                Toast.makeText(UserLoginActivity.this, "Login successfully done", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(UserLoginActivity.this, UserHomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(UserLoginActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        progressDialog.dismiss();
                        Toast.makeText(UserLoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    private void signIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)//selected otpion in googlesigninclient now stored in data
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Intent intent=new Intent(UserLoginActivity.this,UserHomeActivity.class);
                startActivity(intent);
                finish();
            } catch (ApiException e) {
                Toast.makeText(UserLoginActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
            }
        }

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