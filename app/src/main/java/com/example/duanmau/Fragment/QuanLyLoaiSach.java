package com.example.duanmau.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.Adapter.Adapter_LoaiSach;
import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.Model.LoaiSach;
import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class QuanLyLoaiSach extends Fragment {
    private ListView listView;
    private Adapter_LoaiSach adapter_loaiSach;
    private List<LoaiSach> list;
    private LoaiSachDAO loaiSachDAO;
    private FloatingActionButton actionButton;
    private LoaiSach loaiSach;
    private View viewDiaLog;
    private EditText edMaLoai, edTenLoai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_loai_sach,container,false);
        listView = view.findViewById(R.id.lvLS);
        actionButton = view.findViewById(R.id.btnFloat);
        loaiSachDAO = new LoaiSachDAO(getContext());
        capNhat();
        actionButton.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            dataDiaLog();
            edMaLoai.setEnabled(false);
            builder.setTitle("Thêm loại sách");
            builder.setView(viewDiaLog);
            builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!edTenLoai.getText().toString().isEmpty()){
                        loaiSach = new LoaiSach();
                        loaiSach.setTenLoai(edTenLoai.getText().toString());
                        loaiSachDAO.insertLS(loaiSach);
                        capNhat();
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
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
        return view;
    }
    public void capNhat(){
        list =loaiSachDAO.readAllDataLS();
        adapter_loaiSach = new Adapter_LoaiSach(getContext(),list);
        listView.setAdapter(adapter_loaiSach);
    }
    public void dataDiaLog(){
        viewDiaLog = View.inflate(getContext(),R.layout.layout_dialog_loaisach,null);
        edMaLoai = viewDiaLog.findViewById(R.id.diaMaLoai);
        edTenLoai = viewDiaLog.findViewById(R.id.diaLoaiSach);
    }
}
