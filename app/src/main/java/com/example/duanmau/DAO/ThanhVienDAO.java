package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.DatabaseHelper.DBThuVien;
import com.example.duanmau.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DBThuVien dbThuVien = new DBThuVien(context);
        db = dbThuVien.getWritableDatabase();
    }
    public long insertTV(ThanhVien obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",obj.getHoTen());
        contentValues.put("namSinh",obj.getNamSinh());

        return db.insert("ThanhVien",null,contentValues);

    }
    public long updateTV(ThanhVien obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",obj.getHoTen());
        contentValues.put("namSinh",obj.getNamSinh());
        return db.update("ThanhVien",contentValues," maTV=? ", new String[]{String.valueOf(obj.getMaTV())});
    }
    public long deleteTV(int id){
        return db.delete("ThanhVien"," maTV=? ", new String[]{String.valueOf(id)});
    }
    public List<ThanhVien> getAllDataTV(String sql,String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(Integer.parseInt(cursor.getString(0)));
            obj.setHoTen(cursor.getString(1));
            obj.setNamSinh(cursor.getString(2));
            list.add(obj);
        }
        return list;
    }
    public List<ThanhVien> readAllDataTV(){
        String sql ="select * from ThanhVien";
        return getAllDataTV(sql);
    }
    public ThanhVien getIdTV(String id){
        String sql ="select * from ThanhVien where maTV=?";
        List<ThanhVien> list = getAllDataTV(sql,id);
        return list.get(0);

    }

}
