package com.example.mgp2021;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.SurfaceView;

public class Bullet implements EntityBase, Collidable {
    private boolean isDone = false;
    private Bitmap bullet = null;
    private float xPos, yPos, yLimit;
    private float imgRadius = 0.0f;
    private SurfaceView view = null;
    public float GetImgRadius(){return imgRadius;}
    @Override
    public boolean IsDone() {
        return isDone;
    }
    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }
    public void SetXpos(float newXpos) {xPos = newXpos;}


    @Override
    public String GetType(){return "Bullet";}
    @Override
    public float GetPosX(){return xPos;}
    @Override
    public float GetPosY(){return yPos;}
    @Override
    public float GetRadius(){return imgRadius;}

    // Collision Checker
    @Override
    public void OnHit(Collidable _other){
        if(_other.GetType() == "Enemy")
        {
            this.SetIsDone(true);
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        //setting up values
        xPos = DraggablePlayer.Instance.GetPosX() - 20.f;
        yPos = DraggablePlayer.Instance.GetPosY() - 60.f;
        yLimit = _view.getHeight() * 0.01f;
        bullet = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bullet);
        AudioManager.Instance.PlayAudio(R.raw.shoot,10);
    }

    @Override
    public void Update(float _dt) {
        yPos -= _dt * 300.f;

        if(yPos < yLimit)
            this.SetIsDone(true);
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bullet,xPos,yPos, null);
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