package com.puyue.www.qiaoge.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.MyAdapter;
import com.puyue.www.qiaoge.adapter.MyTestAdapter;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2021/1/8
 */
public class TestActivity4 extends BaseSwipeActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    List<String> list = new ArrayList<>();
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.test5);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
    }

    @Override
    public void setViewData() {
        for (int i = 0; i < 100; i++) {
            list.add("ss");
        }

        MyAdapter myAdapter = new MyAdapter(mActivity,list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.fling(0);
                scrollView.smoothScrollTo(0, 0);
            }
        });
    }

    @Override
    public void setClickEvent() {

    }
}
