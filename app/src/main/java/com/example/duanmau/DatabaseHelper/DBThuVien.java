package com.example.duanmau.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBThuVien extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="DBThuVien";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_THUTHU="CREATE TABLE ThuThu (" +
            "    maTT    TEXT PRIMARY KEY," +
            "    hoTen   TEXT NOT NULL," +
            "    matKhau TEXT NOT NULL" +
            ");";
    private static final String TABLE_THANHVIEN="CREATE TABLE ThanhVien (" +
            "    maTV    INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    hoTen   TEXT NOT NULL," +
            "    namSinh TEXT NOT NULL" +
            ");";
    private static final String TABLE_LOAISACH="CREATE TABLE LoaiSach (" +
            "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    tenLoai TEXT    NOT NULL" +
            ");";
    private static final String TABLE_SACH="CREATE TABLE Sach (" +
            "    maSach  INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    tenSach TEXT    NOT NULL," +
            "    giaThue  INTEGER NOT NULL," +
            "    maLoai  INTEGER    REFERENCES LoaiSach (maLoai) " +
            ");";
    private static final String TABLE_PHIEUMUON="CREATE TABLE PhieuMuon (" +
            "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    maTT     TEXT    REFERENCES ThuThu (maTT)," +
            "    maTV     INTEGER    REFERENCES ThanhVien (maTV)," +
            "    maSach   INTEGER REFERENCES Sach (maSach)," +
            "    tienThue INTEGER NOT NULL," +
            "    ngay     DATE    NOT NULL," +
            "    traSach  INTEGER NOT NULL" +
            ");";
    public DBThuVien(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_THUTHU);
        db.execSQL(TABLE_THANHVIEN);
        db.execSQL(TABLE_LOAISACH);
        db.execSQL(TABLE_SACH);
        db.execSQL(TABLE_PHIEUMUON);
        db.execSQL(Data_SQLite.INSERT_THU_THU);
        db.execSQL(Data_SQLite.INSERT_THANH_VIEN);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu="drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien="drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach="drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach ="drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon="drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
