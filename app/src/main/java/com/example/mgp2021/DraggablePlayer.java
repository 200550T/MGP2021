package com.example.mgp2021;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.util.Random;
public class DraggablePlayer implements EntityBase, Collidable{
    private boolean isDone = false;
    private float xPos, yPos = 0.0f;
    private Sprite playerSprite = null;
    public static DraggablePlayer Instance = null;
    private float imgRadius = 0.0f;

    private int currLvl = 0;
    public int GetCurrLevel() {return currLvl;}
    public void SetCurrLevel(int newLevel) {currLvl = newLevel;}
    public void IncreaseLevel() {currLvl += 1;}
    private Vibrator _vibrator;

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
            RenderTextEntity.lives -= 1;
            startVibrate();
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        playerSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.player2),1,1, 8 );
        xPos = _view.getWidth() * 0.5f;
        yPos = _view.getHeight() * 0.85f;
        Instance = this;
        _vibrator = (Vibrator)_view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
    }

    public void startVibrate(){
        if(Build.VERSION.SDK_INT >= 26)
        {
            _vibrator.vibrate(VibrationEffect.createOneShot(150, 10));
        }
        else{
            long pattern[] = {0,50,0};
            _vibrator.vibrate(pattern, -1);
        }
    }

    public void stopVibrate(){
        _vibrator.cancel();
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

        if(currLvl == 0)
        {
            MainGameSceneState.Instance.Firerate = 0.8f;
        }
        else if(currLvl == 1)
        {
            MainGameSceneState.Instance.Firerate = 0.4f;
        }
/*        else if(currLvl == 2)
        {
            MainGameSceneState.Instance.Firerate = 0.5f;
            MainGameSceneState.Instance.PlayerLevel = 2;
        }*/
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
