package com.example.duanmau.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.DAO.ThanhVienDAO;
import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Adapter_PhieuMuon extends BaseAdapter {
    private List<PhieuMuon> list;
    private Context context;
    private TextView tvMaPhieuMuon,tvThanhVien,tvTenSach,tvTrangThai,tvTienThue,tvNgayThue;
    private ImageView btnDel;
    private PhieuMuonDAO phieuMuonDAO;
    private SachDAO sachDAO;
    private ThanhVienDAO thanhVienDAO;
    private PhieuMuon phieuMuonList;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");



    public Adapter_PhieuMuon(List<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_view_phieumuon,parent,false);
        phieuMuonDAO = new PhieuMuonDAO(context);
        phieuMuonList = list.get(position);
        sachDAO = new SachDAO(context);
        Sach sach = sachDAO.getIdS(String.valueOf(phieuMuonList.getMaSach()));
        thanhVienDAO = new ThanhVienDAO(context);
        ThanhVien thanhVien = thanhVienDAO.getIdTV(String.valueOf(phieuMuonList.getMaTV()));
        tvMaPhieuMuon = convertView.findViewById(R.id.tvMaPhieuMuon);
        tvThanhVien = convertView.findViewById(R.id.tvThanhVien);
        tvTenSach = convertView.findViewById(R.id.tvTenSach);
        tvTrangThai = convertView.findViewById(R.id.tvTrangThai);
        tvTienThue = convertView.findViewById(R.id.tvTienThue);
        tvNgayThue = convertView.findViewById(R.id.tvNgayThue);
        btnDel = convertView.findViewById(R.id.btnDel);
        tvMaPhieuMuon.setText("Mã phiếu mượn: "+phieuMuonList.getMaPM());
        tvThanhVien.setText("Thành viên: "+thanhVien.getHoTen());
        tvTenSach.setText("Tên sách: "+sach.getTenSach());
        tvTienThue.setText("Tiền thuê: "+phieuMuonList.getTienThue());
        tvNgayThue.setText("Ngày thuê: "+sdf.format(phieuMuonList.getNgay()));
        if(phieuMuonList.getTraSach()==1){
            tvTrangThai.setTextColor(Color.GREEN);
            tvTrangThai.setText("Đã trả sách");
        }else {
            tvTrangThai.setTextColor(Color.RED);
            tvTrangThai.setText("Chưa trả sách");
        }
        int idDel =phieuMuonList.getMaPM();
        btnDel.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn xóa không");
            builder.setCancelable(true);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    phieuMuonDAO.deletePM(idDel);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list = phieuMuonDAO.readAllDataPM();
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
        return convertView;
    }

}
