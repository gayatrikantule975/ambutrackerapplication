package com.example.ambutrackapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.ambutrackapplication.comman.URLs;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserMyProfileActivity extends AppCompatActivity {
    ImageView ivMyprofile;
    AppCompatButton btnChangeProfile;
    TextView tvName,tvEmailId,tvMobileNo,tvUserName;
    AppCompatButton btnSignOut;
    GoogleSignInOptions googleSignInOptions;
   GoogleSignInClient googleSignInClient;
   String userName;
   SharedPreferences sharedPreferences;
   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_my_profile);
        tvName=findViewById(R.id.tvUserHomeName);
        tvEmailId=findViewById(R.id.tvUserHomeEmail);
        tvMobileNo=findViewById(R.id.tvuserHomeMobileNo);
        tvUserName=findViewById(R.id.tvUserHomeUserName);
        ivMyprofile=findViewById(R.id.ivusermyprofilechangeprogfle);
        btnChangeProfile=findViewById(R.id.btnMyProfileChangeProfile);
        btnSignOut=findViewById(R.id.btnLoginWithgoogleLogout);
        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient= GoogleSignIn.getClient(UserMyProfileActivity.this,googleSignInOptions);
        GoogleSignInAccount googleSignInAccount=GoogleSignIn.getLastSignedInAccount(this);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(UserMyProfileActivity.this);
        userName=sharedPreferences.getString("username","");
        if(googleSignInAccount!=null)
        {
            String name=googleSignInAccount.getDisplayName();
            String email=googleSignInAccount.getEmail();
            tvName.setText(name);
            tvEmailId.setText(email);
            btnSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent=new Intent(UserMyProfileActivity.this,UserLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog=new ProgressDialog(UserMyProfileActivity.this);
        progressDialog.setTitle("My Profile");
        progressDialog.setMessage("Please wait..");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
        getMyDetails();
    }

    private void getMyDetails(){
        AsyncHttpClient client=new AsyncHttpClient();//client server communiction:over the internet pass data to datbase
        RequestParams params=new RequestParams();

        params.put("username",userName);
        client.post(URLs.usergetMyDetailsWebService,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray=response.getJSONArray("getMyDetails");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                             String strid=jsonObject.getString("id");
                             String strImage=jsonObject.getString("image");
                             String strName=jsonObject.getString("name");
                              String strMobileNo=jsonObject.getString("mobileno");
                              String strEmailid=jsonObject.getString("emailid");
                              String strUsername=jsonObject.getString("username");
                                tvName.setText(strName);
                                tvMobileNo.setText(strMobileNo);
                                tvEmailId.setText(strEmailid);
                                tvUserName.setText(strUsername);
                                Glide.with(UserMyProfileActivity.this)
                                                .load("http://192.168.113.27:80/AmbuTrackerAPI/images/"+strImage)
                                                        .skipMemoryCache(true)
                                                                .error(R.drawable.image_not_found)
                                        .into(ivMyprofile);

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(UserMyProfileActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}