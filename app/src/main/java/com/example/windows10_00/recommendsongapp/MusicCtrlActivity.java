package com.example.windows10_00.recommendsongapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MusicCtrlActivity extends AppCompatActivity implements View.OnClickListener {

    Button addBtn , deleteBtn;
    FrameLayout frame;
    AddMusicFragMent addfrag;
    DeleteMusicFragMent deletefrag;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_ctrl);


        setUp();
    }

    private void setUp() {

        addBtn = findViewById(R.id.addMusic);
        deleteBtn = findViewById(R.id.deleteMusic);
        frame = findViewById(R.id.main_fragment);

        addBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);



        addfrag = new AddMusicFragMent();
        deletefrag = new DeleteMusicFragMent();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_fragment , addfrag);
        fragmentTransaction.commit();


    }

    @Override
    public void onClick(View v) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()){
            case R.id.addMusic:

                fragmentTransaction.replace(R.id.main_fragment , addfrag);
                fragmentTransaction.commit();



                break;

            case R.id.deleteMusic:
                fragmentTransaction.replace(R.id.main_fragment , deletefrag);
                fragmentTransaction.commit();

                break;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0 , 0);
    }
}
