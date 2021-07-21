package com.example.textmaze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ShowImageActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_start, btn_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        btn_start = findViewById(R.id.btn_start);
        btn_end = findViewById(R.id.btn_end);

        btn_start.setOnClickListener(this);
        btn_end.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_end:
                finish();
                break;

            case R.id.btn_start:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                finish();   //<== 액티비티 전환하고 현재 액티비티는 종료
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy호출");
    }
}