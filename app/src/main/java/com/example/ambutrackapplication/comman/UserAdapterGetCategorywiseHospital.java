package com.example.ambutrackapplication.comman;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ambutrackapplication.R;

import java.util.List;

public class UserAdapterGetCategorywiseHospital extends BaseAdapter {
   List<POJOGetCategorywiseHospital> pojoGetCategorywiseHospitals;
   Activity activity;

    public UserAdapterGetCategorywiseHospital(List<POJOGetCategorywiseHospital> pojoGetCategorywiseHospitals, Activity activity) {
        this.pojoGetCategorywiseHospitals = pojoGetCategorywiseHospitals;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pojoGetCategorywiseHospitals.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(view==null)
        {
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.lv_get_all_categorywise_hospital,null);
            holder.ivCategorywisehospitalImage=view.findViewById(R.id.ivCategoryWiseHospitalHopsitalImage);
            holder.tvCategorywisehospitalName=view.findViewById(R.id.tvCategoryWiseHospitalName);
            holder.tvCategorywisehospitalRating=view.findViewById(R.id.tvCategoryWiseHospitalRating);
            holder.tvCategorywisehospitalAddress=view.findViewById(R.id.tvCategoryWiseHospitalAddress);
            holder.tvCategorywisehospitalCityName=view.findViewById(R.id.tvCategoryWiseHospitalCityName);
            holder.tvCategorywisehospitalContactNo=view.findViewById(R.id.tvCategoryWiseHospitalContactNo);
            holder.tvCategorywisehospitalFeedback=view.findViewById(R.id.tvCategoryWiseHospitalFeedback);
            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder) view.getTag();
        }
       final POJOGetCategorywiseHospital obj=pojoGetCategorywiseHospitals.get(position);
        holder.tvCategorywisehospitalName.setText(obj.getStrhospitalname());
        holder.tvCategorywisehospitalRating.setText(obj.getStrhospitalrating());
        holder.tvCategorywisehospitalAddress.setText(obj.getStrhospitaladdress());
        holder.tvCategorywisehospitalCityName.setText(obj.getStrhospitalcityname());
        holder.tvCategorywisehospitalContactNo.setText(obj.getStrhospitalcontactno());
        holder.tvCategorywisehospitalFeedback.setText(obj.getStrhospitalfeedback());
        Glide.with(activity).load("http://192.168.113.27:80/AmbuTrackerAPI/images"+obj.getStrhospitalimage())
                .skipMemoryCache(true).error(R.drawable.image_not_found).into(holder.ivCategorywisehospitalImage);
        return null;
    }
class ViewHolder
{
    ImageView ivCategorywisehospitalImage;
    TextView tvCategorywisehospitalName,tvCategorywisehospitalRating,tvCategorywisehospitalAddress,tvCategorywisehospitalCityName,tvCategorywisehospitalContactNo,tvCategorywisehospitalFeedback;
}
}
