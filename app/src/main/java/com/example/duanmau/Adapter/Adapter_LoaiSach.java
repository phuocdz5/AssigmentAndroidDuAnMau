package com.example.duanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.Model.LoaiSach;
import com.example.duanmau.R;

import java.util.List;

public class Adapter_LoaiSach extends BaseAdapter {
    private Context context;
    private List<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;
    private View viewDiaLog;
    private EditText edMaLoai,edTenLoai;

    public Adapter_LoaiSach(Context context, List<LoaiSach> list) {
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
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_view_loaisach,parent,false);
        loaiSachDAO = new LoaiSachDAO(context);
        LoaiSach loaiSach= list.get(position);
        TextView maLoai = convertView.findViewById(R.id.tvMaLS);
        TextView tenLoai = convertView.findViewById(R.id.tvTenLoai);
        ImageView btnDel = convertView.findViewById(R.id.btnDel);
        maLoai.setText("Mã loại sách: "+loaiSach.getMaLoai());
        tenLoai.setText("Tên loại sách: "+loaiSach.getTenLoai());
        int id = loaiSach.getMaLoai();
        btnDel.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn xóa không ?");
            builder.setCancelable(true);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    loaiSachDAO.deleteLS(id);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list = loaiSachDAO.readAllDataLS();
                    notifyDataSetChanged();

                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        convertView.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            dataDiaLog();
            edMaLoai.setText(String.valueOf(loaiSach.getMaLoai()));
            edTenLoai.setText(loaiSach.getTenLoai());
            edMaLoai.setEnabled(false);
            builder.setTitle("Cập nhật");
            builder.setView(viewDiaLog);
            builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!edTenLoai.getText().toString().isEmpty()){
                        loaiSachDAO.updateLS(new LoaiSach(Integer.parseInt(edMaLoai.getText().toString()),edTenLoai.getText().toString()));
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        list = loaiSachDAO.readAllDataLS();
                        notifyDataSetChanged();
                    }

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
    public void dataDiaLog(){
        viewDiaLog = View.inflate(context,R.layout.layout_dialog_loaisach,null);
        edMaLoai = viewDiaLog.findViewById(R.id.diaMaLoai);
        edTenLoai = viewDiaLog.findViewById(R.id.diaLoaiSach);

    }
}
