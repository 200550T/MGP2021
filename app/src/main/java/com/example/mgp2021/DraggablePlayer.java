package com.example.mgp2021;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;
public class DraggablePlayer implements EntityBase, Collidable{
    private boolean isDone = false;
    private float xPos, yPos = 0.0f;
    private Sprite playerSprite = null;
    public static DraggablePlayer Instance = null;

    private float ShootTimer = 0.0f;
    private float imgRadius = 0.0f;


    @Override
    public boolean IsDone() {
        return isDone;
    }
    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }


    @Override
    public String GetType(){return "Player";}
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
            // health code here
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        playerSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.player2),1,1, 8 );
        xPos = _view.getWidth() * 0.5f;
        yPos = _view.getHeight() * 0.85f;
        Instance = this;
    }

    @Override
    public void Update(float _dt) {

        playerSprite.Update(_dt);
        //wk8=>Dragging code --
        if (TouchManager.Instance.HasTouch())  // Touch and drag
        {
            // Check collision with the player
            imgRadius = playerSprite.GetWidth() * 0.5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius) )
            {
                xPos = TouchManager.Instance.GetPosX();
            }
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        playerSprite.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return playerSprite != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.PLAYER_LAYER;
    } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PLAYER;
    } //Week 8=>Update ent type

    public static DraggablePlayer Create() {
        DraggablePlayer result = new DraggablePlayer(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PLAYER); //wk8=>update ent tyep
        return result;
    }
}
