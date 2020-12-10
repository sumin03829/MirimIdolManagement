package kr.hs.emirim.s2019s38.mirimidolmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(@Nullable Context context) {
        super(context,"proupDB",null,1);
    }
    //table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEBER)");
    }
    //db가 upgrade 되었을 떄 기존의 table 을 삭제하고 새로 생성
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists groupTBL");
        onCreate(db);
    }
}
