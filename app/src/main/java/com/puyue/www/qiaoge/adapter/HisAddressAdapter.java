package com.puyue.www.qiaoge.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.HisModel;

import java.util.List;

/**
 * Created by ${王涛} on 2020/11/20
 */
public class HisAddressAdapter extends BaseQuickAdapter<HisModel.DataBean,BaseViewHolder> {

    public HisAddressAdapter(int layoutResId, @Nullable List<HisModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HisModel.DataBean item) {
        TextView tv_address = helper.getView(R.id.tv_address);
        ImageView iv_choose = helper.getView(R.id.iv_choose);
        tv_address.setText(item.getDetailAddress());

    }
}
