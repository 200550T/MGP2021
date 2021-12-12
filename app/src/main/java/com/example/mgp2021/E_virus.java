package com.example.mgp2021;

import android.graphics.Canvas;
import android.view.SurfaceView;
import android.util.Log;

import java.util.Random;

public class E_virus implements EntityBase{
    private boolean isDone = false;
    public float xPos, yPos;
    private Sprite virusSprite = null;
    public static E_virus Instance = null;
    private float imgRadius = 0.0f;

    Random ranGen = new Random(); //wk 8=>Random Generator
    public float GetPosX(){return xPos;}
    public float GetPosY(){ return yPos;}
    public float GetImgRadius(){return imgRadius;}

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
        virusSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.virus_sprite2),2,1, 2 );
        imgRadius = virusSprite.GetWidth() * 0.5f;

        //randomise position
        int min = (int) (_view.getWidth() * 0.2);
        int max = (int) (_view.getWidth() * 0.8);

        yPos = _view.getHeight() * 0.05f;
        xPos = ranGen.nextInt((max - min) + 1) + min;
        //xPos = ranGen.nextFloat() * _view.getWidth();
        Instance = this;
    }

    @Override
    public void Update(float _dt) {
        yPos += _dt * 150.f;

        virusSprite.Update(_dt);
        // Check collision with the player
        if (Collision.SphereToSphere(DraggablePlayer.Instance.GetPosX(), DraggablePlayer.Instance.GetPosY(),DraggablePlayer.Instance.GetImgRadius()
                , xPos, yPos, imgRadius) )
        {
            this.SetIsDone(true);
            //Log.d("check","Collision with player");
        }
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
