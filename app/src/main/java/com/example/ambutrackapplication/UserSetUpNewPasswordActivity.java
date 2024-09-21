package com.example.ambutrackapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ambutrackapplication.comman.URLs;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserSetUpNewPasswordActivity extends AppCompatActivity {
    String strMobileNo;
    EditText etNewPassword,etConfirmPassword;
    AppCompatButton btnSetupNewPassword;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set_up_new_password);
        etNewPassword=findViewById(R.id.etSetUpPasswordPassword);
        etConfirmPassword=findViewById(R.id.etSetUpPasswordConfirmPassword);
        btnSetupNewPassword=findViewById(R.id.btnSetUpPasswordRegistrationMobileNo);
        strMobileNo=getIntent().getStringExtra("mobileno") ;
        btnSetupNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNewPassword.getText().toString().isEmpty()||etConfirmPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(UserSetUpNewPasswordActivity.this,"Please Enter New or Confirm Password",Toast.LENGTH_SHORT).show();
                }
                else if(!etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
                {
                    etConfirmPassword.setError("Password does not match");
                }
                else
                {
                    progressDialog=new ProgressDialog(UserSetUpNewPasswordActivity.this);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setTitle("Updating Password");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    forgetPassword();
                }
            }

            private void forgetPassword() {
                AsyncHttpClient client=new AsyncHttpClient();//Client Server Communication
                RequestParams params=new RequestParams();//Put the data to AsynchttpClient
                params.put("mobileno",strMobileNo);
                Toast.makeText(UserSetUpNewPasswordActivity.this,"Mobile No is recieved Here",Toast.LENGTH_SHORT).show();
                params.put("password",etNewPassword.getText().toString());
                client.post(URLs.userForgetPasswordWebSerivice,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String status=response.getString("success");
                            if(status.equals("1"))
                            {
                                Intent i=new Intent(UserSetUpNewPasswordActivity.this, UserLoginActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(UserSetUpNewPasswordActivity.this,"Password Not Changed",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(UserSetUpNewPasswordActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}