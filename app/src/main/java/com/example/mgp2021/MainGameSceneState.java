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
    private GamePage activity = null;
    Bullet bullet = new Bullet();

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        RenderTextEntity.Create();
        DraggablePlayer.Create();
        Bullet.Create();
        PauseButtonEntity.Create();
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

    @Override
    public void Update(float _dt) {

        BulletTimer += _dt;
        if(BulletTimer >= 0.8)
        {
            Bullet.Create();
            BulletTimer = 0;
        }

        timer += 1 * _dt;
        if(timer >= 2)
        {
           E_virus.Create();
           timer = 0;
        }

        EntityManager.Instance.Update(_dt);
    }
    public void setActivity(GamePage activity) {
        this.activity = activity;
    }

}



