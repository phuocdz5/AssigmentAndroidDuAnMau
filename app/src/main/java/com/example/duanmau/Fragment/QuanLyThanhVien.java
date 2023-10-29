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

import com.example.duanmau.Adapter.Adapter_QuanLyTV;
import com.example.duanmau.DAO.ThanhVienDAO;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class QuanLyThanhVien extends Fragment {
    private List<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;
    private Adapter_QuanLyTV quanLyTV;
    private ThanhVien thanhVien;
    private View viewDiaLog;
    private EditText edMaTV,edHoTen,edNamSinh;
    private FloatingActionButton actionButton;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien,container,false);
        listView = view.findViewById(R.id.lvTV);
        actionButton= view.findViewById(R.id.btnFloat);
        thanhVienDAO= new ThanhVienDAO(getContext());
        capNhat();
        actionButton.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            diaLogData();
            edMaTV.setEnabled(false);
            builder.setTitle("Thêm thành viên");
            builder.setView(viewDiaLog);
            builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!edHoTen.getText().toString().isEmpty()||!edNamSinh.getText().toString().isEmpty()){
                        thanhVien = new ThanhVien();

                        thanhVien.setHoTen(edHoTen.getText().toString());
                        thanhVien.setNamSinh(edNamSinh.getText().toString());
                        thanhVienDAO.insertTV(thanhVien);
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
    public void diaLogData(){
        viewDiaLog = View.inflate(getContext(),R.layout.layout_dialog_thanhvien,null);
        edMaTV = viewDiaLog.findViewById(R.id.diaMaTv);
        edHoTen = viewDiaLog.findViewById(R.id.diahoTen);
        edNamSinh = viewDiaLog.findViewById(R.id.diaNamSinh);
    }
    public void capNhat(){
        list = thanhVienDAO.readAllDataTV();
        quanLyTV= new Adapter_QuanLyTV(getContext(),list);
        listView.setAdapter(quanLyTV);
    }
}
