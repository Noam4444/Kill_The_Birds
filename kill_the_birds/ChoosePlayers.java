package com.example.kill_the_birds;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
//הדף אחראי לאיזה סוג של שחקן ייבחר ולהעביר את המידע של השחקן שנבחר לדף של המשחק שעובד
public class ChoosePlayers extends AppCompatActivity {
RadioGroup rg;
RadioButton rb1,rb2,rb3,rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_choose_players);

        rg= findViewById(R.id.rg);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(ChoosePlayers.this, GameActivity.class);//conductor page
                String res=Check();
                intent.putExtra("select_flight",res);
                startActivity(intent);

            }
        });
    }
    public String Check() {//  באמצעות הכפתור שנבחר אדע להעביר לדף המשחק איזה שחקן נבחר שלשם אנו נעביר מחרוזת שבה יש את המספר שנבחר

        switch (rg.getCheckedRadioButtonId()) {
            case R.id.rb2:
                 return "2";

            case R.id.rb3:
                 return "3";

            case R.id.rb4:
                  return "4";

            default:
                return "1";

        }
    }

}