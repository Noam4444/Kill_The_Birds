package com.example.kill_the_birds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//הדף הראשי של המשחק שבו מכוונים לאן לנווט
public class MainActivity extends AppCompatActivity {
Button btdescription,btlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btdes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Description.class));//מעבר לדף ההוראות המשחק
            }
        });
        findViewById(R.id.choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ChoosePlayers.class));//מעבר לדף בחירת שחקן ומשם לתחילת המשחק
            }
        });
        findViewById(R.id.btlist).setOnClickListener(new View.OnClickListener() {//מעבר לדף הרשימה של ה10 שחקנים עם הכי הרבה נקודות
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListOfPlayers.class));
            }
        });

    }
}