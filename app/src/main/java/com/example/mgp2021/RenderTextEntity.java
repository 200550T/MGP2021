package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import com.example.mgp2021.E_virus;

public class RenderTextEntity implements EntityBase{

    private boolean isDone = false;

    //Paint
    //Paint paint = new Paint();
    private int red = 0, green = 0, blue = 0; //0-255

    Typeface myfont;
    //use for loading fps so need more parameters
    //we want to load FPS on my screen
    int frameCount;
    long lastTime = 0;
    long lastFPSTime = 0;
    float FPS;

    static int score = 0;
    static int lives = 3;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(),"fonts/venus_rising_rg.ttf");
    }

    @Override
    public void Update(float _dt) {
        //get actual fps
        frameCount++;

        long currentTime = System.currentTimeMillis();
        lastTime = currentTime;

        if(currentTime - lastFPSTime > 1000)
        {
            FPS = (frameCount * 1000.f) / (currentTime - lastFPSTime);
            lastFPSTime = currentTime;
            frameCount = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        Paint paint = new Paint();
        paint. setARGB(255, 255,255,0);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(90);

        Paint paint2 = new Paint();
        paint2. setARGB(255, 255,0,0);
        paint2.setStrokeWidth(200);
        paint2.setTypeface(myfont);
        paint2.setTextSize(70);

        //_canvas.drawText("FPS: " + FPS, 30,80,paint); //for now, default number but can use _view.getwidth/ ?

        _canvas.drawText("Score: " + score, 30,310,paint); //for now, default number but can use _view.getwidth/ ?

        //_canvas.drawText("Lives: " + lives, 30,300,paint); //for now, default number but can use _view.getwidth/ ?

    }

    @Override
    public boolean IsInit() {
        return true;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer){
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static RenderTextEntity Create(){
        RenderTextEntity result = new RenderTextEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
