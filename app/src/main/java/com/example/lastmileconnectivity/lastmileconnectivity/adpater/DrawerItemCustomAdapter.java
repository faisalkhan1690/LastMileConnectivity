package com.example.lastmileconnectivity.lastmileconnectivity.adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.example.lastmileconnectivity.lastmileconnectivity.model.DataModel;

import java.util.ArrayList;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<DataModel> dataList=new ArrayList<>();

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, ArrayList<DataModel> dataList) {

        super(mContext, layoutResourceId, dataList);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position==0){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            View listItem = inflater.inflate(R.layout.user_info_header, parent, false);
//            TextView textViewName = (TextView) listItem.findViewById(R.id.user_name);
            return listItem;
        }else{
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            View listItem = inflater.inflate(layoutResourceId, parent, false);

            ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
            TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

            DataModel folder = dataList.get(position-1);


            imageViewIcon.setImageResource(folder.icon);
            textViewName.setText(folder.name);

            return listItem;
        }

    }

    @Override
    public int getCount() {
        return dataList.size()+1;
    }
}