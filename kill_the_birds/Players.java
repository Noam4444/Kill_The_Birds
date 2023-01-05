package com.example.kill_the_birds;
import java.util.Date;
//המחלקה האחראית לקבוע את התכונות שנקבל מהשרת
public class Players {
    String name;
    Date date;
    int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Date getDate(){return date;}
}
