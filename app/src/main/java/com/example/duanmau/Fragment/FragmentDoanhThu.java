package com.example.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FragmentDoanhThu extends Fragment {
    private Button btnTuNgay, btnDenNgay,btnDoanhThu;
    private EditText edTuNgay,edDenNgay;
    private TextView tvDoanhThu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private int mYear,mMonth,mDay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu,container,false);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDenNgay = view.findViewById(R.id.btndenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.eddenNgay);
        tvDoanhThu = view.findViewById(R.id.tvDoanhthu);
        btnTuNgay.setOnClickListener(v->{
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),0,mdateTuNgay,mYear,mMonth,mDay);
            datePickerDialog.show();

        });
        btnDenNgay.setOnClickListener(v->{
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),0,mdateDenNgay,mYear,mMonth,mDay);
            datePickerDialog.show();
        });
        btnDoanhThu.setOnClickListener(v->{
            String tuNgay = edTuNgay.getText().toString();
            String denNgay= edDenNgay.getText().toString();
            PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
            tvDoanhThu.setText("Doanh thu: "+phieuMuonDAO.getDoanhThu(tuNgay,denNgay)+" VNĐ");
        });
        return view;
    }
    DatePickerDialog.OnDateSetListener mdateTuNgay= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth=month;
            mDay =dayOfMonth;
            GregorianCalendar calendar = new GregorianCalendar(mYear,mMonth,mDay);
            edTuNgay.setText(sdf.format(calendar.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mdateDenNgay= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth=month;
            mDay =dayOfMonth;
            GregorianCalendar calendar = new GregorianCalendar(mYear,mMonth,mDay);
            edDenNgay.setText(sdf.format(calendar.getTime()));
        }
    };
}
