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

import com.example.duanmau.DAO.ThanhVienDAO;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import java.util.List;

public class Adapter_QuanLyTV extends BaseAdapter {
    private Context context;
    private List<ThanhVien> list;
    private View viewDiaLog;
    private EditText edMaTV, edHoTen,edNamSinh;


    public Adapter_QuanLyTV(Context context, List<ThanhVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list !=null?list.size():0;
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
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_view_thanhvien,parent,false);
        TextView tvMaTV= convertView.findViewById(R.id.tvMaTV);
        TextView tvhoTen= convertView.findViewById(R.id.tvhoTen);
        TextView tvnamSinh= convertView.findViewById(R.id.tvnamSinh);
        ImageView btnDele = convertView.findViewById(R.id.btnDel);
        tvMaTV.setText("Mã thành viên: "+list.get(position).getMaTV());
        tvhoTen.setText("Họ và tên: "+list.get(position).getHoTen());
        tvnamSinh.setText("Năm sinh: "+list.get(position).getNamSinh());
        int id = list.get(position).getMaTV();
        btnDele.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn xóa không");
            builder.setCancelable(true);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    thanhVienDAO.deleteTV(id);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list = thanhVienDAO.readAllDataTV();
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
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                diaLogData();
                edMaTV.setText(String.valueOf(list.get(position).getMaTV()));
                edHoTen.setText(list.get(position).getHoTen());
                edNamSinh.setText(list.get(position).getNamSinh());
                edMaTV.setEnabled(false);
                builder.setTitle("Cập nhật");
                builder.setView(viewDiaLog);
                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(checkValue(edHoTen.getText().toString(),edNamSinh.getText().toString())){
                            thanhVienDAO.updateTV(new ThanhVien(Integer.parseInt(edMaTV.getText().toString()),edHoTen.getText().toString(),edNamSinh.getText().toString()));
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            list=thanhVienDAO.readAllDataTV();
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
            }
        });
        return convertView;
    }
    public void diaLogData(){
        viewDiaLog = View.inflate(context,R.layout.layout_dialog_thanhvien,null);
        edMaTV = viewDiaLog.findViewById(R.id.diaMaTv);
        edHoTen = viewDiaLog.findViewById(R.id.diahoTen);
        edNamSinh = viewDiaLog.findViewById(R.id.diaNamSinh);
    }
    public boolean checkValue(String hoVaTen,String namSinh){
        if(hoVaTen.isEmpty() || namSinh.isEmpty()){
            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
