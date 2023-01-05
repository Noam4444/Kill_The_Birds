package com.example.kill_the_birds;

import static com.example.kill_the_birds.GameView.screenRatioX;
import static com.example.kill_the_birds.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
//המחלקה אחראית להגדיר את נראות המידות וגודל השחקן שנבחר ולהגדיר את ה5 מצבים שבמהלך המשחק שהמטוס יורה בהם
public class Flight {
    int tooshoot = 0;
    boolean isGoingUp = false;
    int x, y, width, hight, wingCounter = 0, shootCounter = 1;
    Bitmap flight1, flight2, shoot1, shoot2, shoot3, shoot4, shoot5, dead;
    public GameView gameView;

    Flight(GameView gameview, int screenY, Resources res, String flightid) {
        this.gameView = gameview;
        flight1 = GetImageById1(res, flightid);
        flight2 = GetImageById2(res, flightid);
        width = flight1.getWidth();
        hight = flight1.getHeight();
        width /= 4;
        hight /= 4;
        width = (int) (width * screenRatioX);
        hight = (int) (hight * screenRatioY);

        flight1 = Bitmap.createScaledBitmap(flight1, width, hight, false);
        flight2 = Bitmap.createScaledBitmap(flight2, width, hight, false);

        switch (flightid){
            case "2":{

                shoot1=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot1red),width,hight,false);
                shoot2=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot2red),width,hight,false);
                shoot3=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot3red),width,hight,false);
                shoot4=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot4red),width,hight,false);
                shoot5=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot5red),width,hight,false);
                dead=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.deadred),width,hight,false);
                break;
            }
            case "3":{
                shoot1=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shootblue1),width,hight,false);
                shoot2=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shootblue2),width,hight,false);
                shoot3=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shootblue3),width,hight,false);
                shoot4=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shootblue4),width,hight,false);
                shoot5=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shootblue5),width,hight,false);
                dead=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.deadblue),width,hight,false);
                break;
            }
            case "4":{
                shoot1=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot1),width,hight,false);
                shoot2=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot2),width,hight,false);
                shoot3=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot3),width,hight,false);
                shoot4=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot4),width,hight,false);
                shoot5=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot5),width,hight,false);
                dead=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.dead),width,hight,false);
                break;
            }
            default:{
                System.out.println(flightid);
                shoot1=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot1purpel),width,hight,false);
                shoot2=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot2purpel),width,hight,false);
                shoot3=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot3purpel),width,hight,false);
                shoot4=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot4purpel),width,hight,false);
                shoot5=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.shoot5purpel),width,hight,false);
                dead=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res,R.drawable.deadpurple),width,hight,false);
                break;
            }
        }

        y = screenY / 2;
        x = (int) (64 * screenRatioX);
    }

        public Bitmap getFlight () {
            if (tooshoot != 0) {
                if (shootCounter == 1) {
                    shootCounter++;
                    return shoot1;
                }
                if (shootCounter == 2) {
                    shootCounter++;
                    return shoot2;
                }
                if (shootCounter == 3) {
                    shootCounter++;
                    return shoot3;
                }
                if (shootCounter == 4) {
                    shootCounter++;
                    return shoot4;
                }
                shootCounter = 1;
                tooshoot--;
                gameView.newBullt();
                return shoot5;
            }
            if (wingCounter == 0) {
                wingCounter++;
                return flight1;
            }
            wingCounter--;
            return flight2;
        }
        Bitmap GetImageById1 (Resources res, String id){
            switch (id) {

                case "2":
                    return BitmapFactory.decodeResource(res, R.drawable.shoot1red);
                case "3":
                    return BitmapFactory.decodeResource(res, R.drawable.shootblue1);
                case "4":
                    return BitmapFactory.decodeResource(res, R.drawable.ft1_1);
                default:
                    return BitmapFactory.decodeResource(res, R.drawable.shoot1purpel);
            }
        }
        Bitmap GetImageById2 (Resources res, String id){
            switch (id) {

                case "2":
                    return BitmapFactory.decodeResource(res, R.drawable.shoot1red);
                case "3":
                    return BitmapFactory.decodeResource(res, R.drawable.shootblue1);
                case "4":
                    return BitmapFactory.decodeResource(res, R.drawable.ft1_1);
                default:
                    return BitmapFactory.decodeResource(res, R.drawable.shoot1purpel);
            }
        }

        Rect getCollisionShape () {
            return new Rect(x, y, x + width, y + hight);
        } //border screen
        Bitmap getDead () {
            return dead;
        }
    }

