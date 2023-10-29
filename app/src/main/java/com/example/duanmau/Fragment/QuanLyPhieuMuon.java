package com.example.duanmau.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.Adapter.Adapter_PhieuMuon;
import com.example.duanmau.Adapter.SpinnerLoaiSach_Adapter;
import com.example.duanmau.Adapter.SpinnerSach_Apdapter;
import com.example.duanmau.Adapter.SpinnerThanhVien_Adapter;
import com.example.duanmau.DAO.LoaiSachDAO;
import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.DAO.ThanhVienDAO;
import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuanLyPhieuMuon extends Fragment {
    private List<PhieuMuon> list;
    private PhieuMuonDAO phieuMuonDAO;
    private Adapter_PhieuMuon adapter;
    private ListView listView;
    private EditText idMaPM;
    private Spinner spTV,spSach;
    private TextView tienThue,ngay;
    private CheckBox checkBoxTrangThai;
    private Dialog viewDiaLog;
    private SpinnerSach_Apdapter sachApdapter;
    private SpinnerThanhVien_Adapter thanhVienAdapter;
    private List<Sach> listSach;
    private List<ThanhVien> thanhVienList;
    private int maTV,maSach, tienThuePos,posTv,posSach;
    private PhieuMuon phieuMuon;
    private Button btnSave,btnCancel;
    private FloatingActionButton btnAdd;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_phieu_muon,container,false);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        listView = view.findViewById(R.id.lsPhieuMuon);
        btnAdd = view.findViewById(R.id.btnFloat);
        btnAdd.setOnClickListener(v->{
            openDiaLog(getContext(),0);
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                phieuMuon =list.get(position);
                openDiaLog(getContext(),1);
                return false;
            }
        });
        capNhat();

        return view;
    }
    public void capNhat(){
        list = phieuMuonDAO.readAllDataPM();
        adapter = new Adapter_PhieuMuon(list,getActivity());
        listView.setAdapter(adapter);
    }
    public void openDiaLog(Context context, int type){
        viewDiaLog = new Dialog(context);
        viewDiaLog.setContentView(R.layout.layout_dialog_phieumuon);
        idMaPM = viewDiaLog.findViewById(R.id.diaMaPM);
        idMaPM.setEnabled(false);
        spTV = viewDiaLog.findViewById(R.id.spTV);
        spSach = viewDiaLog.findViewById(R.id.spSach);
        tienThue = viewDiaLog.findViewById(R.id.diaTienThue);
        ngay = viewDiaLog.findViewById(R.id.diaNgay);
        checkBoxTrangThai = viewDiaLog.findViewById(R.id.checkBoxTrangThai);
        btnSave = viewDiaLog.findViewById(R.id.btnSave);
        btnCancel = viewDiaLog.findViewById(R.id.btnCancel);
        ngay.setText("Ngày thuê: "+simpleDateFormat.format(new Date()));
        thanhVienList = new ArrayList<>();
        listSach = new ArrayList<>();
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        thanhVienList =thanhVienDAO.readAllDataTV();
        SachDAO sachDAO = new SachDAO(getContext());
        listSach = sachDAO.readAllDataS();
        thanhVienAdapter = new SpinnerThanhVien_Adapter(thanhVienList,getContext());
        sachApdapter = new SpinnerSach_Apdapter(listSach,getContext());
        spSach.setAdapter(sachApdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThuePos = listSach.get(position).getGiaThue();
                tienThue.setText("Tiền thuê: "+tienThuePos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTV.setAdapter(thanhVienAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTV = thanhVienList.get(position).getMaTV();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnCancel.setOnClickListener(v->{
            viewDiaLog.dismiss();
        });
        if(type!=0){
            idMaPM.setText(String.valueOf(phieuMuon.getMaPM()));
            for (int i=0;i<thanhVienList.size();i++){
                if (phieuMuon.getMaTV()==(thanhVienList.get(i).getMaTV())){
                    posTv =i;
                }
            }
            spTV.setSelection(posTv);
            for (int i=0;i<listSach.size();i++){
                if (phieuMuon.getMaSach()==(listSach.get(i).getMaSach())){
                    posSach =i;
                }
            }
            spSach.setSelection(posSach);
            ngay.setText("Ngày thuê: "+simpleDateFormat.format(phieuMuon.getNgay()));
            tienThue.setText("Tiền thuê: "+phieuMuon.getTienThue());
            if(phieuMuon.getTraSach()==1){
                checkBoxTrangThai.setChecked(true);
            }else {
                checkBoxTrangThai.setChecked(false);
            }

        }
        btnSave.setOnClickListener(v->{
            phieuMuon = new PhieuMuon();
            phieuMuon.setMaSach(maSach);
            phieuMuon.setMaTV(maTV);
            phieuMuon.setNgay(new Date());
            phieuMuon.setTienThue(tienThuePos);
            if(checkBoxTrangThai.isChecked()){
                phieuMuon.setTraSach(1);
            }else {
                phieuMuon.setTraSach(0);
            }
            if(type==0){
                phieuMuonDAO.insertPM(phieuMuon);
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }else {
                phieuMuon.setMaPM(Integer.parseInt(idMaPM.getText().toString()));
                phieuMuonDAO.updatePM(phieuMuon);
                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }
            capNhat();
            viewDiaLog.dismiss();
        });
        viewDiaLog.show();

    }
}
