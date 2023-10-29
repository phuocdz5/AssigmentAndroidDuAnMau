package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import java.util.List;

public class SpinnerThanhVien_Adapter extends BaseAdapter {
    private List<ThanhVien> list;
    private Context context;

    public SpinnerThanhVien_Adapter(List<ThanhVien> list, Context context) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.thanhvien_spinner,parent,false);
        ThanhVien thanhVien = list.get(position);
        TextView tvIDTV = view.findViewById(R.id.tvIdTVSP);
        TextView tvTenTV = view.findViewById(R.id.tvTenTVSP);
        tvIDTV.setText(thanhVien.getMaTV()+". ");
        tvTenTV.setText(thanhVien.getHoTen());
        return view;
    }
}
