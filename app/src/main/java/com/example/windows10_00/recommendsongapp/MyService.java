package com.example.windows10_00.recommendsongapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;

public class MyService extends Service {
    Queue<String> data;
    int one = 0, two = 0 , third = 0, four = 0;

    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public void onCreate() {
            super.onCreate();
            data = new LinkedList<>();
            if(data != null)
                Log.d(TAG , "서비스가 실행됩니다.");




    }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            inputData(intent);
            return super.onStartCommand(intent, flags, startId);
        }

        void inputData(Intent intent) {

            try {


                String number = intent.getStringExtra("CODE");


                data.offer(number);

                Log.d(TAG , number+"추가 되었습니다.");

        } catch (Exception e){
            Log.d(TAG , "오류가 발생하였습니다.");

            data.offer("0");

        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        makeCode();

        Log.d(TAG , "서비스가 종료됩닏.");


    }

    void makeCode(){
        Intent intent = new Intent(getApplicationContext() , ChoiceActivity.class);
        StringBuilder result = new StringBuilder();
        int count = 1;
        while (!data.isEmpty()){

            String data = this.data.poll();




            if(data.equals("0")){
                result.delete(0 , result.length());
                result.append("error");
                break;
            }


            if(count == 3 || count == 9)
                result.append(data);
            else {
               numberCalue(data);
            }


            count++;

        }

        result.append(String.valueOf(numberCalue("0")));

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("Result" , String.valueOf(result));
        Log.d(TAG , String.valueOf(result) + "가 최종 코드");

        startActivity(intent);
    }


    int numberCalue(String data){

        Log.d(TAG , data+"값이 들어왔습니다.");
        Log.d(TAG, one + " : "+ two + " : "+ third + " : " + four);
        if(data == null){
            Log.d(TAG , " null 값이 들어왔습니다");
            return 0;
        }
        if(data.equals("1")) one++;
        else if(data.equals("2")) two++;
        else if(data.equals("3")) third++;
        else if(data.equals("4")) four++;

        if(one + two + third + four >= 7) {
            int[] arg = {one, two, third, four};
            int next = 0;
            for (int n : arg) {
                if (next < n) next = n;
            }
            return (next == one) ? 1 : (next == two) ? 2 : (next == third) ? 3 : 4 ;
        }
        return 0;
    }
}
