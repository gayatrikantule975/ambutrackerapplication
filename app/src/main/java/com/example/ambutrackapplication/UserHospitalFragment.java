package com.example.ambutrackapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import kotlin.collections.ArrayDeque;


public class UserHospitalFragment extends Fragment {
ListView lvHospitalCategory;
TextView tvNoCategoryAvailabel;
List<POJOGetAllDetails> pojoGetAllDetails;//Get and set the same type of multiple data
UserAdapterGetAllDetails userAdapterGetAllDetails;//to show the multiple design
SearchView svSearchCategory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_user_hospital, container, false);
       pojoGetAllDetails=new ArrayList<>();
       lvHospitalCategory=view.findViewById(R.id.lvUserHospitalFragementHospitalCategory);
       tvNoCategoryAvailabel=view.findViewById(R.id.tvUserHospitalFragementNoCategoryAvailable);
       svSearchCategory=view.findViewById(R.id.svHospitalFragmentSearchCategory);
       svSearchCategory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               searchCategory(query);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String query) {
               searchCategory(query);
               return false;
           }
       });
       getAllDetails();
       return view;
    }

    private void searchCategory(String query)//in query search data store
     {
        List<POJOGetAllDetails> tempcategory=new ArrayDeque<>();//To store the same type of multiple temporary data which will find after search,so we created new object of pojo class
        tempcategory.clear();//by default temp data should be clear
        for(POJOGetAllDetails obj:pojoGetAllDetails)//without giving index fetch data,same type of  all multiple data fetch one by one not in array form pojo object but it need same type of obj to store its data so created new obj
        {
            if (obj.getcName().toUpperCase().contains(query.toUpperCase()))//match the all and search data,toUppercase used to avoid case sensitivity,means it conver t the both in uppercase
            {
              tempcategory.add(obj);
            }
            else {
                tvNoCategoryAvailabel.setVisibility(View.VISIBLE);
            }
            userAdapterGetAllDetails=new UserAdapterGetAllDetails(tempcategory,getActivity());
            lvHospitalCategory.setAdapter(userAdapterGetAllDetails);
        }
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