package com.example.windows10_00.recommendsongapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ChoiceActivity extends AppCompatActivity {

    Choice1FragMent choiceActivity;
    FrameLayout layout;
    Animation fadein;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        setUp();

        setAction();

        Text text = new Text();

        text.mix();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentLayout , new Choice1FragMent());
        fragmentTransaction.commit();




    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0 , 0);
    }
    void setAction(){
        fadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                layout.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(getApplicationContext() , ResultActivity.class);
                intent.putExtra("Result" , code);
                startActivity(intent);
                overridePendingTransition(R.anim.noanim , R.anim.noanim);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    void setUp(){
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        layout = findViewById(R.id.fragmentLayout);
    }

    @Override
    protected void onNewIntent(Intent intent) {


        receiveData(intent);

        super.onNewIntent(intent);
    }

    private void receiveData(Intent intentData) {
        if(intentData != null){
            code = intentData.getStringExtra("Result");

            //finish();

            layout.startAnimation(fadein);


        }

    }


}
