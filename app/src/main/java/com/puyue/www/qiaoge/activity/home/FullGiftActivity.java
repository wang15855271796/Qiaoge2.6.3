package com.puyue.www.qiaoge.activity.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.FullGiftAdapter;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/8/18
 */
public class FullGiftActivity  extends BaseSwipeActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<String> list = new ArrayList<>();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_full);
    }

    @Override
    public void findViewById() {
        for (int i = 0; i < 5; i++) {
            list.add("ss");
        }
    }

    @Override
    public void setViewData() {
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        FullGiftAdapter fullGiftAdapter = new FullGiftAdapter(R.layout.item_full_gift,list);
        recyclerView.setAdapter(fullGiftAdapter);
    }

    @Override
    public void setClickEvent() {

    }
}
