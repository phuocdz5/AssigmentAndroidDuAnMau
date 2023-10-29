package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Model.Sach;
import com.example.duanmau.R;

import java.util.List;

public class SpinnerSach_Apdapter extends BaseAdapter {
    private List<Sach> list;
    private Context context;

    public SpinnerSach_Apdapter(List<Sach> list, Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.sach_spinner,parent,false);
        Sach sach = list.get(position);
        TextView idSach = view.findViewById(R.id.tvIdSachSP);
        TextView tenSach = view.findViewById(R.id.tvTenSachSP);
        idSach.setText(sach.getMaSach()+". ");
        tenSach.setText(String.valueOf(sach.getTenSach()));
        return view;
    }
}
