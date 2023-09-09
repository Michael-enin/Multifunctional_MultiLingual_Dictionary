package com.example.finalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.example.finalapp.ContractDescription.*;
public class DBHelper extends SQLiteOpenHelper {

    public static final String myDatabaseName = /*"Database_Dictionary.db";*/ "testdatabase.db";
    public static final int myDatabaseVersion =1;
    public static String Database_Location = "";
    private String Full_Database_Path = "";
    private final String mTbl_Ke_En = "Ke_En";
    private final String mTbl_Ke_Am = "Ke_Am";
    private final String mTbl_Am_Ke = "Am_Ke";
    private final String mTbl_Favorites = "Favorites"; //not used now
    private final String mTbl_Column_Key = "key";
    private final String mTbl_Column_Value = "value";

    public SQLiteDatabase mDb;
    private Context mContext;
    public DBHelper(Context context){
        super(context, myDatabaseName, null, 1);
        this.mContext=context;
        Database_Location = "/data/data/"+context.getPackageName()+"/databases/";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      this.mDb=db;

      final String SQL_CREATE_DESCRIPTION_TABLE="CREATE TABLE " +
           DescriptionTable.TableName + "(" +
              DescriptionTable._ID + "INTEGER PRIMARY KEY, " +
              DescriptionTable.Column_Description + "TEXT,  " +
              DescriptionTable.Column_Answer_option + "TEXT, " +
              DescriptionTable.Column_option2 + "TEXT, " +
              DescriptionTable.Column_option3 + "TEXT, " +
              DescriptionTable.Column_option4 + "TEXT, " +
              DescriptionTable.Column_Ans + "INTEGER" + ")";
      mDb.execSQL(SQL_CREATE_DESCRIPTION_TABLE);
        fillDescriptionTable();
    }
  public void fillDescriptionTable(){
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
        ContentValues cv = new ContentValues();
        cv.put(ContractDescription.DescriptionTable.Column_Description, description.getDescription());
        cv.put(DescriptionTable.Column_Answer_option, description.getOption1());
        cv.put(DescriptionTable.Column_option2, description.getOption2());
        cv.put(DescriptionTable.Column_option3, description.getOption3());
        cv.put(DescriptionTable.Column_option4, description.getOption4());
        cv.put(DescriptionTable.Column_Ans, description.getAnswerNo());
        mDb.insert(DescriptionTable.TableName, null, cv);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContractDescription.DescriptionTable.TableName);
        onCreate(db);
    }
    public List<Description> getAllDescription(){
        List<Description>  descriptionList = new ArrayList<>();
        mDb=getReadableDatabase();
        Cursor c=mDb.rawQuery("SELECT * FROM " + ContractDescription.DescriptionTable.TableName, null);
        if(c.moveToFirst()){
            do{
                Description description = new Description();
                description.setDescription(c.getString(c.getColumnIndex(ContractDescription.DescriptionTable.Column_Description)));
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
//    public void openDatabase(){
//        String dbPath= mContext.getDatabasePath(myDatabaseName).getPath();
//        if(mDb!=null && mDb.isOpen()){
//
//        }
//        mDb=SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//    }
//    public void closeDatabase(){
//        if(mDb!=null){
//            mDb.close();
//        }
//    }
//
//    public ArrayList<String> getWord(int dickType){
//        String tableName = getTableName(dickType);
//        String query = "select * from Ke_Am" + tableName;
//        Cursor result = mDb.rawQuery(query, null);
//        ArrayList<String> source = new ArrayList<>();
//        while(result.moveToNext()){
//            source.add(result.getString(result.getColumnIndex(mTbl_Column_Key)));
//        }
//        return source;
//    }
//    public void addFavorites(Words word){
//        try {
//            String query = "insert into Favorites( [" + mTbl_Column_Key + "], [" + mTbl_Column_Value + "]) values (?, ?);";
//            mDb.execSQL(query, new Object[]{word.key, word.value});
//        }
//        catch (SQLException e){ }
//
//    }
//    public void removeFavorites(Words word){
//        try{
//            String query = "DELETE FROM Favorites WHERE [" + mTbl_Column_Key + "] = ? AND [" + mTbl_Column_Value+ "] = ?;";
//        }
//        catch (SQLException e){}
//    }
//    public ArrayList<String> getAllWordsFromFavorites(String key){
//
//        String query = "select * from Favorites ORDER BY [date]";
//        Cursor result = mDb.rawQuery(query, new String[]{key});
//        ArrayList<String> source = new ArrayList<>();
//        while(result.moveToNext()){
//            source.add(result.getString(result.getColumnIndex(mTbl_Column_Key)));
//
//        }
//        return source;
//    }
//    public boolean isWordMarked(Words word){
//
//        String query = "SELECT * FROM Favorites WHERE [key]=? AND [value]=?";
//        Cursor result = mDb.rawQuery(query, new String[]{word.key, word.key});
//
//        return result.getCount() > 0;
//    }
//    public Words getWordFromFavorites(String key){
//        String query = "SELECT * FROM Favorites WHERE [key]=?";
//        Cursor result  = mDb.rawQuery(query, null);
//        Words word = new Words();
//        while(result.moveToNext()){
//            word.key= result.getString(result.getColumnIndex(mTbl_Column_Key));
//            word.value = result.getString(result.getColumnIndex(mTbl_Column_Value));
//
//        }
//        return word;
//    }
//    public String getTableName(int dickType){
//        String tableName="";
//        if(dickType==R.id.Ke_Am){
//            tableName = mTbl_Ke_Am;
//
//        }
//        else if(dickType==R.id.ke_En){
//            tableName = mTbl_Ke_En;
//        }
//        else if(dickType==R.id.am_ke){
//            tableName = mTbl_Am_Ke;
//        }
//        return tableName;
//
//    }
}

