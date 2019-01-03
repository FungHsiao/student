package com.example.yt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteAdapter {
    //连接数据库
    private Context context;
    private String dBName;
    public MySQLiteAdapter(Context context, String dBName) {
        this.context = context;
        this.dBName = dBName;
    }

    private SQLiteDatabase db;
    private void openDatabase(){
        MyHelper myHelper=new MyHelper(context,dBName,null,1);
        db=myHelper.getWritableDatabase();
    }
    private void closeDatabase(){
        if(db.isOpen()){
            db.close();
        }
        db=null;
    }

    public Long insert(Infor infor){
        long rowid=-1;
        openDatabase();
        Cursor cursor=db.query("information",null,"number=? and name=? and course=?",new String[]{infor.getNumber(),infor.getName(),infor.getCourse()},null,null,null);
        if(cursor.getCount()>0){
            rowid=0;
        }else {
            ContentValues values = new ContentValues();
            values.put("number",infor.getNumber());
            values.put("name", infor.getName());
            values.put("course", infor.getCourse());
            values.put("score", infor.getScore());
            rowid = db.insert("information", null, values);
        }
        closeDatabase();
        return rowid;
    }
    //查询
    /*
    public List<Infor> queryAll(){
        openDatabase();
        List<Infor> list=new ArrayList<Infor>();

        Cursor cursor=db.query("information",null,null,null,null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Infor infor=new Infor();
                infor.setName(cursor.getString(cursor.getColumnIndex("name")));
                infor.setSubject(cursor.getString(cursor.getColumnIndex("subject")));
                infor.setScore(cursor.getFloat(cursor.getColumnIndex("score")));
                list.add(infor);
            }while (cursor.moveToNext());
        }
        closeDatabase();
        return list;
    }
    public List<Infor> queryByname(String name){
        openDatabase();
        List<Infor> list=new ArrayList<Infor>();
        Cursor cursor=db.query("information",null,"name=?",new String[]{name},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Infor infor=new Infor();
                infor.setName(cursor.getString(cursor.getColumnIndex("name")));
                infor.setSubject(cursor.getString(cursor.getColumnIndex("subject")));
                infor.setScore(cursor.getFloat(cursor.getColumnIndex("score")));
                list.add(infor);
            }while (cursor.moveToNext());
        }
        closeDatabase();
        return list;
    }
    public List<Infor> queryBySubject(String subject){
        openDatabase();
        List<Infor> list=new ArrayList<Infor>();
        Cursor cursor=db.query("information",null,"subject=?",new String[]{subject},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Infor infor=new Infor();
                infor.setName(cursor.getString(cursor.getColumnIndex("name")));
                infor.setSubject(cursor.getString(cursor.getColumnIndex("subject")));
                infor.setScore(cursor.getFloat(cursor.getColumnIndex("score")));
                list.add(infor);
            }while (cursor.moveToNext());
        }
        closeDatabase();
        return list;
    }
    public List<Infor> query(String name,String subject){
        openDatabase();
        List<Infor> list=new ArrayList<Infor>();
        Cursor cursor=db.query("information",null,"name=? and subject=?",new String[]{name,subject},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Infor infor=new Infor();
                infor.setName(cursor.getString(cursor.getColumnIndex("name")));
                infor.setSubject(cursor.getString(cursor.getColumnIndex("subject")));
                infor.setScore(cursor.getFloat(cursor.getColumnIndex("score")));
                list.add(infor);
            }while (cursor.moveToNext());
        }
        closeDatabase();
        return list;
    }
    //修改
    public boolean update(Infor infor){
        boolean result=false;
        openDatabase();
        ContentValues values=new ContentValues();
        values.put("score",infor.getScore());
        int num=db.update("information",values,"name=? and subject=?",new String[]{infor.getName(),infor.getSubject()});
        if(num>0){
            result=true;
            Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"无法修改",Toast.LENGTH_SHORT).show();
        }
        closeDatabase();
        return result;
    }
    //删除
    public boolean delete(String name,String subject){
        openDatabase();
        boolean result=false;
        int num=db.delete("information","name=? and subject=?",new String[]{name,subject});
        if(num>0){
            result=true;
            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"无法删除",Toast.LENGTH_SHORT).show();
        }
        closeDatabase();
        return result;
    }*/



}

