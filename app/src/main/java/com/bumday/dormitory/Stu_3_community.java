package com.bumday.dormitory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumday.dormitory.Adapter.COM_ListViewAdapter;
import com.bumday.dormitory.Adapter.ListViewAdapter;
import com.bumday.dormitory.DataBase.Community;
import com.bumday.dormitory.DataBase.Posts;
import com.bumday.dormitory.DataBase.StudentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Stu_3_community extends FragmentActivity {

    private String key;
    private Community community;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_3_community);

        Intent main_intent = getIntent();
        key = main_intent.getStringExtra("학번");
        community = (Community) main_intent.getParcelableExtra("Community");

        ArrayList<Posts> com_list = community.listInfo();
        COM_ListViewAdapter adapter = new COM_ListViewAdapter(Stu_3_community.this,com_list);
        ListView listview = (ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);
        for(int i=0;i<com_list.size();i++)
            adapter.addItem(com_list.get(i));


        //로고 누르면, 메인화면으로 전환
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_3_community.this, Stu_0_main.class);
                intent.putExtra("학번",key);
                startActivity(intent);
                finish();
            }
        });

        Button write_btn = (Button) findViewById(R.id.write_btn);
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_3_community.this, Stu_3_community_write.class);
                intent.putExtra("학번",key);
                intent.putExtra("Community",community);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Stu_3_community.this, Stu_0_main.class);
        intent.putExtra("학번",key);
        startActivity(intent);
    }
}
