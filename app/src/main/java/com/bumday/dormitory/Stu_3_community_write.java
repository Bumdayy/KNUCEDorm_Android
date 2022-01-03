package com.bumday.dormitory;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumday.dormitory.DataBase.Community;
import com.bumday.dormitory.DataBase.NetWorkUtil;
import com.bumday.dormitory.DataBase.PHPRequest;
import com.bumday.dormitory.DataBase.Posts;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Stu_3_community_write extends FragmentActivity {

    String key;
    Community community;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_3_community_write);
        NetWorkUtil.setNetworkPolicy();


        final EditText com_title = findViewById(R.id.com_title);
        final EditText com_post = findViewById(R.id.com_post);

        key = getIntent().getStringExtra("학번");
        community = new Community(Stu_3_community_write.this);

        //로고 누르면, 메인화면으로 전환
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_3_community_write.this, Stu_0_main.class);
                intent.putExtra("학번",key);
                startActivity(intent);
                finish();
            }
        });

        //목록 누르면, 커뮤니티 목록으로 전환(에러)
        Button list_btn = (Button) findViewById(R.id.list_btn);
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_3_community_write.this, Stu_3_community.class);
                startActivity(intent);
                finish();
            }
        });

        //작성버튼 이벤트
        Button write_btn = (Button) findViewById(R.id.write_btn);
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PHPRequest Insert = new PHPRequest("http://purpleu.shop/community.php");
                    String result = Insert.PhPtest(String.valueOf(com_title.getText()), String.valueOf(com_post.getText()), key);
                    if (result != null) {
                        SimpleDateFormat simple = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.KOREA);
                        Date date = new Date();
                        String currentDate = simple.format(date);
                        Posts posts= new Posts();
                        posts.setNum(community.listInfo().size());
                        posts.setTitle(com_title.getText().toString());
                        posts.setId(key);
                        posts.setTime(currentDate);
                        posts.setPost(com_post.getText().toString());
                        community.addList(posts);
                        Toast.makeText(getApplicationContext(), "작성 완료", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "작성 실패", Toast.LENGTH_SHORT).show();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Stu_3_community_write.this, Stu_3_community.class);
                intent.putExtra("학번", key);
                intent.putExtra("Community", community);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Stu_3_community_write.this, Stu_3_community.class);
        intent.putExtra("학번", key);
        intent.putExtra("Community", community);
        startActivity(intent);
        finish();
    }
}
