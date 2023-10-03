package com.example.finalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.finalapp.ContractDescription.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DescriptionHelper extends SQLiteOpenHelper {
    private static final String databaseName="description_database.db";
    private static final int database_version = 1;
    private SQLiteDatabase mdb;

    public DescriptionHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    this.mdb = db;
              final String SQL_CREATE_DESCRIPTION_TABLE=
                      " create table " + DescriptionTable.TableName + " ( " +
              DescriptionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
              DescriptionTable.Column_Description + " text,  " +
              DescriptionTable.Column_Answer_option + " text, " +
              DescriptionTable.Column_option2 + " text, " +
              DescriptionTable.Column_option3 + " text, " +
              DescriptionTable.Column_option4 + " text, " +
              DescriptionTable.Column_Ans + " integer )";
              mdb.execSQL(SQL_CREATE_DESCRIPTION_TABLE);
              fillDescriptionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists " + DescriptionTable.TableName);
    onCreate(db);
    }
    private void fillDescriptionTable(){
        Description desc1= new Description("A is Answer","A", "B", "C", "D", 1);
        addDescription(desc1);
        Description desc2= new Description("B is Answer","A", "B", "C", "D", 2);
        addDescription(desc2);
        Description desc3= new Description("A is Answer Again","A", "B", "C", "D", 1);
        addDescription(desc3);
        Description desc4= new Description("C is Answer","A", "B", "C", "D", 3);
        addDescription(desc4);
        Description desc5= new Description("D is Answer","A", "B", "C", "D", 4);
        addDescription(desc5);
    }
    private void addDescription(Description description){
      //  SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DescriptionTable.Column_Description, description.getDescription());
        cv.put(DescriptionTable.Column_Answer_option, description.getOption1());
        cv.put(DescriptionTable.Column_option2, description.getOption2());
        cv.put(DescriptionTable.Column_option3, description.getOption3());
        cv.put(DescriptionTable.Column_option4, description.getOption4());
        cv.put(DescriptionTable.Column_Ans, description.getAnswerNo());
        mdb.insert(DescriptionTable.TableName, null, cv);

    }
    public List<Description> getAllDescription(){
        List<Description>  descriptionList = new ArrayList<>();
        mdb=this.getReadableDatabase();
        Cursor c=mdb.rawQuery("select * from " + DescriptionTable.TableName, null);
        if(c.moveToFirst()){
            do{
               Description description = new Description();
               description.setDescription(c.getString(c.getColumnIndex(DescriptionTable.Column_Description)));
               description.setOption1(c.getString(c.getColumnIndex(DescriptionTable.Column_Answer_option)));
               description.setOption2(c.getString(c.getColumnIndex(DescriptionTable.Column_option2)));
               description.setOption3(c.getString(c.getColumnIndex(DescriptionTable.Column_option3)));
               description.setOption4(c.getString(c.getColumnIndex(DescriptionTable.Column_option4)));
               description.setAnswerNo(c.getInt(c.getColumnIndex(DescriptionTable.Column_Ans)));
               descriptionList.add(description);
            }
            while (c.moveToNext());
        }
        c.close();
    return descriptionList;
    }
}
