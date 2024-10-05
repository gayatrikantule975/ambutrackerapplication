package com.example.ambutrackapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ambutrackapplication.comman.URLs;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserEditProfileActivity extends AppCompatActivity {
    ImageView ivEditProfile;
    EditText etName,etMobileNo,etEmailId,etUsername;
    Button btnEditProfile;
    String strName,strMobileNo,stremailId,strUserName;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_edit_profile);
       ivEditProfile=findViewById(R.id.ivUserUpdateProfileMainLogo);
       etName=findViewById(R.id.etUserEditProfileName);
       etMobileNo=findViewById(R.id.etUserEditProfileMobileNo);
       etEmailId=findViewById(R.id.etUserEditProfileEmailId);
       etUsername=findViewById(R.id.etUserEditPofileUserName);
       btnEditProfile=findViewById(R.id.btnUserUpdateProfile);
     strName=getIntent().getStringExtra("name");
     strMobileNo=getIntent().getStringExtra("mobileno");
     stremailId=getIntent().getStringExtra("emialid");
     strUserName=getIntent().getStringExtra("username");
     etName.setText(strName);
     etMobileNo.setText(strMobileNo);
     etEmailId.setText(stremailId);
     etUsername.setText(strUserName);
     btnEditProfile.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            progressDialog=new ProgressDialog(UserEditProfileActivity.this);
            progressDialog.setTitle("Updating Profile");
            progressDialog.setMessage("Please Wait..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            userEditProfile();
         }
     });
    }

    private void userEditProfile() {
        AsyncHttpClient client=new AsyncHttpClient();//Client Server communication,over the network trnasfer data
        RequestParams params=new RequestParams();//Put the data to asyncHttpClinet
        params.put("name",etName.getText().toString());
        params.put("mobileno",etMobileNo.getText().toString());
        params.put("emailid",etEmailId.getText().toString());
       params.put("username",etUsername.getText().toString());
       client.post(URLs.userUpdateProfileWebService,params,new JsonHttpResponseHandler()
       {
           @Override
           public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
               super.onSuccess(statusCode, headers, response);
               try {
                   String status=response.getString("success");
                   if(status.equals("1"))
                   {
                       progressDialog.dismiss();
                       Toast.makeText(UserEditProfileActivity.this,"Upadte done",Toast.LENGTH_SHORT).show();
                       Intent i=new Intent(UserEditProfileActivity.this,UserMyProfileActivity.class);
                       startActivity(i);
                   }
                   else
                   {
                       progressDialog.dismiss();
                       Toast.makeText(UserEditProfileActivity.this,"Update not done",Toast.LENGTH_SHORT).show();
                   }
               } catch (JSONException e) {
                   throw new RuntimeException(e);
               }
           }

           @Override
           public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
               super.onFailure(statusCode, headers, throwable, errorResponse);
               progressDialog.dismiss();
               Toast.makeText(UserEditProfileActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
           }
       });
    }
}