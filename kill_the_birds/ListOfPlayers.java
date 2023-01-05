package com.example.kill_the_birds;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ListOfPlayers extends AppCompatActivity {
//הדף שאחראי לרשימת 10 שחקנים הכי טובים בסדר ממויין מהגבוהה לנמוך
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Players>list;
    Players players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_list_of_players);
        recyclerView=findViewById(R.id.userList);
        database= FirebaseDatabase.getInstance().getReference("data");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myAdapter =new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        Query queryRef = database.orderByChild("score").limitToLast(10);//ממיין לפי 10 עם הכי הרבה נקודות
        queryRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) { //לקבל מהשרת את כל הנתנוים
                    players=dataSnapshot.getValue(Players.class); //בכל פעם שנקבל נשים במשתנה
                    list.add(players);//נוסיף אותו לרשימה שיצרנו
                }
                Collections.reverse(list);//ממיין אותה בהפוך לא מהנמוך לגבוהה אלא מהגבוהה לנמוך

                myAdapter.notifyDataSetChanged();
        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void back(View view) {
        Intent intent=new Intent(ListOfPlayers.this,MainActivity.class);
        startActivity(intent);
    }
}