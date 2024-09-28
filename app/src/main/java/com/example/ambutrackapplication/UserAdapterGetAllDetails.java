package com.example.ambutrackapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ambutrackapplication.comman.POJOGetAllDetails;
import com.example.ambutrackapplication.comman.URLs;

import java.util.List;

public class UserAdapterGetAllDetails extends BaseAdapter {
    List<POJOGetAllDetails> pojoGetAllDetails;
    Activity activity;

    public UserAdapterGetAllDetails(List<POJOGetAllDetails> list, Activity activity) {
        this.pojoGetAllDetails = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pojoGetAllDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) //Hold the design
    {
        final ViewHolder holder;
        LayoutInflater inflater=(LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);//To forcefully show the layout file in java class
        //This power is given by the System ,so we pass getSystemService method
        if(view==null)//this is for the first time app oened
        {
            holder=new ViewHolder();//Intilaize the viewholder class to use its all widgets,In java class we cant directly find id ,so first made inner class,initialize the widgets in this class
            //then intilaize class in getView method,then inflate design in view,then store all ids in holder and then set these ids to view
           view= inflater.inflate(R.layout.lv_get_all_details, null);
            holder.ivCategoryImage=view.findViewById(R.id.ivCategoryImage);
            holder.tvCategoryName=view.findViewById(R.id.tvCategoryName);
            view.setTag(holder);

        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }
        final POJOGetAllDetails obj=pojoGetAllDetails.get(position);//to one by one data means position taking from pojo class and set it to imageview and textview
      holder.tvCategoryName.setText(obj.getcName());
      Glide.with(activity).
              load("\"http://192.168.113.27:80/AmbuTrackerAPI/images/"+obj.getcImage()).
              skipMemoryCache(true).
              error(R.drawable.image_not_found)
              .into(holder.ivCategoryImage);

        return view;
    }
    class ViewHolder//create one inner class to sore the object of desing view
    {
        ImageView ivCategoryImage;
        TextView tvCategoryName;
    }
}
//BaseAdapter=store/load multiple views in the design,Adapter =collect multiple design and show it
