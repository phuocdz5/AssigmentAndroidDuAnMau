package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.DatabaseHelper.DBThuVien;
import com.example.duanmau.Model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;


    public SachDAO(Context context) {
        DBThuVien dbThuVien = new DBThuVien(context);
        db = dbThuVien.getWritableDatabase();
    }
    public long insertS(Sach obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach",obj.getTenSach());
        contentValues.put("giaThue",obj.getGiaThue());
        contentValues.put("maLoai",obj.getMaLoai());
        return db.insert("Sach",null,contentValues);
    }
    public long updateS(Sach obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach",obj.getTenSach());
        contentValues.put("giaThue",obj.getGiaThue());
        contentValues.put("maLoai",obj.getMaLoai());
        return db.update("Sach",contentValues," maSach=? ", new String[]{String.valueOf(obj.getMaSach())});
    }
    public long deleteS(int id){
        return db.delete("Sach"," maSach=? ", new String[]{String.valueOf(id)});
    }
    public List<Sach> getAllDataS(String sql, String...selectionAgrs){
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionAgrs);
        while (cursor.moveToNext()){
            Sach sach = new Sach();
            sach.setMaSach(Integer.parseInt(cursor.getString(0)));
            sach.setTenSach(cursor.getString(1));
            sach.setGiaThue(Integer.parseInt(cursor.getString(2)));
            sach.setMaLoai(Integer.parseInt(cursor.getString(3)));
            list.add(sach);
        }
        return list;
    }
    public List<Sach> readAllDataS(){
        String sql = "select * from Sach";
        return getAllDataS(sql);
    }
    public Sach getIdS(String id){
        String sql ="select * from Sach where maSach=?";
        List<Sach> list = getAllDataS(sql,id);
        return list.get(0);
    }

}
