package com.example.kill_the_birds;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Date;

//הדף אחראי לניהול סוף המשחק לאן המשתמש רוצה ללכת האם לחזור לדף הראשי או לרשימת השחקנים או למשחק חוזר
//בדף הסיום יהיה שליחת השם והנקודות של השחקן לשרת
public class Finish extends AppCompatActivity {
Button btexit,btdata,btlist;
int score=0;
TextView tvscore;
EditText etname;
DatabaseReference data;
    String r,t;
boolean IsIn=false;
    private  GameActivity gameActivity;
    private  GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_finish);
        setContentView(R.layout.activity_finish);
        btexit=findViewById(R.id.btexit);
        btdata=findViewById(R.id.btdata);
        etname =findViewById(R.id.etname);
        tvscore =findViewById(R.id.tvscore);
        btlist=findViewById(R.id.btlist);
        Intent intent=getIntent();//יוצר משתנה שיקבל את הנקודות
        score=intent.getIntExtra("key_score",0);//מקבל את הנקודות מהדף של המשחק אחרי הפסילה האחרונה
        tvscore.setText(String.valueOf(score));//ממיר אותו לטקסט כדי שאוכל להראות אותו בתור טקסט בטקסט ויו

        data= FirebaseDatabase.getInstance().getReference().child("data");

        btlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoList=new Intent(Finish.this, ListOfPlayers.class);
                startActivity(GoList);
            }
        });
        //חזרה לעמוד של בחירת שחקן

        //הגדרת יציאה מהאפליקציה על ידי העברה לדף הראשי ומשם מתבצעת היציאה מהאפלקיציה
        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Finish.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etname.length()>0){
                    insertData();
                    btdata.setVisibility(View.INVISIBLE);
                }
                else if (etname.length()<1){
                    Toast.makeText(Finish.this,"Please enter a name",Toast.LENGTH_LONG).show();

                }


            }
        });

    }


    private void insertData(){//הכנסת מידע לשרת
        Date date = null;
        String name= etname.getText().toString();//מקבל את הטקסט שהמשתמש הכניס לתיבת טקסט
        if (etname.length()>0) {
            DataPlayer dataPlayer = new DataPlayer(name, date, score);//מעדכן את האובייקט שלי שיקבל את השם והנקודות
            data.push().setValue(dataPlayer);//שולח לדאטה בייס את השם והנקודות
            Toast.makeText(Finish.this, "Your details have been received", Toast.LENGTH_SHORT).show();//שולח לו הודעה שהנתונים נשלחו בהצלחה
        }
    }


}