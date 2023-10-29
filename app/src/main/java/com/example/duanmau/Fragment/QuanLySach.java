package com.example.duanmau.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.Adapter.Adapter_Sach;
import com.example.duanmau.Adapter.SpinnerLoaiSach_Adapter;
import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.Model.LoaiSach;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuanLySach extends Fragment {
    private ListView listView;
    private List<Sach> list;
    private Adapter_Sach adapter;
    private SachDAO sachDAO;
    private FloatingActionButton btnAdd;
    private Sach sach = new Sach();
    private Spinner spLoaiSach;
    private SpinnerLoaiSach_Adapter spinner_adapter;
    private List<LoaiSach> listLS;
    private View viewDiaLog;
    private EditText edMaSach,edTenSach,edgiaThue;
    private LoaiSachDAO loaiSachDAO;
    private int maLoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach,container,false);
        listView = view.findViewById(R.id.lvSach);
        btnAdd = view.findViewById(R.id.btnFloat);
        sachDAO = new SachDAO(getContext());
        capNhat();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thêm sách mới");
                dataDiaLog();
                edMaSach.setEnabled(false);
                builder.setView(viewDiaLog);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!edTenSach.getText().toString().isEmpty()||!edgiaThue.getText().toString().isEmpty()){
                            sach.setTenSach(edTenSach.getText().toString());
                            try {
                                sach.setGiaThue(Integer.parseInt(edgiaThue.getText().toString()));


                            }catch (NumberFormatException e){
                                Toast.makeText(getContext(), "Không được nhập chữ ", Toast.LENGTH_SHORT).show();
                            }
                            sach.setMaLoai(maLoai);
                            sachDAO.insertS(sach);
                            capNhat();
                        }else {
                            Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
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
        return view;
    }
    public void capNhat(){
        list = sachDAO.readAllDataS();
        adapter = new Adapter_Sach(list,getContext());
        listView.setAdapter(adapter);
    }
    public void dataDiaLog(){
        viewDiaLog = View.inflate(getContext(),R.layout.layout_dialog_sach,null);
        edMaSach=viewDiaLog.findViewById(R.id.diaMaSach);
        edTenSach = viewDiaLog.findViewById(R.id.diaTenSach);
        edgiaThue = viewDiaLog.findViewById(R.id.diaGiaThue);
        spLoaiSach =  viewDiaLog.findViewById(R.id.spLoaiSach);
        listLS = new ArrayList<>();
        loaiSachDAO = new LoaiSachDAO(getContext());
        listLS = loaiSachDAO.readAllDataLS();
        spinner_adapter = new SpinnerLoaiSach_Adapter(getContext(),listLS);
        spLoaiSach.setAdapter(spinner_adapter);
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
