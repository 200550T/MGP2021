package com.example.mgp2021;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class E_VirusBoss implements EntityBase, Collidable{
    private boolean isDone = false;
    public float xPos, yPos;
    private Sprite virusBossSprite = null;
    public static E_VirusBoss Instance = null;
    private float imgRadius = 0.0f;
    private float shootTimer = 0.0f;
    private boolean stopY = false;
    public int bossHealth = 100;

    private boolean startLoop = false;
    private boolean moveleft = false;
    private float yLimit = 0.0f;
    private float xLimit1 = 0.0f;
    private float xLimit2 = 0.0f;
    public static int bossPhase = 0;
    private int moveSpeed = 0;

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
            //this.SetIsDone(true);
        }
        else if (_other.GetType() == "Bullet")
        {
            bossHealth -= 2;
            //this.SetIsDone(true);
            //AudioManager.Instance.PlayAudio(R.raw.hit, 100);
            //RenderTextEntity.score += 1;
        }
    }

    @Override
    public void Init(SurfaceView _view) {
        //virusBossSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.virusboss),18,6, 15 );
        bossPhase = 1;
        moveSpeed = 100;
        bossHealth = 100;

        virusBossSprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.finalbosssprite2),2,2, 8 );
        imgRadius = virusBossSprite.GetWidth() * 0.5f;

        yLimit = _view.getHeight() * 0.265f;
        xLimit1 = _view.getWidth() * 0.8f;
        xLimit2 = _view.getWidth() * 0.2f;

        yPos = _view.getHeight() * 0.001f;
        xPos = _view.getWidth() * 0.5f;
        Instance = this;
    }

    @Override
    public void Update(float _dt) {
        //if paused
        if(GameSystem.Instance.GetIsPaused()){return;}
        virusBossSprite.Update(_dt);

        if(yPos <= yLimit)
        {
            yPos += _dt * 100.f;
        }
        else
            startLoop = true;

        if(startLoop)
        {
            MainGameSceneState.Instance.bossPlasmaShoot = true;
            if(xPos >= xLimit1 && !moveleft)
            {
                moveleft = true;
            }

            if(xPos <= xLimit2 && moveleft)
            {
                moveleft = false;
            }

            if(moveleft == true)
            {
                xPos -= moveSpeed*_dt;
            }
            else
            {
                xPos += moveSpeed*_dt;
            }
        }

        //boss phases
        if(bossHealth <= 67)
        {
            bossPhase = 2;
            moveSpeed = 250;
        }
        if (bossHealth <= 34)
        {
            bossPhase = 3;
            moveSpeed = 400;
        }

        //if boss dead
        if(bossHealth <= 0)
        {
            this.SetIsDone(true);
            RenderTextEntity.score += 100;
            MainGameSceneState.Instance.bossPlasmaShoot = false;
            GameSystem.Instance.SetIsPaused(true);

            //show end game dialog
            EndGameDialog EndCover = new EndGameDialog();
            EndCover.show(GamePage.Instance.getFragmentManager(), "EndGame");
            if (EndGameDialog.IsShown)
                return;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        virusBossSprite.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return virusBossSprite != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.ENEMY_LAYER;
    } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public EntityBase.ENTITY_TYPE GetEntityType() {
        return EntityBase.ENTITY_TYPE.ENT_ENEMY;
    }

    public static E_VirusBoss Create() {
        E_VirusBoss result = new E_VirusBoss(); //wek 8
        EntityManager.Instance.AddEntity(result, EntityBase.ENTITY_TYPE.ENT_ENEMY);
        return result;
    }
}
