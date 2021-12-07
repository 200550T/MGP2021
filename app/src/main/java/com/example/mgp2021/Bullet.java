package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;


public class Bullet implements EntityBase{
    private boolean isDone = false;
    private Bitmap bullet = null, scaledbmp = null;

    int ScreenWidth, ScreenHeight;

    public float xPos = 0;
    public float yPos = 0;

    private float yLimit, xStart = 0;

    Matrix tfx = new Matrix();
    DisplayMetrics metrics;

    //check if anything to do with entity (use for pause)
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
        bullet = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bullet);

        //setting up values
        //xStart = xPos = ;
        yPos = _view.getHeight() * 0.8f;
    }


    @Override
    public void Update(float _dt) {
        float imgRadius = bullet.getHeight() * 0.5f;
    }

    @Override
    public void Render(Canvas _canvas) {
        Matrix transform = new Matrix();
        transform.postTranslate(-bullet.getWidth()*0.5f, -bullet.getHeight()*0.5f);
        transform.postTranslate(xPos, yPos);
        _canvas.drawBitmap(bullet, transform, null);
    }

    @Override
    public boolean IsInit() {
        return bullet != null;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static Bullet Create() {
        Bullet result = new Bullet();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
