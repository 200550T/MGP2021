package com.example.mgp2021;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import com.facebook.share.Share;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;

public class FacebookPost extends Activity implements View.OnClickListener, StateBase{
    //public static MainMenu Instance = null;
    //Define buttons
    private Button btn_exitshare;
    private Button btn_sharemyscore;
    private LoginButton btn_fbLogin;
    private LoginManager loginManager = null;
    private CallbackManager callbackManager = null;
    private static final String EMAIL = "email";
    private ShareDialog share_Dialog;
    private int PICK_IMAGE_REQUEST = 1;
    private int highscore = 0;
    ProfilePictureView profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        highscore = GameSystem.Instance.GetIntFromSave("Highscore");

        FacebookSdk.setApplicationId("1604223473292246");
        FacebookSdk.sdkInitialize(getApplicationContext());
        if(BuildConfig.DEBUG){
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        setContentView(R.layout.facebookpost);


        TextView scoreText;
        scoreText = (TextView) findViewById(R.id.scoreText);
        scoreText.setText(String.format("Personal Highscore:%d", GameSystem.Instance.GetIntFromSave("Highscore")));

        //buttons
        btn_exitshare = (Button)findViewById(R.id.btn_exitshare);
        btn_exitshare.setOnClickListener(this);

        btn_fbLogin = (LoginButton) findViewById(R.id.fb_login_button);
        btn_fbLogin.setReadPermissions(Arrays.asList(EMAIL));
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));

        btn_sharemyscore= (Button)findViewById(R.id.btn_sharemyscore);
        btn_sharemyscore.setOnClickListener(this);


        callbackManager = CallbackManager.Factory.create();

        loginManager = LoginManager.getInstance();
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                profile_pic.setProfileId(Profile.getCurrentProfile().getId());
            }

            @Override
            public void onCancel() {
                System.out.println("Login attempt canceled.");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                System.out.println("Login attempt failed.");
            }
        });
    }

    public void shareScore(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        if(ShareDialog.canShow(SharePhotoContent.class)){
            System.out.println("photonShown");
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .setCaption("Thanks for playing CyberSpace! Your high score is " + highscore)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
            share_Dialog.show(content);
        }
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        Intent intent = new Intent();

        if (v == btn_sharemyscore)
        {
            shareScore();
        }

        if (v == btn_exitshare)
        {
            intent.setClass(this, HighscorePage.class);
            startActivity(intent);
        }
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
        return "FacebookPost";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
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
