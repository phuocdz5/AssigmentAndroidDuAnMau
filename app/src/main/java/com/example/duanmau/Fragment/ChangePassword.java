package com.example.duanmau.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.DAO.ThuThuDAO;
import com.example.duanmau.Model.ThuThu;
import com.example.duanmau.R;

public class ChangePassword extends Fragment {
    private EditText edPassOld,edPassNew,edConfirmPass;
    private Button btnSave;
    private ThuThuDAO thuThuDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_pass_fragment,container,false);
        thuThuDAO = new ThuThuDAO(getContext());
        edPassOld = view.findViewById(R.id.passOld);
        edPassNew = view.findViewById(R.id.passNew);
        edConfirmPass = view.findViewById(R.id.confirmPass);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v->{
            Bundle bundle = getArguments();
            String user = bundle.getString("user");
            if(checkForm()){
                ThuThu thuThu = thuThuDAO.getIDTT(user);
                thuThu.setMatKhau(edPassNew.getText().toString());
                thuThuDAO.updatePassTT(thuThu);
                if(thuThuDAO.updatePassTT(thuThu)>0){
                    Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    edPassOld.setText("");
                    edPassNew.setText("");
                    edConfirmPass.setText("");
                }else {
                    Toast.makeText(getContext(), "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                }
            }


        });
        return view;
    }
    public boolean checkForm(){
        String passOld = edPassOld.getText().toString();
        String passNew = edPassNew.getText().toString();
        String confirmPass= edConfirmPass.getText().toString();
        if(passOld.equals("")||passNew.equals("")||confirmPass.equals("")){
            Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
        }else {
            Bundle bundle = getArguments();
            String sharePassOld = bundle.getString("pass");
            if(sharePassOld.equals(passOld)){
                if(passNew.equals(confirmPass)){
                    return true;
                }else {
                    Toast.makeText(getContext(), "Vui lòng nhập mật khẩu trùng khớp", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(getContext(), "Nhập đúng mật khẩu cũ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

}
