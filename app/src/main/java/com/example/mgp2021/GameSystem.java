package com.example.mgp2021;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();

    // For pause
    private boolean isPaused = false;

    // For Scoring
    public static final String SHARED_PREF_ID = "GameSaveFile";
    private int currScore = 0;
    private int highScore = 0;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    // Better to place in a getter/setter.
    public DraggablePlayer playerEntityInstance = null;

    // Singleton Pattern : Blocks others from creating
    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {
        // We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new MainMenu());
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new HighscorePage());
        StateManager.Instance.AddState(new GameoverPage());

        // Get our shared preferences (Save file)
        sharedPref = MainMenu.Instance.getSharedPreferences(SHARED_PREF_ID,0);
    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }

    // For scoring
    public int GetScore(){
        return currScore;
    }

    public void AddScore(){
        AddScore(1);
    }

    public void AddScore (int _amt){
        currScore += _amt;
    }

    public void ResetScore(){
        currScore = 0;
    }

    public void SaveEditBegin()
    {
        // Safety check, only allow if not already editing
        if (editor != null)
            return;

        // Start the editing
        editor = sharedPref.edit();
    }

    public void SaveEditEnd()
    {
        // Check if has editor
        if (editor == null)
            return;

        editor.commit();
        editor = null; // Safety to ensure other functions will fail once commit done
    }

    public void SetIntInSave(String _key, int _value)
    {
        if (editor == null)
            return;

        editor.putInt(_key, _value);
    }

    public int GetIntFromSave(String _key)
    {
        return sharedPref.getInt(_key, 0);
    }



    public void SaveEditInt(String _key, int _value)
    {
        GameSystem.Instance.SaveEditBegin();
        GameSystem.Instance.SetIntInSave(_key, _value);
        GameSystem.Instance.SaveEditEnd();
    }

}
