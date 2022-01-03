package com.bumday.dormitory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class Stu_1_id extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_1_id);


        TextView major = findViewById(R.id.st_major);
        TextView name = findViewById(R.id.st_name);
        TextView id = findViewById(R.id.st_id);
        TextView room = findViewById(R.id.st_room);


        //로고 누르면, 메인화면으로 전환
        TextView logo = (TextView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_1_id.this, Stu_0_main.class);
                startActivity(intent);
                finish();
            }
        });


    }

    // 바코드 생성코드
//    public Bitmap createBarcod(String code){
//
//        Bitmap bitmap = null;
//        MultiFormatWriter gen = new MultiFormatWriter();
//        try{
//            final int WIDTH = 840;
//            final int HEIGHT = 260;
//            BitMatrix bytemap = gen.encode(code, BarcodeFormat.CODE_128, WIDTH, HEIGHT);
//            bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
//            for(int i = 0; i< WIDTH ; ++i)
//                for (int j = 0 ; j < HEIGHT ; ++j){
//                    bitmap.setPixel(i, j, bytemap.get(i,j) ? Color.BLACK : Color.WHITE);
//                }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return bitmap;
//    }

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
