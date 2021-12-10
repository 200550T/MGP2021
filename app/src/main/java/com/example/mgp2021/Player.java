package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class Player implements EntityBase{
    private boolean isDone = false;
    private Bitmap bmp = null, scaledbmp = null;

    int ScreenWidth, ScreenHeight;

    public float xPos = 0;
    public float yPos = 0;

    private float xLimitRight, xLimitLeft, xStart = 0;

    //private float xPos, yPos, offset;
//    private SurfaceView view = null;
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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.player2);

        //setting up values
        xStart = xPos = _view.getWidth() * 0.5f;
        xLimitRight = _view.getWidth() * 0.85f;
        xLimitLeft = _view.getWidth() * 0.15f;
        yPos = _view.getHeight() * 0.8f;
    }


    @Override
    public void Update(float _dt) {
        if (TouchManager.Instance.IsDown() && (TouchManager.Instance.GetPosX() > xStart)){

                xPos += _dt * 300.f;
        }
        else if (TouchManager.Instance.IsDown() && (TouchManager.Instance.GetPosX() < xStart)){

                xPos -= _dt * 300.f;
        }
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
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static Player Create() {
        Player result = new Player();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }

    /*Override
    public float GetPosX() {
        return xPos;
    }

    public float GetPosY() {
        return yPos;
    }


    // T
    // Optional
    //public float GetRadius(){
    // return bmp.getHeight() * 0.5f;
    //}

    public void OnHit(Collidable _other){
        if (_other.GetType() == "SampleEntity")
        {
            // SetIsDone (true);
        }
    }*/
}
