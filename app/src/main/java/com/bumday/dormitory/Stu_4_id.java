package com.bumday.dormitory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumday.dormitory.DataBase.Student;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class Stu_4_id extends FragmentActivity {

    //학생 객체 생성
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_4_id);

        // 학생 객체 데이터 전달
        student=getIntent().getParcelableExtra("학생정보");

        TextView major = findViewById(R.id.st_major);
        TextView name = findViewById(R.id.st_name);
        TextView id = findViewById(R.id.st_id);
        TextView room = findViewById(R.id.st_room);

        // 관생증 정보 setting
        major.setText(student.getMajor());
        name.setText(student.getName());
        id.setText(student.getId());
        room.setText(student.getDom()+" "+student.getRoom());

        // 관생증 바코드 setting [학번을 > 바코드로]
        ImageView iv_barcord = findViewById(R.id.iv_barcord);
        String strBarcode = student.getId();
        Bitmap barcode = createBarcod(strBarcode);
        iv_barcord.setImageBitmap(barcode);
        iv_barcord.invalidate();

        //로고 누르면, 메인화면으로 전환
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_4_id.this, Stu_0_main.class);
                intent.putExtra("학번",student.getId());
                startActivity(intent);
                finish();
            }
        });


    }

    // 바코드 생성코드
    public Bitmap createBarcod(String code){

        Bitmap bitmap = null;
        MultiFormatWriter gen = new MultiFormatWriter();
        try{
            final int WIDTH = 840;
            final int HEIGHT = 260;
            BitMatrix bytemap = gen.encode(code, BarcodeFormat.CODE_128, WIDTH, HEIGHT);
            bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
            for(int i = 0; i< WIDTH ; ++i)
                for (int j = 0 ; j < HEIGHT ; ++j){
                    bitmap.setPixel(i, j, bytemap.get(i,j) ? Color.BLACK : Color.WHITE);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    //뒤로가기 두번 연속=종료
    private long time = 0;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Stu_4_id.this, Stu_0_main.class);
        intent.putExtra("학번",student.getId());
        startActivity(intent);
        finish();
    }
}
