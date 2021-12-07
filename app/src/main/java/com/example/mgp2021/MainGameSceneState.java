package com.example.mgp2021;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;

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

        PauseButtonEntity.Create();
        // Example to include another Renderview for Pause Button
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

       if (TouchManager.Instance.IsDown())
               //&& TouchManager.Instance.GetPosX() >= 0
               //&& TouchManager.Instance.GetPosX() <= 50
               //&& TouchManager.Instance.GetPosY() >= 0
               //&& TouchManager.Instance.GetPosY() <= 50)
           {
            //Example of touch on screen in the main game to trigger back to Main menu
            // StateManager.Instance.ChangeState("Mainmenu"); /// Need to comment off cos we not going to touch screen to go else where!!!
       }
    }
}



