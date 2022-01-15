package com.example.mgp2021;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    private float BulletTimer = 0.0f;
    public float Firerate = 0.05f;
    public int PlayerLevel = 0;
    private GamePage activity = null;
    public static MainGameSceneState Instance = null;
    Bullet bullet = new Bullet();

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        Instance = this;
        RenderBackground.Create();
        RenderTextEntity.Create();
        DraggablePlayer.Create();
        //Powerup.Create();
        PauseButtonEntity.Create();
        AudioManager.Instance.PlayAudio(R.raw.bgm,100);
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

        String scoreText = String.format("Highscore:%d", GameSystem.Instance.GetIntFromSave("Highscore"));
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        _canvas.drawText(scoreText,10,10,paint);
    }

    @Override
    public void Update(float _dt) {
        //if(GameSystem.Instance.GetIsPaused()){return;}
        if(GameSystem.Instance.GetIsPaused() == false)
        {
            BulletTimer += _dt;
            if(BulletTimer >= Firerate)
            {
                Bullet.Create();
                BulletTimer = 0;
            }

            timer += 1 * _dt;
            if(timer >= 1.2)
            {
                E_virus.Create();
                timer = 0;
            }
        }

        EntityManager.Instance.Update(_dt);
    }
    public void setActivity(GamePage activity) {
        this.activity = activity;
    }

}



