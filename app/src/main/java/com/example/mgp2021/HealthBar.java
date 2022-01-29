package com.example.mgp2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class HealthBar implements EntityBase{
    private Bitmap health1 = null;
    private Bitmap health2 = null;
    private Bitmap health3 = null;

    private Bitmap ScaledbmpH1 = null;
    private Bitmap ScaledbmpH2 = null;
    private Bitmap ScaledbmpH3 = null;

    private float xPos = 0;
    private float xPos1 = 0;
    private float xPos2 = 0;
    private float yPos = 0;

    private boolean isDone = false;
    private boolean isInit = false;

    int ScreenWidth, ScreenHeight;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {

        health1 = ResourceManager.Instance.GetBitmap(R.drawable.player2);
        health2 = ResourceManager.Instance.GetBitmap(R.drawable.player2);
        health3 = ResourceManager.Instance.GetBitmap(R.drawable.player2);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpH1 = Bitmap.createScaledBitmap(health1, (int) (ScreenWidth)/6, (int)(ScreenWidth)/6, true);
        ScaledbmpH2 = Bitmap.createScaledBitmap(health2, (int) (ScreenWidth)/6, (int)(ScreenWidth)/6, true);
        ScaledbmpH3 = Bitmap.createScaledBitmap(health3, (int) (ScreenWidth)/6, (int)(ScreenWidth)/6, true);


        xPos = ScreenWidth * 0.01f;
        xPos1 = ScreenWidth * 0.15f;
        xPos2 = ScreenWidth * 0.29f;
        yPos = ScreenHeight * 0.01f;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {

    }

    @Override
    public void Render(Canvas _canvas) {
        if(RenderTextEntity.lives == 3)
        {
            _canvas.drawBitmap(ScaledbmpH1,xPos, yPos, null);
            _canvas.drawBitmap(ScaledbmpH2,xPos1, yPos, null);
            _canvas.drawBitmap(ScaledbmpH3,xPos2, yPos, null);
        }
        else if (RenderTextEntity.lives == 2)
        {
            _canvas.drawBitmap(ScaledbmpH1,xPos, yPos, null);
            _canvas.drawBitmap(ScaledbmpH2,xPos1, yPos, null);
        }
        else if (RenderTextEntity.lives == 1)
        {
            _canvas.drawBitmap(ScaledbmpH1,xPos, yPos, null);
        }

    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.HEALTHBAR_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_HEALTHBAR;}

    public static HealthBar Create()
    {
        HealthBar result = new HealthBar();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_HEALTHBAR);
        return result;
    }


}
