package com.example.ambutrackapplication.comman;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.ambutrackapplication.R;

public class NetworkChangeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!NetworkDetails.isconnectedtointernet(context))
        {
            AlertDialog.Builder ad=new AlertDialog.Builder(context);
            View layout_dialog= LayoutInflater.from(context).inflate(R.layout.interconnectivitydialog,null);
            ad.setView(layout_dialog);
            AlertDialog alertDialog=ad.create();
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            AppCompatButton btnRetry=layout_dialog.findViewById(R.id.btnRetry);
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    onReceive(context, intent); 
                }
            });
        }
        else
        {
            Toast.makeText(context,"You are Connected to Internet",Toast.LENGTH_SHORT).show();
        }
    }
}
