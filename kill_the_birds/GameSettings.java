package com.example.kill_the_birds;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public  class GameSettings extends AppCompatActivity {
    //הדף אחראי על כפתור ההגדרות במהלך המשחק לטפל באירועים שונים
    Intent data = new Intent();
    int checkMusic;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesetting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        checkMusic=5;
    }


    public void btresume(View view) {
        data.putExtra("myData1", checkMusic);
        setResult(RESULT_OK, data);
        finish();
    }

    public void btexit(View view) {
        data.putExtra("myData1", 3);
        setResult(RESULT_OK, data);
        finish();
    }

    public void playmusic(View  v) { checkMusic =1;}


    public void pousemusic(View view) {
        checkMusic =0;
    }

    public void btrestart(View view) {
        data.putExtra("myData1", 4);
        setResult(RESULT_OK, data);
        finish();
    }

}