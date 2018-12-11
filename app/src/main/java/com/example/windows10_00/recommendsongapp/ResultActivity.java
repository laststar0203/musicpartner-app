package com.example.windows10_00.recommendsongapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseHelper dHelper;
    SQLiteDatabase db;
    TextView nameText , addressText, lookCountText, listenCountText, loveCountText;
    Button listenBtn;
    ImageButton loveBtn;
    String name , address;
    int touchCount;
    int lookCount , listenCount, loveCount;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setup();

        String code = getIntent().getStringExtra("Result").toString();


        openDataBase(code);




    }

    void openDataBase(String code){


            dHelper = new DatabaseHelper(this);
            db = dHelper.getWritableDatabase();

        try {
           printMusic(dHelper.select(db,  code , DatabaseHelper.SearchMode.CODE));
        }catch (NullPointerException e){
            Toast.makeText(getApplicationContext() , "데이터 값을 찾을 수 없습니다." + code + db , Toast.LENGTH_LONG).show();
            finish();
        }catch (IllegalArgumentException e){
            Toast.makeText(getApplicationContext() , "데이터 값이 제대로 전송이 안됬습니다." + code + db , Toast.LENGTH_LONG).show();
            finish();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext() , "알수 없는 오류!" , Toast.LENGTH_LONG).show();
            finish();
        }


    }



    void printMusic(ArrayList data){
        this.name = data.get(0).toString();
        this.address = data.get(1).toString();
        this.lookCount =(int) data.get(2);
        this.listenCount =(int) data.get(3);
        this.loveCount =(int) data.get(4);



        nameText.setText(name);
        addressText.setText("");
        lookCountText.setText(String.valueOf("나온 횟수 : "+lookCount));
        listenCountText.setText(String.valueOf("들은 횟수 : "+listenCount));
        loveCountText.setText(String.valueOf("좋아요 횟수 : "+loveCount));

    }

    void setup(){

        nameText = findViewById(R.id.name);
        addressText = findViewById(R.id.address);
        lookCountText = findViewById(R.id.lookCount);
        listenCountText = findViewById(R.id.listenCount);
        loveCountText = findViewById(R.id.love);
        listenBtn = findViewById(R.id.listenBtn);
        loveBtn = findViewById(R.id.loveBtn);

        listenBtn.setOnClickListener(this);
        loveBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.listenBtn:
                listenCount++;
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(address));
                startActivity(intent);

                break;
            case R.id.loveBtn:
                if(touchCount%2 == 0) {
                    loveBtn.setImageResource(R.drawable.heart);
                    Toast.makeText(getApplicationContext() , "하트 ++" , Toast.LENGTH_LONG).show();
                }
                else {
                    loveBtn.setImageResource(R.drawable.antiheart);
                    Toast.makeText(getApplicationContext() , "하트 --" , Toast.LENGTH_LONG).show();
                }
                touchCount++;

                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0 , 0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        dHelper.onUpdate(db , name , new int[]{lookCount , --listenCount , (touchCount%2 != 0) ? loveCount : --loveCount});

    }
}
