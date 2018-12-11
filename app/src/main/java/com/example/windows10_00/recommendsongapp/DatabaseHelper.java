package com.example.windows10_00.recommendsongapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class DatabaseHelper extends SQLiteOpenHelper {



        public enum SearchMode{CODE , NAME , ADDRESS , LOOK_COUNT , LISTEN_COUNT , LOVE_COUNT};
        public final static int DATABASE_VERSION = 1;
        public final static String DATABASE_NAME = "Music_List";
        public final static String TAG2 = "DataBaseHelper";
        ArrayList<String> musicName , address ;
        ArrayList<Integer> lookCount, listenCount , loveCount;

        public DatabaseHelper(Context context) {
            super(context, "MusicPartnerDB.db", null, DATABASE_VERSION);

            musicName = new ArrayList<>();
            address = new ArrayList<>();
            lookCount = new ArrayList<>();
            listenCount = new ArrayList<>();
            loveCount = new ArrayList<>();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String CREATE_SQL = "create table " + DATABASE_NAME + "(" +
                    "_id integer PRIMARY KEY autoincrement, " +
                    " code text, " +
                    " name text, " +
                    " address text," +
                    " lookCount integer," +
                    " listenCount integer," +
                    " loveCount integer)";

            db.execSQL(CREATE_SQL);

            Log.d(TAG2, "Table creat!");


            insert(db, "1111" , "default" , "default");
            defaultInput(db);
        }

    private void defaultInput(SQLiteDatabase db) {
        insert(db, "111" , "로시-구름" , "https://www.youtube.com/watch?v=oSuElDuA1qo");
        insert(db, "121" , "케이윌 - 말해 뭐해" , "https://www.youtube.com/watch?v=SdHQkkRc-hc");
        insert(db, "131" , "추노 - 낙인" , "https://www.youtube.com/watch?v=xgmoUZo5U0Y");
        insert(db, "211" , "미스터션샤인ost - 바이올린" , "https://www.youtube.com/watch?v=_fHTseXUrSY");
        insert(db, "221" , "About That Oldie" , "https://www.youtube.com/watch?v=FqQJKeti-TE");
        insert(db, "231" , "마리오 BGM" , "https://www.youtube.com/watch?v=wGX4obVl64w&list=PLrOqFyl41VVBoazw9QZ-XmFoMfEs8QzJQ");
        insert(db, "311" , "Havanla - 카밀" , "https://www.youtube.com/watch?v=f3f6vbRCEp4");
        insert(db, "321" , "Hand clap - Fitz" , "https://www.youtube.com/watch?v=Y2V6yjjPbX0");
        insert(db, "331" , "Sugar - 마룬도" , "https://www.youtube.com/watch?v=09R8_2nJtjg");
        insert(db, "411" , "Solo - 제니" , "https://www.youtube.com/watch?v=b73BI9eUkjM");
        insert(db, "421" , "사랑을 했따 - 아이콘" , "https://www.youtube.com/watch?v=vecSVX1QYbQ");
        insert(db, "431" , "임창정 - 여보세요" , "https://www.youtube.com/watch?v=FLPLgJqeZJw");
        insert(db, "112" , "벛꽃연가 - 첸" , "https://www.youtube.com/watch?v=wJi2_U_rGwk");
        insert(db, "122" , "걱정말아요 그대 - 이적" , "https://www.youtube.com/watch?v=Dic27EnDDls");
        insert(db, "132" , "눈의꽃 - 박효신" , "https://www.youtube.com/watch?v=sr3JaQ3h7YA");
        insert(db, "212" , "언더테일 BGM - Dream" , "https://www.youtube.com/watch?v=c9eqra5gvlI");
        insert(db, "222" , "Tobu - mesemrize" , "https://www.youtube.com/watch?v=O3M8zZFF0WM");
        insert(db, "232" , "Tobu - Colors" , "https://www.youtube.com/watch?v=eyLml-zzXzw");
        insert(db, "312" , "마룬도 - Girls like you" , "https://www.youtube.com/watch?v=aJOTlE1K90k");
        insert(db, "322" , "Perfect - Eol sheeran" , "https://www.youtube.com/watch?v=2Vv-BfVoq4g");
        insert(db, "332" , "아카펠라 - Austin Jones" , "https://www.youtube.com/watch?v=JmHWPCzIfW0&list=PLFEtCKQ55pW6-tse8wbWAIEvG-XVRoLI2");
        insert(db, "412" , "모든날, 모든순간 - 폴 컴" , "https://www.youtube.com/watch?v=9II_GQJ7mKo");
        insert(db, "242" , "Way baclchome - 숀" , "https://www.youtube.com/watch?v=YPqDMRJ0lBk");
        insert(db, "432" , "유레카 - 지코" , "https://www.youtube.com/watch?v=RDIxJjn5A50");
        insert(db, "113" , "웬디 - Goodbye" , "https://www.youtube.com/watch?v=A-emJHnVtZ8");
        insert(db, "123" , "도깨비 OST - Beautiful" , "https://www.youtube.com/watch?v=hAjiKVEWZSk");
        insert(db, "313" , "소녀 - 오혁" , "https://www.youtube.com/watch?v=SWRta06XPvs");
        insert(db, "213" , "C418 - Sweden 마크BGM" , "https://www.youtube.com/watch?v=3pbvmR8n27w");
        insert(db, "223" , "Jim yoSef - Link" , "https://www.youtube.com/watch?v=9iHM6X6uUH8");
        insert(db, "323" , "Tony2 - Road So Far" , "https://www.youtube.com/watch?v=MVMIwIJtMdU");
        insert(db, "313" , "Happier - Ed sneeran" , "https://www.youtube.com/watch?v=iWZmdoY1aTE");
        insert(db, "323" , "위대한 쇼맨 - TightroPe" , "https://www.youtube.com/watch?v=S-CVJuNQo6w");
        insert(db, "333" , "Santa Monica Dream - Angus & Julia Stone" , "https://www.youtube.com/watch?v=kVdWqHtTPkE");
        insert(db, "413" , "너를 만나 - 폴 킴" , "https://www.youtube.com/watch?v=YBzJ0jmHv-4");
        insert(db, "423" , "어반자카파 - 그때의 나 그때의 우리" , "https://www.youtube.com/watch?v=O92yHB0MDZ8");
        insert(db, "343" , "스탠딩 에그 - 시간이달라서" , "https://www.youtube.com/watch?v=5i0j6aosVcg");
        insert(db, "114" , "뷰티인사이드 OST - 빈젠트" , "https://www.youtube.com/watch?v=mDJdYwP9rhc");
        insert(db, "124" , " Frozen(겨울왕국) - Love Is An Open Door " , "https://www.youtube.com/watch?v=caCaxCzRkgM");
        insert(db, "134" , "For the First Time in Forever - Frozen" , "https://www.youtube.com/watch?v=EgMN0Cfh-aQ");
        insert(db, "214" , "UNDERTALE - Bonetrousle Acapella" , "https://www.youtube.com/watch?v=UgH4Ai43UTk");
        insert(db, "224" , "Dancing Line - The Despacito" , "https://www.youtube.com/watch?v=zZyRKZRT9nI");
        insert(db, "234" , "언더테일 메타톤EX브금" , "https://www.youtube.com/watch?v=ZLkHEpKdlWU");
        insert(db, "314" , "Jason Derulo x David Guetta - Goodbye" , "https://www.youtube.com/watch?v=kUjKxtJd21E&list=PLuUrokoVSxlcChk5v_nwasQztA2e1CRUC&index=3");
        insert(db, "324" , "Steve Aoki - Waste It On Me" , "https://www.youtube.com/watch?v=bIv16itYi_0&list=PLuUrokoVSxlcChk5v_nwasQztA2e1CRUC&index=4");
        insert(db, "334" , "Justin Bieber - Baby ft. Ludacris" , "https://www.youtube.com/watch?v=kffacxfA7G4");
        insert(db, "414" , "Simply K-Pop _ I.O.I(아이오아이)" , "https://www.youtube.com/watch?v=0vv7iqGqoSw");
        insert(db, "424" , "소유 & 정기고 (SoYou & JunggiGo) - 썸" , "https://www.youtube.com/watch?v=HprBMqVNAAs");
        insert(db, "434" , "김흥국 – 호랑나비" , "https://www.youtube.com/watch?v=avHH4syRR-A");











    }

    public void insert(SQLiteDatabase db, String code, String name, String address) {



            ContentValues recondValue = new ContentValues();

            recondValue.put("code", code);
            recondValue.put("name", name);
            recondValue.put("address", address);
            recondValue.put("lookCount", 0);
            recondValue.put("listenCount", 0);
            recondValue.put("loveCount", 0);


            db.insert(DATABASE_NAME, null, recondValue);

            Log.d(TAG2, "Table Insert! + recode : "+ recondValue  + "db :"+db );


        }

        public boolean select(SQLiteDatabase db, String name , String address){

            String[] colume = {"name" , "address"};
            String wherStr = "name = ? or address = ?";
            String[] args = {name , address};

            Cursor c = db.query(DATABASE_NAME , colume , wherStr , args ,null ,null , null);

            String value = null;
           
                if(c.moveToNext()) value = c.getColumnName(c.getColumnIndex("name"));

                if(value == null) {
                    return false;
                }

                return true;

           // db.execSQL("select name, address from name, adress where name = ? or address = ?");

        }



    public ArrayList select(SQLiteDatabase db, String value , SearchMode mode) {

        ArrayList list = new ArrayList<>();
        String[] colume;
        String whereStr;
        String[] whereparams;
        Cursor c;
        int recordCount;
        Random random;
        switch (mode){
                case CODE:

                    musicName.clear();
                    address .clear();

                    if (db == null || (value == null || value.equals(""))) {
                        throw new NullPointerException();
                    }
                /*
        String SELECT_SQL = "select name, address "
                +" from "+DatabaseHelper.DATABASE_NAME+" where code = ?";
        String[] args = {"0"};
        */
                    //Cursor c1 = db.rawQuery(SELECT_SQL , args);
                    colume = new String[]{"name" , "code", "address" , "lookCount" , "listenCount" , "loveCount"};
                    whereStr = "code = ?";
                    whereparams = new String[]{value};


                    c = db.query(DATABASE_NAME, colume, whereStr, whereparams,
                            null, null, null);

                    recordCount = c.getCount();


                    Log.d(TAG2 , ""+recordCount);

                    while(c.moveToNext()) {

                        musicName.add(c.getString(c.getColumnIndex("name")));
                        address.add(c.getString(c.getColumnIndex("address")));
                        lookCount.add(c.getInt(c.getColumnIndex("lookCount")));
                        listenCount.add(c.getInt(c.getColumnIndex("listenCount")));
                        loveCount.add(c.getInt(c.getColumnIndex("loveCount")));


                    }
                    random = new Random();

                    int randomCount = random.nextInt(musicName.size());

                    list.add(musicName.get(randomCount));
                    list.add(address.get(randomCount));
                    list.add(lookCount.get(randomCount));
                    list.add(listenCount.get(randomCount));
                    list.add(loveCount.get(randomCount));

                    break;

                case NAME:
                    musicName.clear();
                    address .clear();

                    if (db == null || (value == null || value.equals(""))) {
                        throw new NullPointerException();
                    }

                    colume = new String[]{"address" , "lookCount" , "listenCount" , "loveCount"};
                    whereStr = "name = ?";
                    whereparams = new String[]{value};

                    c = db.query(DATABASE_NAME, colume, whereStr, whereparams,
                            null, null, null);

                    while(c.moveToNext()) {

                        list.add(c.getString(c.getColumnIndex("address")));
                        list.add(c.getInt(c.getColumnIndex("lookCount")));
                        list.add(c.getInt(c.getColumnIndex("listenCount")));
                        list.add(c.getInt(c.getColumnIndex("loveCount")));

                    }
                    break;

            }







            return list;
        }

        public void delete(SQLiteDatabase db, String name){

            String[] whereArgs = {name};

            db.delete(DATABASE_NAME , "name = ?" , whereArgs);

        }


        public void onUpdate(SQLiteDatabase db, String name, int[] data){

            String[] whereArgs = {name};

            ContentValues values = new ContentValues();

            values.put("lookCount" , ++data[0]);
            values.put("listenCount" , ++data[1]);
            values.put("loveCount" , ++data[2]);

            db.update(DATABASE_NAME , values , "name = ?" , whereArgs);

            Log.d(TAG2, "Table updata!" + values );
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
            Log.d(TAG2, "Table upgraed!" );

            onCreate(db);
        }
    }
