package com.example.kill_the_birds;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    int x=0,y=0;//מידות לגודל המסך
    Bitmap background;//תמונת רקע
    Background(int screenX, int screenY, Resources res){
        background= BitmapFactory.decodeResource(res,R.drawable.sky5);
        background=Bitmap.createScaledBitmap(background,screenX,screenY,false);
    }
}
