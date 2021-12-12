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
        //Bullet.Create();
        PauseButtonEntity.Create();
        E_virus.Create();
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

        EntityManager.Instance.Update(_dt);

        //Broken code do not uncomment first
//       if (TouchManager.Instance.IsDown())
//       {
//            DraggablePlayer.Create();
//           this.activity.CreateBullet();
//           Log.d("Test", "works");
//           Bullet.Create();
//       }

//       timer = 10 * _dt;
//       if(timer >= 1)
//       {
//           E_virus.Create();
//           timer = 0;
//       }
    }
    public void setActivity(GamePage activity) {
        this.activity = activity;
    }

}



