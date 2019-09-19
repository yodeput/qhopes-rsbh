package com.qtasnim.qhopes.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qtasnim.qhopes.R;
import com.qtasnim.qhopes.models.profilpasienmodel;

import java.util.ArrayList;

public class ProfilAdapter extends ArrayAdapter<profilpasienmodel> implements View.OnClickListener {

    private ArrayList<profilpasienmodel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtnorekmed;


    }

    public ProfilAdapter(ArrayList<profilpasienmodel> data, Context context) {
        super(context, R.layout.view_datarow_menu_hariini, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        profilpasienmodel dataModel=(profilpasienmodel)object;

        switch (v.getId())
        {
            case R.id.item_info:
               // Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
               //         .setAction("No action", null).show();

           //    Snackbar.make(v, "Release date" + dataModel.getName(), )
           //     break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        profilpasienmodel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }



        // Return the completed view to render on screen
        return convertView;
    }
}
