package com.example.kill_the_birds;

import java.util.Date;
//המחלקה האחראית לקבל את התכונות שנשלחים לשרת
public class DataPlayer {
    String name;
    Date date;
    int score;
    public DataPlayer(String name,Date date,int score) {
        this.name = name;
        this.score=score;
        this.date=date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(Date date){this.date=date;}

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public  Date getDate(){return date;}

}
