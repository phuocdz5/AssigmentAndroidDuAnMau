package com.example.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.DatabaseHelper.DBThuVien;
import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PhieuMuonDAO(Context context) {
        this.context = context;
        DBThuVien dbThuVien = new DBThuVien(context);
        db = dbThuVien.getWritableDatabase();
    }
    public long insertPM(PhieuMuon obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT",obj.getMaTT());
        contentValues.put("maTV",obj.getMaTV());
        contentValues.put("maSach",obj.getMaSach());
        contentValues.put("tienThue",obj.getTienThue());
        contentValues.put("ngay",sdf.format(obj.getNgay()));
        contentValues.put("traSach",obj.getTraSach());
        return db.insert("PhieuMuon",null,contentValues);
    }
    public long updatePM(PhieuMuon obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT",obj.getMaTT());
        contentValues.put("maTV",obj.getMaTV());
        contentValues.put("maSach",obj.getMaSach());
        contentValues.put("tienThue",obj.getTienThue());
        contentValues.put("ngay",sdf.format(obj.getNgay()));
        contentValues.put("traSach",obj.getTraSach());
        return db.update("PhieuMuon",contentValues," maPM=? ", new String[]{String.valueOf(obj.getMaPM())});
    }
    public long deletePM(int id){
        return db.delete("PhieuMuon"," maPM=? ", new String[]{String.valueOf(id)});
    }
    public List<PhieuMuon> getAllDataPM(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPM(Integer.parseInt(cursor.getString(0)));
            obj.setMaTT(cursor.getString(1));
            obj.setMaTV(Integer.parseInt(cursor.getString(2)));
            obj.setMaSach(Integer.parseInt(cursor.getString(3)));
            obj.setTienThue(Integer.parseInt(cursor.getString(4)));
            try{
                obj.setNgay(sdf.parse(cursor.getString(5)));
            }catch (ParseException e){
                e.printStackTrace();
            }
            obj.setTraSach(Integer.parseInt(cursor.getString(6)));
            list.add(obj);
        }
        return list;
    }
    public List<PhieuMuon> readAllDataPM(){
        String sql ="select * from PhieuMuon";
        return getAllDataPM(sql);
    }
    public PhieuMuon getIdPM(int id){
        String sql ="select * from PhieuMuon where maPM=?";
        List<PhieuMuon> list = getAllDataPM(sql,String.valueOf(id));
        return list.get(0);
    }
    //Thong ke top 10
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sql ="select maSach, count(maSach) as soLuong from PhieuMuon group by maSach order by soLuong desc limit 10";
        List<Top> list = new ArrayList<>();
        SachDAO SachDAO = new SachDAO(context);

        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Top obj = new Top();
            Sach sach = SachDAO.getIdS(String.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")))));
            obj.setTenSach(sach.getTenSach());
            obj.setSoLuong(Integer.parseInt(cursor.getString(1)));
            list.add(obj);
        }
        return list;
    }
    //doanhthu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sql ="select SUM(tienThue) as doanhThu from PhieuMuon where ngay between ? and ? ";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
