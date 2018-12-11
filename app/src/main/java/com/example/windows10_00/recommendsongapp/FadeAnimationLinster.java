package com.example.windows10_00.recommendsongapp;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;

public class FadeAnimationLinster extends AppCompatActivity implements Animation.AnimationListener {

   Application startactivitys;
    Object endActivity;



    public  FadeAnimationLinster(Application startActivity , Object endActivity){

        this.startactivitys = startActivity;
        this.endActivity = endActivity;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(startactivitys , endActivity.getClass());
        startActivity(intent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
