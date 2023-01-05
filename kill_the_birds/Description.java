package com.example.kill_the_birds;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//הדף אחרי לתת הסבר על המשחק באמצעות שני תיבות טקסט אחת בעברית ואחת באנגלית
public class Description extends AppCompatActivity {
    Button btback;
    RadioButton rb1, rb2;
    RadioGroup rg;
    TextView t2,t1;
    String res,res2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_description);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rg = findViewById(R.id.rg);
        t1 = findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        res=t1.getText().toString();
        res2=t2.getText().toString();// שומר את הטקסט של התיבת טקסט במחרוזת
        t2.setVisibility(t2.INVISIBLE);//מחליט שהיא לא תיראה
        btback = findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Description.this, MainActivity.class));
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group1, int checkedId1) {
                switch (checkedId1) {
                    case R.id.rb1:
                        t1.setText(res);
                        break;
                    case R.id.rb2:
                        t1.setText(res2);
                        break;
                }
            }
        });

    }



    }

