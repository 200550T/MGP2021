package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class ObstacleEntity implements EntityBase{
    private boolean isDone = false;
    private Bitmap bmp = null, scaledbmp = null;
    int ScreenWidth, ScreenHeight;
    public float xPos = 0;
    public float yPos = 0;

    //private float xPos = 0, yPos = 0;

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
       // bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.player2);

        Random ranGen = new Random();
    }


    @Override
    public void Update(float _dt) {
    }

    @Override
    public void Render(Canvas _canvas) {
        Matrix transform = new Matrix();
        transform.postTranslate(-bmp.getWidth()*0.5f, -bmp.getHeight()*0.5f);
        transform.postTranslate(xPos, yPos);
        _canvas.drawBitmap(bmp, transform, null);
    }

    @Override
    public boolean IsInit() {
        return bmp != null;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
    }

    @Override
    public EntityBase.ENTITY_TYPE GetEntityType() {
        return EntityBase.ENTITY_TYPE.ENT_DEFAULT;
    }

    public static Player Create() {
        Player result = new Player();
        EntityManager.Instance.AddEntity(result, EntityBase.ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
