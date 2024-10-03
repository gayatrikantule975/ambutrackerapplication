package com.example.ambutrackapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ambutrackapplication.comman.POJOGetCategorywiseHospital;
import com.example.ambutrackapplication.comman.URLs;
import com.example.ambutrackapplication.comman.UserAdapterGetCategorywiseHospital;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class UserCategoryWiseHospitalActivity extends AppCompatActivity {
SearchView svSearchcategoryWiseHospital;
ListView lvCategoryWiseHospitalList;
TextView tvNoCategoryAvailable;
String strCategoryName;
List<POJOGetCategorywiseHospital> pojoGetCategorywiseHospital;
UserAdapterGetCategorywiseHospital userAdapterGetCategorywiseHospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category_wise_hospital);
svSearchcategoryWiseHospital=findViewById(R.id.svSearchHospitalByCategory);
lvCategoryWiseHospitalList=findViewById(R.id.lvCategoryWisHospitalHospitalList);
tvNoCategoryAvailable=findViewById(R.id.tvCategoryWiseHospitalNoCategoryAvailable);
pojoGetCategorywiseHospital=new ArrayList<>();
strCategoryName=getIntent().getStringExtra("hospitalcategoryname");
svSearchcategoryWiseHospital.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchCategoryWiseHospital(query);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String query) {
        searchCategoryWiseHospital(query);
        return false;
    }
});
getCategoryWiseHospital();
    }

    private void searchCategoryWiseHospital(String query) {
        List<POJOGetCategorywiseHospital> temphospitallist=new ArrayList<>();
        temphospitallist.clear();

        for(POJOGetCategorywiseHospital obj:pojoGetCategorywiseHospital)
        {
            if(obj.getStrhospitalname().toUpperCase().contains(query.toUpperCase())||obj.getStrhospitalcityname().toUpperCase().contains(query.toUpperCase()))
            {
                temphospitallist.add(obj);
            }
            else
            {
                tvNoCategoryAvailable.setVisibility(View.VISIBLE);
            }
        }
        userAdapterGetCategorywiseHospital=new UserAdapterGetCategorywiseHospital(temphospitallist,UserCategoryWiseHospitalActivity.this);
        lvCategoryWiseHospitalList.setAdapter(userAdapterGetCategorywiseHospital);
    }

    private void getCategoryWiseHospital() {
        AsyncHttpClient  client=new AsyncHttpClient();//Client Server Communication,over the network transfer the data
        RequestParams params=new RequestParams();//put the data to AsyncHttpClient
        params.put("hospitalcategoryname",strCategoryName);
        client.post(URLs.usergetCategorywiseHospitalWebService,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("getHospitalCategoryWise");
                    if(jsonArray.isNull(0))
                    {
                        tvNoCategoryAvailable.setVisibility(View.VISIBLE);
                    }
for(int i=0;i<jsonArray.length();i++)
{
    JSONObject jsonObject=jsonArray.getJSONObject(i);
    String id=jsonObject.getString("id");
    String hospitalcategoryname=jsonObject.getString("hospitalcategoryname");
    String hospitalname=jsonObject.getString("hospitalname");
    String hospitalimage=jsonObject.getString("hospitalimage");
    String hospitaladdress=jsonObject.getString("hospitaladdress");
    String hospitalcityname=jsonObject.getString("hospitalcityname");
    String hospitalcontactno=jsonObject.getString("hospitalcontactno");
    String hospitalrating=jsonObject.getString("hospitalrating");
    String hospitalfeedback=jsonObject.getString("hospitalfeedback");
pojoGetCategorywiseHospital.add(new POJOGetCategorywiseHospital(id,hospitalcategoryname,hospitalname,hospitalimage,hospitaladdress,hospitalcityname,hospitalcontactno,hospitalrating,hospitalfeedback));
}
userAdapterGetCategorywiseHospital=new UserAdapterGetCategorywiseHospital(pojoGetCategorywiseHospital, UserCategoryWiseHospitalActivity.this);
lvCategoryWiseHospitalList.setAdapter(userAdapterGetCategorywiseHospital);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(UserCategoryWiseHospitalActivity.this,"Server Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}