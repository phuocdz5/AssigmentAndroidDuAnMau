package com.example.duanmau.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.DatabaseHelper.DBThuVien;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.Model.ThuThu;

public class DemoDB {
    private SQLiteDatabase db;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    static final String TAG="//==============";
    public DemoDB(Context context) {
        DBThuVien dbThuVien = new DBThuVien(context);
        db = dbThuVien.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
    }
    public void thanhVien(){
        ThanhVien thanhVien = new ThanhVien(1,"Nguyễn Tiến Phước","2004");
        if(thanhVienDAO.insertTV(thanhVien)>0){
            Log.i(TAG,"Thêm thành công");
        }else {
            Log.i(TAG,"Thêm thất bại");
        }
        Log.i(TAG,"=====================================");
        Log.i(TAG,"Tổng thành viên là: "+thanhVienDAO.readAllDataTV().size());
        thanhVien = new ThanhVien(5,"Nguyễn Văn Phước","2000");
        thanhVienDAO.updateTV(thanhVien);
        Log.i(TAG,"=============== sau khi sửa ======================");
        Log.i(TAG,"Tổng thành viên là: "+thanhVienDAO.readAllDataTV().size());
        thanhVienDAO.deleteTV(5);
        Log.i(TAG,"=============== sau khi xóa ======================");
        Log.i(TAG,"Tổng thành viên là: "+thanhVienDAO.readAllDataTV().size());

    }
    public void thuThu(){
        ThuThu thuThu = new ThuThu("phuocdz1312","Nguyễn Tiến Phước","admin123");
        if(thuThuDAO.insertTT(thuThu)>0){
            Log.i(TAG,"Thêm thành công");
        }else {
            Log.i(TAG,"Thêm thất bại");
        }
        Log.i(TAG,"=====================================");
        Log.i(TAG,"Tổng thành viên là: "+thuThuDAO.readAllDataTT().size());
        thuThu = new ThuThu("phuocdz","Nguyễn Tiến Phước","admin123954sa6");
        thuThuDAO.updatePassTT(thuThu);
        Log.i(TAG,"=============== sau khi sửa ======================");
        Log.i(TAG,"Tổng thành viên là: "+thuThuDAO.readAllDataTT().size());
        thuThuDAO.deleteTT("phuocdz1312");
        Log.i(TAG,"=============== sau khi xóa ======================");
        Log.i(TAG,"Tổng thành viên là: "+thuThuDAO.readAllDataTT().size());
    }

}
