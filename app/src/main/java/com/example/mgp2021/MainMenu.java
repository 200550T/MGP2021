package com.example.mgp2021;

import static androidx.fragment.app.FragmentManager.findFragment;

import android.app.Activity;
import android.app.KeyguardManager;
import android.graphics.Canvas;
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

    public static MainMenu Instance = null;
    //Define buttons
    private Button btn_start;
    private Button btn_exit;
    private boolean exit_check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Instance = this;

        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mainmenu);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); //Set Listener to this button --> Start Button

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
            // intent --> to set to another class which another page or screen that we are launching.
            intent.setClass(this, GamePage.class);
 				 StateManager.Instance.ChangeState("MainGame"); // Default is like a loading page

        }

        else if (v == btn_exit)
        {
            // intent.setClass(this, MainMenu.class);
            exit_check = true;
        }
        startActivity(intent);

    }

    @Override
    public void Render(Canvas _canvas) {
    }
	
    @Override
    public void OnEnter(SurfaceView _view) {
    }
	
    @Override
    public void OnExit() {
    }
	
    @Override
    public void Update(float _dt) {
        if (exit_check)
        {
            if (ExitConfirmDialog.IsShown)
                return;

            ExitConfirmDialog ExitDialog = new ExitConfirmDialog();
            // ExitDialog.show(this.getFragmentManager(), "ExitConfirm");
        }
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
