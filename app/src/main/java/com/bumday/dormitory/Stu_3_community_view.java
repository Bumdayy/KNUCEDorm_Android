package com.bumday.dormitory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumday.dormitory.DataBase.Community;
import com.bumday.dormitory.DataBase.Posts;

public class Stu_3_community_view extends FragmentActivity {
    Community community;
    Posts posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_3_community_view);

        Intent main_intent = getIntent();
        posts = main_intent.getParcelableExtra("게시글정보");
        community = new Community(this);

        TextView post_name = (TextView)findViewById(R.id.post_name);
        TextView post_time = (TextView)findViewById(R.id.post_time);
        TextView post_title = (TextView)findViewById(R.id.post_title);
        TextView post_post = (TextView)findViewById(R.id.post_post);

        post_name.setText(posts.getName());
        post_time.setText(posts.getTime());
        post_title.setText(posts.getTitle());
        post_post.setText(posts.getPost());

        //로고 누르면, 메인화면으로 전환
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_3_community_view.this, Stu_0_main.class);
                intent.putExtra("학번",posts.getId());
                startActivity(intent);
                finish();
            }
        });

        Button list_btn = (Button) findViewById(R.id.list_btn);
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_3_community_view.this, Stu_3_community.class);
                intent.putExtra("학번",posts.getId());
                intent.putExtra("Community",community);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Stu_3_community_view.this, Stu_3_community.class);
        intent.putExtra("학번",posts.getId());
        intent.putExtra("Community",community);
        startActivity(intent);
        finish();
    }
}
