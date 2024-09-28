package com.example.ambutrackapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ambutrackapplication.comman.POJOGetAllDetails;
import com.example.ambutrackapplication.comman.URLs;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;


public class UserHospitalFragment extends Fragment {
ListView lvHospitalCategory;
TextView tvNoCategoryAvailabel;
List<POJOGetAllDetails> pojoGetAllDetails;
UserAdapterGetAllDetails userAdapterGetAllDetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_user_hospital, container, false);
       lvHospitalCategory=view.findViewById(R.id.lvUserHospitalFragementHospitalCategory);
       tvNoCategoryAvailabel=view.findViewById(R.id.tvUserHospitalFragementNoCategoryAvailable);
       getAllDetails();
       return view;
    }

    private void getAllDetails() {
        AsyncHttpClient client=new AsyncHttpClient();//clinet server communication,over the network data transfer
        RequestParams params=new RequestParams();//Put the data to the AsyncHttpClient
        client.post(URLs.usergetAllDetialsWebService,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray=response.getJSONArray("getAllDetails");
                    if(jsonArray.isNull(0))
                    {
                        tvNoCategoryAvailabel.setVisibility(View.VISIBLE);
                    }
                    for(int i=0;i<jsonArray.length();i++)
                    {
                       JSONObject jsonObject=jsonArray.getJSONObject(i);
                       String strId=jsonObject.getString("id");
                       String strCategoryImage=jsonObject.getString("image");
                       String strcategoryName=jsonObject.getString("name");
                       //if pass id=4,image=a.png,name=cardiology passed to constructor then recieved in first constructor of POJO class
                        pojoGetAllDetails.add(new POJOGetAllDetails(strId,strCategoryImage,strcategoryName));//Passed arguments to pojo constructor
                        userAdapterGetAllDetails=new UserAdapterGetAllDetails(pojoGetAllDetails,getActivity());//passed argument to adapter class
                        lvHospitalCategory.setAdapter(userAdapterGetAllDetails);//Set the adpter design to listview
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getActivity(),"Server Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}