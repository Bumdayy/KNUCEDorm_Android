package com.bumday.dormitory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Admin_2_scanbarcord extends FragmentActivity {
    private IntentIntegrator barcodeScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_2_scanbarcord);


        //스캔기능 실행관련 명령어
        barcodeScan = new IntentIntegrator(this);
        barcodeScan.setOrientationLocked(false);
        barcodeScan.setPrompt("(관생증 바코드를 스캔해주세요)");
        barcodeScan.initiateScan();
    }

    //스캔기능 관련 함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                finish();
                // todo
            } else {
                Toast.makeText(this, "읽어들인 값: " + result.getContents(), Toast.LENGTH_LONG).show();
                finish();
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

//뒤로가기 두번 연속=종료 [여기선필요없을듯]
//    private long time = 0;
//
//    @Override
//    public void onBackPressed() {
//        if (System.currentTimeMillis() - time >= 2000) {
//            time = System.currentTimeMillis();
//            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
//        } else if (System.currentTimeMillis() - time < 2000) {
//            finish();
//        }
//    }
}

