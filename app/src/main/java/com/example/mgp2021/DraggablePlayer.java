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
    public int triesCount = 10;

    public static int currLvl = 0;
    public int GetCurrLevel() {return currLvl;}
    public void SetCurrLevel(int newLevel) {currLvl = newLevel;}
    public void IncreaseLevel() {currLvl += 1;}
    private Vibrator _vibrator;

    private float xLimit = 0.0f;
    private float xLimit1 = 0.0f;

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
            currLvl = 0;
            RenderTextEntity.lives -= 1;
            startVibrate();
            triesCount -= 1;
        }
        else if (_other.GetType() == "EnemyBullet")
        {
            currLvl = 0;
            RenderTextEntity.lives -= 1;
            startVibrate();
            triesCount -= 1;
        }
        else if (_other.GetType() == "BossPlasma")
        {
            currLvl = 0;
            RenderTextEntity.lives -= 3;
            startVibrate();
            triesCount -= 1;
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        currLvl = 0;
        xLimit = _view.getWidth() * 0.9f;
        xLimit1 = _view.getWidth() * 0.1f;
        playerSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.player2),1,1, 8 );
        xPos = _view.getWidth() * 0.5f;
        yPos = _view.getHeight() * 0.85f;
        Instance = this;
        _vibrator = (Vibrator)_view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
    }

    public void startVibrate(){
        if(Build.VERSION.SDK_INT >= 26)
        {
            _vibrator.vibrate(VibrationEffect.createOneShot(100, 200));
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

        if(GameSystem.Instance.GetIsPaused()){return;}

        playerSprite.Update(_dt);
        //wk8=>Dragging code --
        if (TouchManager.Instance.HasTouch())  // Touch and drag
        {
            // Check collision with the player
            imgRadius = playerSprite.GetWidth() * 0.5f;
            if(xPos <= xLimit && xPos >= xLimit1)
            {
                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius))
                {
                    xPos = TouchManager.Instance.GetPosX();
                }
            }
            if (xPos >= xLimit)
            {
                xPos = xLimit;
            }
            if (xPos <= xLimit1)
            {
                xPos = xLimit1;
            }

        }

        //if no lives left trigger gameover
        if (RenderTextEntity.lives <= 0)
        {
            // _canvas.drawText("GAME OVER", 30,290,paint2); //for now, default number but can use _view.getwidth/ ?
            //StateManager.Instance.ChangeState("Mainmenu");

            //pause the game
            GameSystem.Instance.SetIsPaused(true);

            //check if its a new highscore
            int Highscore = GameSystem.Instance.GetIntFromSave("Highscore");
            //set and save new highscore
            if(RenderTextEntity.score > Highscore)
            {
                GameSystem.Instance.SaveEditInt("Highscore", RenderTextEntity.score);
            }

            //show game over dialog
            if (GameOverDialog.IsShown)
                return;
            GameOverDialog GameOver = new GameOverDialog();
            GameOver.show(GamePage.Instance.getFragmentManager(), "GameOver");
        }

        //powerup
        if(currLvl == 0)
        {
            MainGameSceneState.Instance.TimeToSpawnPW = 10.0f;
            MainGameSceneState.Instance.Firerate = 0.9f;
        }
        else if(currLvl == 1)
        {
            MainGameSceneState.Instance.TimeToSpawnPW = 20.0f;
            MainGameSceneState.Instance.Firerate = 0.7f;
        }
        else if(currLvl == 2)
        {
            MainGameSceneState.Instance.TimeToSpawnPW = 35.0f;
            MainGameSceneState.Instance.Firerate = 0.5f;
        }
        else if(currLvl == 3)
        {
            MainGameSceneState.Instance.TimeToSpawnPW = 45.0f;
            MainGameSceneState.Instance.Firerate = 0.3f;
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
