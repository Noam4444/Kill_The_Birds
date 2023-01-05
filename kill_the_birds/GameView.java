package com.example.kill_the_birds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//הדף שמטפל בנראות ובמהלכי המשחק והאלגוריתמטיקה שלו
public class GameView extends SurfaceView implements  Runnable {
    private Thread thread;
    public boolean isPlaying, isGameOver = false;
    private int screenX, screeny, score = 0, level = 0;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Bird[] birds;
    private SharedPreferences prefs;
    private Random random;// Random to speed Bird
    private SoundPool soundPool;
    private List<Bullt> bullets;
    private Flight flight;//variable of Flight
    private int sound, countLife, counterShoot = 2;
    private Background background1, background2;
    private GameActivity activity;
    private Bitmap[] life;
    private String res;

    public GameView(GameActivity activity, int screenX, int screenY, String res) {
        super(activity);
        this.activity = activity;
        StartGame(activity, screenX, screenY, res, 3);//פונקציה שמאתחלת את המשחק 3 פעמים משום שאנו נותנים למשתמש 3 פסילות
    }


    @Override
    //פונקציה שמריצה את 3 הפונקציות העיקריות כל עוד המשתנה במצב אמת
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }

    }

    //מעדכן ברגע אמת מה קורה במהלך המשחק
    private void update() {
        background1.x -= 10 * screenRatioX;
        background2.x -= 10 * screenRatioX;
        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }
        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }
        if (flight.isGoingUp)
            flight.y -= 30 * screenRatioY;
        else
            flight.y += 30 * screenRatioY;
        if (flight.y < 0)
            flight.y = 0;
        if (flight.y > screeny - flight.hight)
            flight.y = screeny - flight.hight;
        List<Bullt> trash = new ArrayList<>();//A variable that helps out from List Bullt
        for (Bullt bullt : bullets) {
            if (bullt.x > screenX) // אם יצא מגבולות המסך של הרוחב
                trash.add(bullt);//insert buul to trash
            bullt.x += 50 * screenRatioX;
            for (Bird bird : birds) {
                if (Rect.intersects(bird.getCollisionShape(), bullt.getCollisionShape())) { //האם הייריה והציפור באותו  נפגשו
                    if (bird.counterShoot == 1) {
                        score++;// point  plus if kill Bird
                        bird.x = -500;//פה הציפור נעלמת
                        bullt.x = screenX + 500;
                        bird.wasShoot = true;
                    } else {
                        bullt.x = screenX + 500;//הציפור צריכה לחזור
                        bird.counterShoot--;//מוריד מהקאונטרשוט אם הוא לא הגיע ל1
                    }
                }
            }
        }
        if (score > 10 && level == 1) {//אם הרגתי 5 ציפורים עולים לרמה 2
            level++;
            for (Bird bird : birds) {
                bird.Level2();
            }
        }


        if (score > 20 && level == 2) {//אם הרגתי 10 ציפורים עולים לרמה 3
            level++;
            for (Bird bird : birds) {
                bird.Level3();
            }
        }
        if (score > 30 && level == 3) {//אם הרגתי 20 ציפורים עולים לרמה 4
            level++;
            for (Bird bird : birds) {
                bird.Level4();
            }
        }

        for (Bullt bullt : trash)
            bullets.remove(bullt);//remove from bullt
        for (Bird bird : birds) {
            bird.x -= bird.speed;
            if (bird.x + bird.width < 0) {

                int bound = (int) (30 * screenRatioX);
                bird.speed = random.nextInt(bound);//speed random of birds

                if (bird.speed < 10 * screenRatioX)
                    bird.speed = (int) (10 * screenRatioX);
                bird.x = screenX;
                bird.y = random.nextInt(screeny - bird.height);
                bird.wasShoot = false;
                bird.SetShootperLevel();
            }
            if (Rect.intersects(bird.getCollisionShape(), flight.getCollisionShape())) {//If the bird and the player meting finish Game.
                isGameOver = true;
                return;
            }
        }
    }

    //הפונקציה שמציירת את המשחק עצמו
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            // 3 life in the game
            for (int i = 0; i < countLife; i++) {
                canvas.drawBitmap(life[i], 2000 + i * -100, 0, null);//מאתחל את שלושת הלבבות בהפרשים של מרחק 100 לפט בין כל אחד
            }


            for (Bird bird : birds)
                canvas.drawBitmap(bird.getBird(), bird.x, bird.y, paint);
            canvas.drawText(score + "", screenX / 2f, 164, paint);

            if (isGameOver && countLife == 1) {
                isPlaying = false;
                canvas.drawBitmap(flight.getDead(), flight.x, flight.y, paint);
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                waitBeforeExiting();
                return;
            } else if (isGameOver && countLife > 0) {
                isGameOver = false;
                StartGame(activity, screenX, screeny, res, --countLife);

            }


            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);
            for (Bullt bullt : bullets)
                canvas.drawBitmap(bullt.bullt, bullt.x, bullt.y, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    // הפונקציה שעושה כמה פעולות לאחר שהמשחק הסתיים
    private void waitBeforeExiting() {
        try {
            Thread.sleep(3000);
            Intent intent = new Intent(activity, Finish.class);
            intent.putExtra("flight", res);
            intent.putExtra("key_score", score);
            if(activity.currentPos == 1)
                activity.song2.pause();
            activity.startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //פונקציה ששומרת את הנקודות שהשחקן צבר במהלך המשחק
    private void saveIfHighScore() {
        if (prefs.getInt("higescore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("higescore", score);
            editor.apply();
        }
    }

    //מתי שהמשתמש יוצא מהאפליקציה הפונקציה תפעל
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //פונקצייה שממשיכה את המשחק
    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }


    public void puse() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //הגדרות של לחיצות במקומות שונים במסך במהלך המשחק
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                if (event.getX() > screenX / 2)
                    flight.tooshoot++;
                break;
        }
        return true;
    }

    //הגדרות של היריות והסאונד יריות במהלך המשחק
    public void newBullt() {
        if (!prefs.getBoolean("ismute", false))
            soundPool.play(sound, 1, 1, 0, 0, 1);
        Bullt bullt = new Bullt(getResources());
        bullt.x = flight.x + flight.width;
        bullt.y = flight.y + (flight.hight / 2);
        bullets.add(bullt);
    }

    public void StartGame(GameActivity activity, int screenX, int screenY, String res, int count) {

        this.activity = activity;

        countLife = count;

        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);//The page that shares the information
        this.screenX = screenX;
        this.screeny = screenY;
        this.res = res;
        life = new Bitmap[countLife];
        for (int i = 0; i < countLife; i++) {
            life[i] = BitmapFactory.decodeResource(getResources(), R.drawable.lifeg1);
        }


        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // תנאי לפי גירסת השמע
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build(); // to start before soundpool

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(activity, R.raw.shoot, 1);

        background1 = new Background(screenX, screeny, getResources());
        background2 = new Background(screenX, screeny, getResources());




        flight = new Flight(this, screeny, getResources(), res);

        bullets = new ArrayList<>();//initialization variable List.
        background2.x = screenX;

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        level = 1;


        birds = new Bird[4];//initialization variable arry Bird
        for (int i = 0; i < 4; i++) {
            Bird bird = new Bird(getResources());//insert Birds
            birds[i] = bird;
        }
        random = new Random();
    }

}

