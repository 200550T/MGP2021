package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.SurfaceView;

public class Bullet implements EntityBase {
    private boolean isDone = false;
    private Bitmap bullet = null;
    private float xPos, yPos;
    private SurfaceView view = null;
    //public final static Bullet Instance = new Bullet();

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
        //setting up values
        xPos = DraggablePlayer.Instance.xPos;
        yPos = DraggablePlayer.Instance.yPos;
        //yLimit = _view.getHeight() * 0.5f;
        bullet = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bullet);
        //System.out.print("drawable bullet: " + R.drawable.bullet);
    }

    @Override
    public void Update(float _dt) {
        yPos -= _dt * 300.f;
    }

    @Override
    public void Render(Canvas _canvas) {
        //_canvas.drawBitmap(scaledbmp, xPos, yPos, null); //1st image
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
        return LayerConstants.BULLET_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_BULLET;
    }

    public static Bullet Create() {
        Bullet result = new Bullet();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_BULLET);
        return result;
    }
}