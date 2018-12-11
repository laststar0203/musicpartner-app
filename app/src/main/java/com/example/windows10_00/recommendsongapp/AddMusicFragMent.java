package com.example.windows10_00.recommendsongapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;

public class AddMusicFragMent extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    View view;

    SeekBar speedSeek, likeSeek , timeSeek;

    Spinner spinner;

    TextView speedText, likeText , timeText;

    Button addBtn, cancleBtn;

    EditText nameEdit, addressEdit;

    String[] item = {"OST" , "게임브금" , "브금" , "뮤직" , "팝송"};

    int speedValue , likeValue , timeValue;

    String spinerValue = "";

    StringBuilder sb ;

    boolean isAdd;

    DatabaseHelper dHelper ;
    SQLiteDatabase db ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.addmusic , container , false);

        setUp();

        return view;
    }

    private void setUp() {


        speedSeek = view.findViewById(R.id.speedSeek);
        likeSeek = view.findViewById(R.id.likeSeek);
        timeSeek = view.findViewById(R.id.timeSeek);
        spinner = view.findViewById(R.id.musicSpinner);
        speedText = view.findViewById(R.id.speedText);
        likeText = view.findViewById(R.id.likeText);
        timeText = view.findViewById(R.id.timeText);
        addBtn = view.findViewById(R.id.addBtn);
        cancleBtn = view.findViewById(R.id.cancleBtn);
        nameEdit = view.findViewById(R.id.nameEdit);
        addressEdit = view.findViewById(R.id.addressEdit);

        addBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);
        speedSeek.setOnSeekBarChangeListener(this);
        timeSeek.setOnSeekBarChangeListener(this);
        likeSeek.setOnSeekBarChangeListener(this);

        sb = new StringBuilder(spinerValue);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item , item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinnerAction();

        dHelper = new DatabaseHelper(getActivity().getApplicationContext());
        db = dHelper.getWritableDatabase();
    }

    void spinnerAction(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sb.delete(0 , sb.length());
                sb.append(item[position]);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {



        String name = null;
        String address;

        switch (v.getId()){

            case R.id.addBtn:
                name = nameEdit.getText().toString();
                address = addressEdit.getText().toString();

                if(name.equals("") || address.equals(""))
                {
                    Toast.makeText(getActivity().getApplicationContext() , "정보가 입력되지 않았습니다" , Toast.LENGTH_LONG).show();
                    return;
                }

                if(dHelper.select(db , name, address))
                    Toast.makeText(getActivity().getApplicationContext(), "이름 이나 주소 둘 중 하나가 이미 존재합니다.", Toast.LENGTH_SHORT).show();
                else {
                    dHelper.insert(db, getCodeValue(), name, address);
                    Toast.makeText(getActivity().getApplicationContext() , "노래가 추가되었습니다 !" , Toast.LENGTH_LONG).show();
                    isAdd = true;
                }
                break;

            case R.id.cancleBtn:
                name = nameEdit.getText().toString();
                address = addressEdit.getText().toString();

                    if(!isAdd) {
                        Toast.makeText(getActivity().getApplicationContext() , "취소할 뮤직이 없습니다," , Toast.LENGTH_LONG).show();
                        return;
                    }else {
                        dHelper.delete(db, name);
                        Toast.makeText(getActivity().getApplicationContext(), "방금 추가했던 뮤직이 취소되었습니다.", Toast.LENGTH_LONG).show();
                        isAdd = false;
                    }

                break;
        }
    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){

            case R.id.speedSeek:

                switch (progress/3){
                    case 0:
                        speedText.setText("느림");
                        speedValue = 1;
                        break;
                    case 1:
                        speedText.setText("보통");
                        speedValue = 2;
                        break;
                    case 2:
                    case 3:
                        speedText.setText("빠름");
                        speedValue = 3;
                        break;
                }
                speedValue = (int)progress;

                break;

            case R.id.likeSeek:
                switch (progress/3){
                    case 0:
                        likeText.setText("어두움");
                        likeValue = 1;
                        break;
                    case 1:
                        likeText.setText("보통");
                        likeValue = 2;
                        break;
                    case 2:
                    case 3:
                        likeText.setText("밝음");
                        likeValue = 3;
                        break;
                }

                break;
            case R.id.timeSeek:
                switch (progress/3){
                    case 0:
                        timeText.setText("오래됨");
                        timeValue = 1;
                        break;
                    case 1:
                        timeText.setText("중간");
                        timeValue = 2;
                        break;
                    case 2:
                    case 3:
                        timeText.setText("최신");
                        timeValue = 3;
                        break;
                }
                break;
        }
    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    String getCodeValue(){

        Queue<String> data = new LinkedList<>();
        switch (spinerValue){
            case "OST":
                data.offer("1");
                break;
            case "게임브금":
                data.offer("2");
                break;
            case "브금":
                data.offer("2");
                break;
            case "뮤직":
                data.offer("4");
                break;
            case "팝송":
                data.offer("3");
                break;

        }
        switch (timeValue){
            case 1:
                data.offer("3");
                break;
            case 2:
                data.offer("2");
                break;
            case 3:
                data.offer("1");
                break;
        }
        switch (speedValue + 3 + likeValue * 3){

            case 4:
                data.offer("3");
                break;
            case 7:
                data.offer("2");
                break;
            case 10:
                data.offer("4");
                break;

            case 5:
                data.offer("2");
                break;
            case 8:
                data.offer("4");
                break;
            case 11:
                data.offer("4");
                break;

            case 6:
                data.offer("2");
                break;
            case 9:
                data.offer("1");
                break;
            case 12:
                data.offer("1");
                break;

        }
        String dataValue = "";
        StringBuilder sb = new StringBuilder(dataValue);
        while(!data.isEmpty()){
                sb.append(data.poll());
        }
        return dataValue;
    }




}
