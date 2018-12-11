package com.example.windows10_00.recommendsongapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DeleteMusicFragMent extends Fragment implements View.OnClickListener {

    EditText editText;
    TextView title , other;
    Button serachBtn, deleteBtn;
    View view;
    boolean isSerch;
    DatabaseHelper dHelper;
    SQLiteDatabase db;
    String name;
    boolean IsDelete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.deletemusic , container , false);

        setUp();

        return view;
    }

    private void setUp() {

    editText = view.findViewById(R.id.serachEdit);
    title = view.findViewById(R.id.titleContentText);
    other = view.findViewById(R.id.otherContentText);
    serachBtn = view.findViewById(R.id.serachBtn);
    deleteBtn = view.findViewById(R.id.deleteBtn);

        IsDelete = true;

    serachBtn.setOnClickListener(this);
    deleteBtn.setOnClickListener(this);

    dHelper = new DatabaseHelper(getActivity().getApplicationContext());
    db = dHelper.getWritableDatabase();


    }

    @Override
    public void onClick(View v) {

        name = editText.getText().toString();

        switch (v.getId()){
            case R.id.serachBtn:
                if(dHelper.select(db , name , name)){
                        setContentText(dHelper.select(db , name , DatabaseHelper.SearchMode.NAME));
                        IsDelete = false;
                }else
                    Toast.makeText(getActivity().getApplicationContext() , "해당 이름을 가진 뮤직이 존재하지 않습니다", Toast.LENGTH_LONG).show();
                break;

            case R.id.deleteBtn:
                if(IsDelete){
                    Toast.makeText(getActivity().getApplicationContext(), "삭제할 뮤직이 없습니다.", Toast.LENGTH_LONG).show();
                }else {

                    dHelper.delete(db, name);
                    Toast.makeText(getActivity().getApplicationContext(), "뮤직이 삭제되었습니다.", Toast.LENGTH_LONG).show();
                    IsDelete = true;

                }
                break;



        }
    }

    void setContentText(ArrayList list){
        title.setText(name);
        other.setText("주소 : "+list.get(0).toString()+"\n"
        +"본 횟수 : "+list.get(1).toString()+"\n"
        +"들은 횟수 : "+list.get(2).toString()+"\n"
        +"좋아요 횟수 : "+list.get(3).toString());
    }


}
