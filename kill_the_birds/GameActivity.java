package com.example.kill_the_birds;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
//הדף אחראי לפעולות שקוראות במהלך המשחק
public class GameActivity extends AppCompatActivity {
    public static final int REQUEST_CODE =1;
    private  GameView gameView;
    MediaPlayer song2;
    int currentPos;
    FrameLayout game;
    RelativeLayout GameButtons;
    Intent settingIntent;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        Intent intent=getIntent();
        String res= intent.getStringExtra("select_flight");
        gameView=new GameView(this,point.x,point.y,res);
        settingIntent = new Intent(GameActivity.this, GameSettings.class);
        song2=MediaPlayer.create(getApplicationContext(), R.raw.musicgame2);
        currentPos = 0;


        game = new FrameLayout(this);
        GameButtons = new RelativeLayout(this);
        ImageButton btsetting = new ImageButton(this);
        btsetting.setBackgroundResource(R.drawable.setting);


        btsetting.setId(123456); //good to set ID to some random number because defaults new button id's all to same number
        btsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(settingIntent,REQUEST_CODE);
            }
        });
        //Define the layout parameter for the button to wrap the content for both width and height
        RelativeLayout.LayoutParams b1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        GameButtons.setLayoutParams(params);

        GameButtons.addView(btsetting);
        b1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        b1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        btsetting.setLayoutParams(b1);


        game.addView(gameView);
        game.addView(GameButtons);
        setContentView(game);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("myData1")) {
                if (data.getExtras().getInt("myData1")==1 && currentPos == 0){
                    currentPos=1;
                    song2.start();
                }
                if(data.getExtras().getInt("myData1")==0  && currentPos == 1){
                    currentPos=0;
                     song2.pause();
                }
                if(data.getExtras().getInt("myData1")==3 ){
                    currentPos=3;
                    song2.pause();
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(data.getExtras().getInt("myData1")==4 ){
                    currentPos=4;
                    song2.pause();
                    Intent intent=new Intent(this,ChoosePlayers.class);
                    startActivity(intent);
                    finish();
                }
               else if(data.getExtras().getInt("myData1")==5){
                    Intent intent5=new Intent(GameActivity.this,GameActivity.class);
                }
            }
        }
    }

    @Override
    protected  void  onPause(){
        super.onPause();
        gameView.puse();
    }
    @Override
    protected  void  onResume(){
        super.onResume();
        gameView.resume();
    }


}