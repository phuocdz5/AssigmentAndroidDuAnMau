package com.example.duanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.Model.LoaiSach;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Sach extends BaseAdapter {
    private List<Sach> list;
    private Context context;
    private SachDAO sachDAO;
    private LoaiSachDAO loaiSachDAO;
    private LoaiSach loaiSach = new LoaiSach();
    private View viewDiaLog;
    private List<LoaiSach> listLS;
    private EditText edMaSach, edTenSach,edgiaThue;
    private Spinner spLoaiSach;
    private SpinnerLoaiSach_Adapter adapter;
    private Sach sach = new Sach();
    int maLoai,pos;

    public Adapter_Sach(List<Sach> list, Context context) {
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
        convertView = inflater.inflate(R.layout.list_view_sach,parent,false);
        sachDAO = new SachDAO(context);
        Sach lsSach = list.get(position);
        loaiSachDAO = new LoaiSachDAO(context);
        loaiSach = loaiSachDAO.getIDLS(String.valueOf(lsSach.getMaLoai()));
        TextView tvMaSach = convertView.findViewById(R.id.tvMaSach);
        TextView tvTenSach = convertView.findViewById(R.id.tvTenSach);
        TextView tvGiaThue = convertView.findViewById(R.id.tvGiaThue);
        TextView tvLoaiSach = convertView.findViewById(R.id.tvLoaiSach);
        ImageView btnDel = convertView.findViewById(R.id.btnDel);
        tvMaSach.setText("Mã sách: "+lsSach.getMaSach());
        tvTenSach.setText("Tên sách: "+lsSach.getTenSach());
        tvGiaThue.setText("Giá thuê: "+lsSach.getGiaThue());
        tvLoaiSach.setText("Loại sách: "+loaiSach.getTenLoai());

        int id = lsSach.getMaSach();
        btnDel.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có muốn xóa không");
            builder.setCancelable(true);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sachDAO.deleteS(id);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list = sachDAO.readAllDataS();
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
        convertView.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cập nhật");
            dataDiaLog();
            edMaSach.setText(String.valueOf(lsSach.getMaSach()));
            edTenSach.setText(String.valueOf(lsSach.getTenSach()));
            edgiaThue.setText(String.valueOf(lsSach.getGiaThue()));
            for(int i=0; i<listLS.size();i++){
                if(lsSach.getMaLoai()==listLS.get(i).getMaLoai()){
                    pos =i;
                }
            }
            spLoaiSach.setSelection(pos);
            edMaSach.setEnabled(false);
            builder.setView(viewDiaLog);
            builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!edTenSach.getText().toString().isEmpty()||!edgiaThue.getText().toString().isEmpty()){
                        sach.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        sach.setTenSach(edTenSach.getText().toString());
                        try {
                            sach.setGiaThue(Integer.parseInt(edgiaThue.getText().toString()));


                        }catch (NumberFormatException e){
                            Toast.makeText(context, "Không được nhập chữ ", Toast.LENGTH_SHORT).show();

                        }
                        sach.setMaLoai(maLoai);
                        sachDAO.updateS(sach);
                        list = sachDAO.readAllDataS();
                        notifyDataSetChanged();
                    }else {
                        Toast.makeText(context, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();

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
        viewDiaLog = View.inflate(context,R.layout.layout_dialog_sach,null);
        edMaSach=viewDiaLog.findViewById(R.id.diaMaSach);
        edTenSach = viewDiaLog.findViewById(R.id.diaTenSach);
        edgiaThue = viewDiaLog.findViewById(R.id.diaGiaThue);
        spLoaiSach =  viewDiaLog.findViewById(R.id.spLoaiSach);
        listLS = new ArrayList<>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLS = loaiSachDAO.readAllDataLS();
        adapter = new SpinnerLoaiSach_Adapter(context,listLS);
        spLoaiSach.setAdapter(adapter);
        spLoaiSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoai =listLS.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
