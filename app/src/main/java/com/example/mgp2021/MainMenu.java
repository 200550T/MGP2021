package com.example.mgp2021;

import static androidx.fragment.app.FragmentManager.findFragment;

import android.app.Activity;
import android.app.KeyguardManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

import androidx.fragment.app.FragmentManager;

// Created by TanSiewLan2021

public class MainMenu extends Activity implements OnClickListener, StateBase {  //Using StateBase class

    //public static MainMenu Instance = null;
    //Define buttons
    private Button btn_start;
    private Button btn_exit;
    private Button btn_highscore;
    public static MainMenu Instance = null;
    private boolean exit_check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Instance = this;
        new GameView(this);
        setContentView(R.layout.mainmenu);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); //Set Listener to this button --> Start Button

        btn_highscore = (Button)findViewById(R.id.btn_highscore);
        btn_highscore.setOnClickListener(this); //Set Listener to this button --> Highscore Button

        btn_exit = (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this); //Set Listener to this button --> Back Button
        StateManager.Instance.AddState(new MainMenu());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (v == btn_start)
        {
            //set stage
            MainGameSceneState.StageNo = 1;

            // intent --> to set to another class which another page or screen that we are launching.
            intent.setClass(this, GamePage.class);
            StateManager.Instance.ChangeState("MainGame"); // Default is like a loading page
            GameSystem.Instance.SetIsPaused(false);

            //init variables
            RenderTextEntity.score = 0;
            RenderTextEntity.lives = 3;
            DraggablePlayer.currLvl = 0;
            //MainGameSceneState.StartGame = true;
        }

        if (v == btn_highscore)
        {
            //StateManager.Instance.ChangeState("HighscorePage");
            intent.setClass(this, HighscorePage.class);
        }

        else if (v == btn_exit)
        {
            //intent.setClass(this, MainMenu.class);
            System.exit(0);
            exit_check = true;

  /*         if (ExitConfirmDialog.IsShown)
                return;

            ExitConfirmDialog ExitDialog = new ExitConfirmDialog();
            ExitDialog.show(this.getFragmentManager(), "ExitConfirm");*/
        }
        startActivity(intent);

    }

    @Override
    public void Render(Canvas _canvas) {
    }
	
    @Override
    public void OnEnter(SurfaceView _view) {
        AudioManager.Instance.MuteAudio(R.raw.bgm);
        AudioManager.Instance.PlayAudio(R.raw.air,1);
    }
	
    @Override
    public void OnExit() {
    }
	
    @Override
    public void Update(float _dt) {
    }
	
    @Override
    public String GetName() {
        return "Mainmenu";
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
