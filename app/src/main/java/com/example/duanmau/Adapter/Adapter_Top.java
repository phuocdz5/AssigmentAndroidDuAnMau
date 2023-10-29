package com.example.duanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Model.Top;
import com.example.duanmau.R;

import java.util.List;

public class Adapter_Top extends BaseAdapter {
    private List<Top> list;
    private Context context;

    public Adapter_Top(List<Top> list, Context context) {
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
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_item_top,parent,false);
        TextView tvTenSach = convertView.findViewById(R.id.tvTenSachTop);
        TextView tvSoLuong =convertView.findViewById(R.id.tvSoLuongTop);
        Top top = list.get(position);
        tvTenSach.setText("Tên sách: "+top.getTenSach());
        tvSoLuong.setText("Số lượng: "+top.getSoLuong());
        return convertView;
    }
}
