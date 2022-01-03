package com.bumday.dormitory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumday.dormitory.Adapter.ListViewAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Stu_2_sleepout extends FragmentActivity {

    // 오늘날짜 설정
    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    String year = yearFormat.format(currentTime);
    String month = monthFormat.format(currentTime);
    String day = dayFormat.format(currentTime);
    Button startdate;
    Button enddate;
    private DatePickerDialog.OnDateSetListener startdatecallbackMethod;
    private DatePickerDialog.OnDateSetListener enddatecallbackMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_2_sleepout);

        startdate = (Button) findViewById(R.id.startdate);
        enddate = (Button) findViewById(R.id.enddate);
        this.InitializeListener();

        startdate.setText(year + "/" + month + "/" + day);
        enddate.setText(year + "/" + month + "/" + day);

        //로고 누르면, 메인화면으로 전환
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_2_sleepout.this, Stu_0_main.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // 날짜설정시 이벤트 (달은 0부터시작하므로 +1)
    public void InitializeListener() {
        startdatecallbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        };
        enddatecallbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                enddate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        };
    }


    // 시작날짜버튼 리스너
    public void startdateOnClickHandler(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, startdatecallbackMethod, Integer.parseInt(year), (Integer.parseInt(month) - 1), Integer.parseInt(day));
        dialog.show();
    }

    // 끝날짜버튼 리스너
    public void enddateOnClickHandler(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, enddatecallbackMethod, Integer.parseInt(year), (Integer.parseInt(month) - 1), Integer.parseInt(day));
        dialog.show();
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
