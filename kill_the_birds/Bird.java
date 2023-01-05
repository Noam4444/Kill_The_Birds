package com.example.kill_the_birds;
import static com.example.kill_the_birds.GameView.screenRatioX;
import static com.example.kill_the_birds.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
//במחלקה יש 4 ציפורים בכל פעם על המסך שיופעיעו
//יש את הגדרת השלבים(4 שלבים) שבכל שלב יוגדר איזה ציפור תהיה וכמה יריות נצטרך לירות כדי להרוג אותה
public class Bird {
    public int speed=20;
    public boolean wasShoot =true;
    int x=0,y,width,height,birdCounter=1, counterShoot, level;//הקאונטרשוט סופר כמות יריות. הלבל אומר לי באיזה שלב אני נמצא
    private  Resources r1;//משתנה גלובלי שעוזר לי לקבל את המידות וצורת הציפורים להמשך השלבים של המשחק
    Bitmap bird1,bird2,bird3,bird4;
    Bird(Resources res){
        bird1 = BitmapFactory.decodeResource(res, R.drawable.birdpinq1);
        bird2 = BitmapFactory.decodeResource(res, R.drawable.birdpinq2);
        bird3 = BitmapFactory.decodeResource(res, R.drawable.birdpinq3);
        bird4 = BitmapFactory.decodeResource(res, R.drawable.birdpinq4);

        level =1;
        counterShoot =GetShootperLevel();

        r1=res;
        width=bird1.getWidth();
        height=bird1.getHeight();

        width/=6;
        height/=6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird1=Bitmap.createScaledBitmap(bird1,width,height,false);
        bird2=Bitmap.createScaledBitmap(bird2,width,height,false);
        bird3=Bitmap.createScaledBitmap(bird3,width,height,false);
        bird4=Bitmap.createScaledBitmap(bird4,width,height,false);

        y=-height;
    }
    public void Level2(){
        bird1 = BitmapFactory.decodeResource(r1, R.drawable.birdgreen1);
        bird2 = BitmapFactory.decodeResource(r1, R.drawable.birdgreen2);
        bird3 = BitmapFactory.decodeResource(r1, R.drawable.birdgreen3);
        bird4 = BitmapFactory.decodeResource(r1, R.drawable.birdgreen4);

        level =2;
        counterShoot =GetShootperLevel();

        width=bird1.getWidth();
        height=bird1.getHeight();

        width/=6;
        height/=6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird1=Bitmap.createScaledBitmap(bird1,width,height,false);
        bird2=Bitmap.createScaledBitmap(bird2,width,height,false);
        bird3=Bitmap.createScaledBitmap(bird3,width,height,false);
        bird4=Bitmap.createScaledBitmap(bird4,width,height,false);
    }
    public void Level3(){
        bird1 = BitmapFactory.decodeResource(r1, R.drawable.birdpurpul1);
        bird2 = BitmapFactory.decodeResource(r1, R.drawable.birdpurpul2);
        bird3 = BitmapFactory.decodeResource(r1, R.drawable.birdpurpul3);
        bird4 = BitmapFactory.decodeResource(r1, R.drawable.birdpurpul4);

        level =3;
        counterShoot =GetShootperLevel();

        width=bird1.getWidth();
        height=bird1.getHeight();

        width/=6;
        height/=6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird1=Bitmap.createScaledBitmap(bird1,width,height,false);
        bird2=Bitmap.createScaledBitmap(bird2,width,height,false);
        bird3=Bitmap.createScaledBitmap(bird3,width,height,false);
        bird4=Bitmap.createScaledBitmap(bird4,width,height,false);
    }
    public void Level4(){
        bird1 = BitmapFactory.decodeResource(r1, R.drawable.bird1);
        bird2 = BitmapFactory.decodeResource(r1, R.drawable.bird2);
        bird3 = BitmapFactory.decodeResource(r1, R.drawable.bird3);
        bird4 = BitmapFactory.decodeResource(r1, R.drawable.bird4);

        level =4;
        counterShoot =GetShootperLevel();

        width=bird1.getWidth();
        height=bird1.getHeight();

        width/=6;
        height/=6;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        bird1=Bitmap.createScaledBitmap(bird1,width,height,false);
        bird2=Bitmap.createScaledBitmap(bird2,width,height,false);
        bird3=Bitmap.createScaledBitmap(bird3,width,height,false);
        bird4=Bitmap.createScaledBitmap(bird4,width,height,false);
    }
    public int GetShootperLevel(){//מקבל כמה יריות לכל שלב
        switch (level){
            case 1:return 1;
            case 2:return 2;
            case 3:return 4;
            case 4:return 5;
            default:return 1;
        }
    }
    public  void SetShootperLevel(){//כמה יריות לשלב
        switch (level){
            case 1:
                counterShoot =1;
            break;
            case 2:
                counterShoot =2;
            break;
            case 3:
                counterShoot =4;
            break;
            case 4:
                counterShoot =5;
            break;
            default:
                counterShoot =1;
        }
    }

    Bitmap getBird(){
        if(birdCounter==1){
            birdCounter++;
            return  bird1;
        }
        if(birdCounter==2){
            birdCounter++;
            return  bird2;
        }
        if(birdCounter==3){
            birdCounter++;
            return  bird3;
        }
        birdCounter=1;
        return  bird4;
    }
    Rect getCollisionShape(){
        return  new Rect(x,y,x+width,y+height);
    }//border of screen
}
