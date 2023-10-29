package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.DatabaseHelper.DBThuVien;
import com.example.duanmau.Model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DBThuVien dbThuVien = new DBThuVien(context);
        db = dbThuVien.getWritableDatabase();
    }
    public long insertLS(LoaiSach obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",obj.getTenLoai());
        return db.insert("LoaiSach",null,contentValues);
    }
    public long updateLS(LoaiSach obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",obj.getTenLoai());
        return db.update("LoaiSach",contentValues," maLoai=? ", new String[]{String.valueOf(obj.getMaLoai())});
    }
    public long deleteLS(int id){
        return db.delete("LoaiSach"," maLoai=? ", new String[]{String.valueOf(id)});
    }
    public List<LoaiSach> getAllDataLS(String sql, String...selectionAgrs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionAgrs);
        while (cursor.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai(Integer.parseInt(cursor.getString(0)));
            obj.setTenLoai(cursor.getString(1));
            list.add(obj);
        }
        return list;
    }
    public List<LoaiSach> readAllDataLS(){
        String sql = "select * from LoaiSach";
        return getAllDataLS(sql);
    }
    public LoaiSach getIDLS(String id){
        String sql ="select * from LoaiSach where maLoai=?";
        List<LoaiSach> list = getAllDataLS(sql,id);
        return list.get(0);
    }

}
