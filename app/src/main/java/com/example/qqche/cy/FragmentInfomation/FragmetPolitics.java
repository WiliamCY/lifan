package com.example.qqche.cy.FragmentInfomation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqche.cy.Adapter.CommendAdapter;
import com.example.qqche.cy.Adapter.HotPostAdapter;
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

public class FragmetPolitics extends Fragment {
    Unbinder unbinder;
    private CommendAdapter commendAdapter;
    private XRecyclerView commendXecyview;
    private List<Commend> commendList = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_politics, container, false);
        unbinder = ButterKnife.bind(this, view);

        commendXecyview = view.findViewById(R.id.police_xecyview);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        commendXecyview.setLayoutManager(layoutManager);
        commendAdapter = new CommendAdapter(commendList);
        commendXecyview.setAdapter(commendAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
