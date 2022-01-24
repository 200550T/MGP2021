package com.example.mgp2021;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.SurfaceView;

public class EnemyBullet implements EntityBase, Collidable{
    private boolean isDone = false;
    private Bitmap enemyBullet = null;
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
    public String GetType(){return "EnemyBullet";}
    @Override
    public float GetPosX(){return xPos;}
    @Override
    public float GetPosY(){return yPos;}
    @Override
    public float GetRadius(){return imgRadius;}

    // Collision Checker
    @Override
    public void OnHit(Collidable _other){
        if(_other.GetType() == "Player")
        {
            this.SetIsDone(true);
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        //setting up values
        xPos = E_Worm.Instance.GetPosX() - 20.f;
        yPos = E_Worm.Instance.GetPosY() - 60.f;
        yLimit = _view.getHeight() * 0.99f;
        enemyBullet = BitmapFactory.decodeResource(_view.getResources(), R.drawable.enemybullet);
        //AudioManager.Instance.PlayAudio(R.raw.shoot,100);
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()){return;}
        yPos += _dt * 600.f;

        if(yPos > yLimit)
            this.SetIsDone(true);
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(enemyBullet,xPos,yPos, null);
    }

    @Override
    public boolean IsInit() {
        return enemyBullet != null;
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

    public static EnemyBullet Create() {
        EnemyBullet result = new EnemyBullet();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_BULLET);
        return result;
    }
}
