package com.example.kill_the_birds;
//המחלקה של היריות אחראית לייצוג היריות של השחקן במשחק ולנראות שלהם
import static com.example.kill_the_birds.GameView.screenRatioX;
import static com.example.kill_the_birds.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
public class Bullt {
    int x, y, width, height;
    Bitmap bullt;
    Bullt(Resources res){
        bullt = BitmapFactory.decodeResource(res, R.drawable.bullet);

        width = bullt.getWidth();
        height = bullt.getHeight();

        width /= 4;
        height /= 4;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bullt = Bitmap.createScaledBitmap(bullt, width, height, false);
    }
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }//border of screen
}
