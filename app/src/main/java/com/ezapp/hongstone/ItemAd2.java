package com.ezapp.hongstone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ezapp.hongstone.R;

import java.util.ArrayList;

public class ItemAd2 extends BaseAdapter {
    ArrayList<Item> dataList;
    Context context;

    public ItemAd2(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.item, null);

        TextView centertext = (TextView) v.findViewById(R.id.center_text);


        centertext.setText(dataList.get(i).gettfcwkerMvmnCnterNm());
        centertext.setText(dataList.get(i).getlatitude());
        centertext.setText(dataList.get(i).getlongitude());
        return v;
    }

    public ArrayList<Item> getItemList() {
        return dataList;
    }

    public void setItemList(ArrayList<Item> dataList) {
        this.dataList = dataList;
    }
}
