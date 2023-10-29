package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Model.LoaiSach;
import com.example.duanmau.R;

import java.util.List;

public class SpinnerLoaiSach_Adapter extends BaseAdapter {
    private Context context;
    private List<LoaiSach> list;


    public SpinnerLoaiSach_Adapter(Context context, List<LoaiSach> list) {
        this.context = context;
        this.list = list;
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
        View view = LayoutInflater.from(context).inflate(R.layout.loaisach_spinner,parent,false);
        LoaiSach loaiSach = list.get(position);
        TextView tvLoaiSach = view.findViewById(R.id.textLoaiSach);
        tvLoaiSach.setText(loaiSach.getTenLoai());
        return view;
    }
}
