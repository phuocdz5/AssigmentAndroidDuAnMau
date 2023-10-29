package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.DatabaseHelper.DBThuVien;
import com.example.duanmau.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DBThuVien dbThuVien = new DBThuVien(context);
        db = dbThuVien.getWritableDatabase();
    }
    public long insertTT(ThuThu obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT",obj.getMaTT());
        contentValues.put("hoTen",obj.getHoTen());
        contentValues.put("matKhau",obj.getMatKhau());
        return db.insert("ThuThu",null,contentValues);
    }
    public long updatePassTT(ThuThu obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",obj.getHoTen());
        contentValues.put("matKhau",obj.getMatKhau());
        return db.update("ThuThu",contentValues," maTT=? ", new String[]{obj.getMaTT()});
    }
    public long deleteTT(String id){
        return db.delete("ThuThu"," maTT=? ", new String[]{id});

    }
    public List<ThuThu> getAllDataTT(String sql,String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while(cursor.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.setMaTT(cursor.getString(0));
            obj.setHoTen(cursor.getString(1));
            obj.setMatKhau(cursor.getString(2));
            list.add(obj);
        }
        return list;
    }
    public List<ThuThu> readAllDataTT(){
        String sql ="select * from ThuThu";
        return getAllDataTT(sql);
    }
    public ThuThu getIDTT(String id){
        String sql ="select * from ThuThu where maTT=? ";
        List<ThuThu> list = getAllDataTT(sql,id);
        return list.get(0);
    }
    public boolean checkUser(String maTT){
        Cursor cursor =db.rawQuery("select * from ThuThu where maTT=? ", new String[]{maTT});
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public boolean checkLogin(String maTT,String matKhau){
        Cursor cursor = db.rawQuery("select * from ThuThu where maTT=? and matKhau=? ", new String[]{maTT,matKhau});
        if (cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public boolean checkAccount(){
        String sql ="select * from ThuThu ";
        List<ThuThu> list = getAllDataTT(sql);
        if(list.size()>4){
            return true;
        }
        return false;
    }

}
