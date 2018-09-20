package com.example.qqche.cy.FragmentInfomation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.HotPostAdapter;
import com.example.qqche.cy.Adapter.InforMationMoneyAdapter;
import com.example.qqche.cy.Class.Commend;
import com.example.qqche.cy.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/28.
 */

public class FragmenTinance extends Fragment {
    private XRecyclerView money_xecyview;
    private List<Commend> commendList = new ArrayList<>();
    private InforMationMoneyAdapter inforMationMoneyAdapter;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finance, container, false);
        unbinder = ButterKnife.bind(this, view);
        money_xecyview = view.findViewById(R.id.money_xecyview);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        money_xecyview.setLayoutManager(layoutManager);
        inforMationMoneyAdapter = new InforMationMoneyAdapter(commendList);
        money_xecyview.setAdapter(inforMationMoneyAdapter);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
