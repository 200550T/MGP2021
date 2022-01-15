package com.example.mgp2021;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

public class GameoverPage extends Activity implements OnClickListener, StateBase {
    //public static MainMenu Instance = null;
    //Define buttons
    private Button btn_menu, btn_playagain, btn_sharescore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instance = this;

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gameoverpage);

        btn_menu = (Button)findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);

        btn_playagain = (Button)findViewById(R.id.btn_playagain);
        btn_playagain.setOnClickListener(this);

        btn_sharescore = (Button)findViewById(R.id.btn_sharescore);
        btn_sharescore.setOnClickListener(this);

        StateManager.Instance.AddState(new GameoverPage());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if (v == btn_menu)
        {
            intent.setClass(this, MainMenu.class);
        }

        if(v == btn_playagain)
        {
            intent.setClass(this, GamePage.class);
            StateManager.Instance.ChangeState("MainGame"); // Default is like a loading page
            GameSystem.Instance.SetIsPaused(false);
        }

        if(v== btn_sharescore)
        {
            //add share score screen here
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
    }

    @Override
    public String GetName() {
        return "GameoverPage";
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
