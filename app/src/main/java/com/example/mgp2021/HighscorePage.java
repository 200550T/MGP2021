package com.example.mgp2021;

import android.app.Activity;
import android.app.KeyguardManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HighscorePage extends Activity implements OnClickListener, StateBase {
    //public static MainMenu Instance = null;
    //Define buttons
    private Button btn_back;
    public static HighscorePage Instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Instance = this;

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.highscorepage);

        TextView scoreText;
        scoreText = (TextView)findViewById(R.id.scoreText);
        scoreText.setText(String.format("Highscore:%d", GameSystem.Instance.GetIntFromSave("Highscore")));

        //String.format("Highscore:%d", GameSystem.Instance.GetIntFromSave("Highscore"))

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this); //Set Listener to this button --> Back Button

        //StateManager.Instance.AddState(new HighscorePage();
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if (v == btn_back)
        {
            intent.setClass(this, MainMenu.class);
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
        return "HighscorePage";
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
