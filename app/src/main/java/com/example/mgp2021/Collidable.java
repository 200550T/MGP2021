package com.example.mgp2021;

// Created by TanSiewLan2021

public interface Collidable {
    String GetType();

    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(Collidable _other);
}

