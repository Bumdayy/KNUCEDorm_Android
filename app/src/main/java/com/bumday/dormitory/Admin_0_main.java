package com.bumday.dormitory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Admin_0_main extends FragmentActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_0_main);

        //1: 학생정보관리 버튼 누르면, 학생정보관리 액티비티로 전환
        TextView stuinfomng = (TextView)findViewById(R.id.stuinfomng);
        stuinfomng.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_1_stuinfomng.class);
                startActivity(intent);
            }
        });

        //2: 관생증스캐너 버튼 누르면, 관생증스캔 액티비티로 전환
        TextView scanbarcord = (TextView)findViewById(R.id.scanbarcord);
        scanbarcord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_2_scanbarcord.class);
                startActivity(intent);
            }
        });

        //X버튼 누르면, 로그아웃
        ImageView logout = (ImageView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //뒤로가기 두번 연속=종료
    private long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            finish();
        }
    }
}
