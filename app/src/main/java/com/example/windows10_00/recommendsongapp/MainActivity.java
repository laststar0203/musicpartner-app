package com.example.windows10_00.recommendsongapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout layout;
    Button startBtn;
    ImageButton songBtn;
    Animation fadein , fadeout;
    boolean dontTouch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();

        setAction();

    }
    void setAction(){
        fadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dontTouch = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layout.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(getApplicationContext() , ChoiceActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.noanim , R.anim.noanim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });




    }

    void setUp(){
        layout = findViewById(R.id.entireLayout);
        startBtn = findViewById(R.id.startBtn);
        songBtn = findViewById(R.id.songBtn);

        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        startBtn.setOnClickListener(this);
        songBtn.setOnClickListener(this);

    }


    @Override
    protected void onStop() {
        super.onStop();
        layout.setVisibility(View.VISIBLE);
        dontTouch = false;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startBtn:
                if(dontTouch) return;

                layout.startAnimation(fadein);
                break;
            case R.id.songBtn:
                    Intent intent = new Intent(getApplicationContext() , MusicCtrlActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                break;
        }
    }
}
