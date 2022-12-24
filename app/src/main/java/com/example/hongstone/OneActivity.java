package com.example.hongstone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OneActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        DBHelper helper = new DBHelper(this, "EZ", null, 1);

        ListView listView = (ListView) findViewById(R.id.list_view2);

        ItemAd adapter = new ItemAd(this);
        listView.setAdapter(adapter);

        ArrayList<Item> dataList = new ArrayList<>();

        dataList = helper.selecta("SELECT tfcwkerMvmnCnterNm, latitude, longitude FROM MOVE;");

        adapter.setItemList(dataList);
        adapter.notifyDataSetChanged();

        ArrayList<Item> finalDataList = dataList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(OneActivity.this, MainActivity.class); // 메인 -> sub

                String a = finalDataList.get(position).gettfcwkerMvmnCnterNm();
                String b = finalDataList.get(position).getlatitude();
                String c = finalDataList.get(position).getlongitude();

                intent.putExtra("data", a); // data란 이름(키)으로 person 데이터 담아서 보내기
                intent.putExtra("la", b);
                intent.putExtra("lo", c);

                startActivity(intent); // 화면전환
            }
        });
    }
}
