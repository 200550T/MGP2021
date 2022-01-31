package com.example.mgp2021;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float virustimer = 0.0f;
    private float BulletTimer = 0.0f;
    private float blocktimer = 0.0f;
    private float wormtimer = 0.0f;
    public float Firerate = 0.0f;
    public float BossFirerate = 0.0f;
    public float TimeToSpawnPW = 0.0f;
    public boolean EnemyShoot = false;
    public boolean bossPlasmaShoot = false;
    public float bossShootTimer = 0.0f;
    public float enemyShootTimer = 0.0f;
    private float PowerupTimer = 0.0f;
    private GamePage activity = null;
    //public static boolean StartGame;
    public static MainGameSceneState Instance = null;
    Bullet bullet = new Bullet();

    //stages
    public static int StageNo = 1;
    private int scoreToChangeStage = 0;
    private int currentScore = 0;
    boolean bossSpawnOnce = false;


    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        StageNo = 1;
        currentScore = 0;
        scoreToChangeStage = 20;
        EnemyShoot = false;
        bossPlasmaShoot = false;
        bossSpawnOnce = false;

        Instance = this;
        RenderBackground.Create();
        RenderTextEntity.Create();
        DraggablePlayer.Create();
        HealthBar.Create();
        PauseButtonEntity.Create();

        AudioManager.Instance.MuteAudio(R.raw.air);
        //play bgm
        AudioManager.Instance.PlayAudio(R.raw.bgm,1);

    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

    private void CreateBoss(boolean canSpawn)
    {
        if(canSpawn && !bossSpawnOnce)
        {
            E_VirusBoss.Create();
            bossSpawnOnce = true;
        }
    }


    @Override
    public void Update(float _dt) {
        //update stages
        currentScore = RenderTextEntity.score;
        if(currentScore >= scoreToChangeStage)
        {
            StageNo += 1;
            scoreToChangeStage += scoreToChangeStage;
        }


        //if(GameSystem.Instance.GetIsPaused()){return;}
        if(GameSystem.Instance.GetIsPaused() == false)
        {
            //spawn player bullet
            BulletTimer += _dt;
            if(BulletTimer >= Firerate)
            {
                Bullet.Create();
                BulletTimer = 0;
            }

            PowerupTimer += _dt;
            if(PowerupTimer >= TimeToSpawnPW)
            {
                Powerup.Create();
                PowerupTimer = 0;
            }

            //stages
            if(StageNo == 1)
            {
                //Log.d("stage 1", "Stage 1 go");
                virustimer += 1 * _dt;
                if(virustimer >= 1.5)
                {
                    E_virus.Create();
                    virustimer = 0;
                }

                blocktimer += 1 * _dt;
                if(blocktimer >= 2.3)
                {
                    E_block.Create();
                    blocktimer = 0;
                }
            }
            else if (StageNo == 2)
            {
                //Log.d("stage 2", "Stage 2 go");

                virustimer += 1 * _dt;
                if(virustimer >= 1.2)
                {
                    E_virus.Create();
                    virustimer = 0;
                }

                blocktimer += 1 * _dt;
                if(blocktimer >= 2)
                {
                    E_block.Create();
                    blocktimer = 0;
                }

                wormtimer += 1 * _dt;
                if(wormtimer >= 4)
                {
                    E_Worm.Create();
                    wormtimer = 0;
                }
            }
            else if (StageNo == 3)
            {
                //Log.d("stage 3", "Stage 3 go");

                virustimer += 1 * _dt;
                if(virustimer >= 1)
                {
                    E_virus.Create();
                    virustimer = 0;
                }

                blocktimer += 1 * _dt;
                if(blocktimer >= 1.8)
                {
                    E_block.Create();
                    blocktimer = 0;
                }

                wormtimer += 1 * _dt;
                if(wormtimer >= 2.7)
                {
                    E_Worm.Create();
                    wormtimer = 0;
                }
            }
            else if (StageNo == 4)
            {
                virustimer = 0;
                wormtimer = 0;
                blocktimer = 0;
                //Log.d("stage 4", "Stage 4 go");
                CreateBoss(true);
            }

            //check if enemy can shoot
            if(EnemyShoot == true)
            {
                enemyShootTimer += 1 * _dt;
                if(enemyShootTimer >= 1.5)
                {
                    EnemyBullet.Create();
                    enemyShootTimer = 0;
                }
            }

            if(bossPlasmaShoot)
            {
                if(E_VirusBoss.bossPhase == 1)
                {
                    BossFirerate = 1.5f;
                }
                else if(E_VirusBoss.bossPhase == 2)
                {
                    BossFirerate = 1.f;
                }
                else if(E_VirusBoss.bossPhase == 3)
                {
                    BossFirerate = 0.7f;
                }


                bossShootTimer += 1 * _dt;
                if (bossShootTimer >= BossFirerate) {
                    BossSmallPlasma.Create();
                    bossShootTimer = 0;
                }
            }
        }

        EntityManager.Instance.Update(_dt);
    }
    public void setActivity(GamePage activity) {
        this.activity = activity;
    }

}



