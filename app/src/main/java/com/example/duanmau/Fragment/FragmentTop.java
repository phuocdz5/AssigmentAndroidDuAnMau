package com.example.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.Adapter.Adapter_Top;
import com.example.duanmau.DAO.PhieuMuonDAO;
import com.example.duanmau.Model.Top;
import com.example.duanmau.R;

import java.util.List;

public class FragmentTop extends Fragment {
    private ListView listView;
    private List<Top> list;
    private Adapter_Top adapterTop;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top,container,false);
        listView = view.findViewById(R.id.lsTop);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = (List<Top>) phieuMuonDAO.getTop();
        adapterTop = new Adapter_Top(list,getContext());
        listView.setAdapter(adapterTop);
        return view;
    }
}
