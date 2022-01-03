package com.bumday.dormitory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin_1_stuinfomng extends FragmentActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_1_stuinfomng);



        //로고 누르면, 메인화면으로 전환
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_1_stuinfomng.this, Admin_0_main.class);
                startActivity(intent);
                finish();
            }
        });
        // EditText ID따기
        final EditText stunum = (EditText)findViewById(R.id.stunum);
        final EditText name = (EditText)findViewById(R.id.name);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText address = (EditText)findViewById(R.id.address);
        final EditText major = (EditText)findViewById(R.id.major);
        final EditText telnum = (EditText)findViewById(R.id.telnum);

        // 빈 데이터 리스트 생성.
        final ArrayList<String> items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listview_text, items) ;
        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        // 생성 버튼 누르면, 아래에 리스트값 추가
        Button confirm_btn = (Button)findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stunums = stunum.getText().toString();
                String names =  name.getText().toString();
                String passwords = password.getText().toString();
                String addresss = address.getText().toString();
                String majors = major.getText().toString();
                String telnums = telnum.getText().toString();

//                int count;
//                count = adapter.getCount();

                // 아이템 추가.
                items.add("학번 :"+stunums+ "/이름 :" +names+ "/비밀번호 :"+passwords+ "/거주지 :"+addresss+ "/전공 :"+majors+ "/연락처 :" +telnums);

                // listview 갱신
                adapter.notifyDataSetChanged();
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
