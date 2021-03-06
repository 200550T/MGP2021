package com.example.mgp2021;

// Created by TanSiewLan2021
// Create a GamePage is an activity class used to hold the GameView which will have a surfaceview

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

public class GamePage extends Activity {

    public static GamePage Instance = null;
    Bullet bullet = new Bullet();
    //Bullet bullet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Hide titlebar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // Hide topbar

        Instance = this;

        setContentView(new GameView(this)); // Surfaceview = GameView
    }

    public void ChangeToMenu()
    {
        /*Intent intent = new Intent();
        intent.setClass(this, MainMenu.class);
        startActivity(intent);*/
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // WE are hijacking the touch event into our own system
        int x = (int) event.getX();
        int y = (int) event.getY();

        TouchManager.Instance.Update(x, y, event.getAction());

        return true;
    }

    /*public void CreateBullet()
    {
        Bullet.Create(0);
    }*/
}

