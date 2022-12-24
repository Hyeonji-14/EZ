package com.example.hongstone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAd extends BaseAdapter {
    ArrayList<Item> dataList;
    Context context;

    public ItemAd(Context context) {
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
        return v;
    }

    public ArrayList<Item> getItemList() {
        return dataList;
    }

    public void setItemList(ArrayList<Item> dataList) {
        this.dataList = dataList;
    }
}
