package com.example.mgp2021;

import android.graphics.Canvas;
import android.view.SurfaceView;
import android.util.Log;
import java.util.Random;

public class E_block implements EntityBase, Collidable{
    private boolean isDone = false;
    public float xPos, yPos;
    private Sprite blockSprite = null;
    public static E_block Instance = null;
    private float imgRadius = 0.0f;

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
    }

    @Override
    public void Init(SurfaceView _view) {
        blockSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.block2),1,1, 1 );
        imgRadius = blockSprite.GetWidth() * 0.5f;

        //randomise position
        float min = 0.1f;
        float max = 0.9f;

        yPos = _view.getHeight() * 0.05f;
        xPos = (min + ranGen.nextFloat() * (max - min)) * _view.getWidth();
        Instance = this;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()){return;}
        yPos += _dt * 310.f;
        blockSprite.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        blockSprite.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return blockSprite != null;
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

    public static E_block Create() {
        E_block result = new E_block(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_ENEMY);
        return result;
    }
}
