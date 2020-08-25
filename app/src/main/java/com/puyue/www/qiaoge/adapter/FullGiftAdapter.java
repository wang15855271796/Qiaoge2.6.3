package com.puyue.www.qiaoge.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ${王涛} on 2020/8/18
 */
public class FullGiftAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public FullGiftAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
