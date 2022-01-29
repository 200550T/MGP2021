package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class Powerup implements EntityBase, Collidable{
    private boolean isDone = false;
    public float xPos, yPos;
    private Sprite Powerup = null;
    public static Powerup Instance = null;
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
    public String GetType(){return "Powerup";}
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
            DraggablePlayer.Instance.IncreaseLevel();
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        Powerup = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.powerup01_2),3,2, 10 );

        //randomise position
        int min = (int) (_view.getWidth() * 0.2);
        int max = (int) (_view.getWidth() * 0.8);

        yPos = _view.getHeight() * 0.05f;
        //xPos = ranGen.nextInt((max - min) + 1) + min;
        //xPos = _view.getWidth() * 0.5f;
        xPos = ranGen.nextFloat() * _view.getWidth();
        Instance = this;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()){return;}
        yPos += _dt * 310.f;
        imgRadius = Powerup.GetWidth() * 0.5f;
        Powerup.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas) {
        Powerup.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return Powerup != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() { return LayerConstants.COLLECTIBLES_LAYER; } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public EntityBase.ENTITY_TYPE GetEntityType() {
        return EntityBase.ENTITY_TYPE.ENT_POWERUP;
    }

    public static Powerup Create() {
        Powerup result = new Powerup();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_POWERUP);
        return result;
    }
}
