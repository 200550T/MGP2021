package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class Ship implements EntityBase {
    private boolean isDone = false;
    private Bitmap bmp = null, scaledbmp = null;
    int ScreenWidth, ScreenHeight;
    private float xPos, yPos, offset;
    private SurfaceView view = null;
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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_1);
        //Find the surfaceview size or screensize
        metrics = _view.getResources().getDisplayMetrics();
        ScreenHeight = metrics.heightPixels / 5;
        ScreenWidth = metrics.widthPixels / 5;
        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);
    }

    @Override
    public void Update(float _dt) {
        tfx.preRotate(20 * _dt,metrics.widthPixels / 10,metrics.heightPixels / 10);
        tfx.postTranslate(10*_dt,10*_dt);
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(scaledbmp, xPos, yPos, null); //1st image
        _canvas.drawBitmap(scaledbmp, xPos + ScreenWidth, yPos, null); //2nd image (to show its continuous)
        _canvas.drawBitmap(scaledbmp, tfx, null);
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

    public static Ship Create() {
        Ship result = new Ship();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}