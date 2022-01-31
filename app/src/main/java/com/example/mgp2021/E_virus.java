package com.example.mgp2021;

import android.graphics.Canvas;
import android.view.SurfaceView;
import android.util.Log;

import java.util.Random;

public class E_virus implements EntityBase, Collidable{
    private boolean isDone = false;
    public float xPos, yPos;
    private Sprite virusSprite = null;
    public static E_virus Instance = null;
    private float imgRadius = 0.0f;
    private float shootTimer = 0.0f;

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
        }
        else if (_other.GetType() == "Bullet")
        {
            this.SetIsDone(true);
            AudioManager.Instance.PlayAudio(R.raw.hit, 100);
            RenderTextEntity.score += 1;
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        virusSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.virus_sprite2),2,1, 2 );
        imgRadius = virusSprite.GetWidth() * 0.5f;

        //randomise position
        float min = 0.1f;
        float max = 0.9f;

        yPos = _view.getHeight() * 0.05f;
        //xPos = ranGen.nextInt((max - min) + 1) + min;
        //xPos = ranGen.nextFloat() * _view.getWidth();
        xPos = (min + ranGen.nextFloat() * (max - min)) * _view.getWidth();
        Instance = this;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()){return;}
        yPos += _dt * 300.f;
        virusSprite.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        virusSprite.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return virusSprite != null;
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

    public static E_virus Create() {
        E_virus result = new E_virus(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_ENEMY);
        return result;
    }
}
