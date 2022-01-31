package com.example.mgp2021;

import android.graphics.Canvas;
import android.view.SurfaceView;
import android.util.Log;
import java.util.Random;

public class E_Worm implements EntityBase, Collidable{
    private boolean isDone = false;
    public float xPos, yPos;
    private Sprite wormSprite = null;
    public static E_Worm Instance = null;
    private float imgRadius = 0.0f;
    //public static boolean isHit = false;


    private float enemyShootTimer = 0.0f;

    Random ranGen = new Random(); //wk 8=>Random Generator

    @Override
    public boolean IsDone() {
        return isDone;
    }
    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }


    @Override
    public String GetType(){return "Enemy";}
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
            MainGameSceneState.Instance.EnemyShoot = false;
        }
        else if (_other.GetType() == "Bullet")
        {
            MainGameSceneState.Instance.EnemyShoot = false;
            this.SetIsDone(true);
            AudioManager.Instance.PlayAudio(R.raw.hit, 100);
            RenderTextEntity.score += 1;
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        MainGameSceneState.Instance.EnemyShoot = true;
        wormSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.worm),1,1, 1 );
        imgRadius = wormSprite.GetWidth() * 0.5f;

        //randomise position
        float min = 0.1f;
        float max = 0.9f;

        yPos = _view.getHeight() * 0.05f;
        //xPos = ranGen.nextFloat() * _view.getWidth();
        xPos = (min + ranGen.nextFloat() * (max - min)) * _view.getWidth();
        Instance = this;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()){return;}
        yPos += _dt * 280.f;
        wormSprite.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        wormSprite.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return wormSprite != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.ENEMY_LAYER;
    } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public EntityBase.ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_ENEMY;
    }

    public static E_Worm Create() {
        E_Worm result = new E_Worm(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_ENEMY);
        return result;
    }
}
