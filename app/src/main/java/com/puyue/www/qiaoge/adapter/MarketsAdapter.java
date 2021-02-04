package com.puyue.www.qiaoge.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;


import com.puyue.www.qiaoge.RoundImageView;

import com.puyue.www.qiaoge.activity.home.ShopDetailActivity;
import com.puyue.www.qiaoge.model.InfoListModel;


import java.util.List;

/**
 * Created by ${王涛} on 2021/1/3
 */
public class MarketsAdapter extends BaseQuickAdapter<InfoListModel.DataBean.ListBean,BaseViewHolder> {
    public MarketsAdapter(int layoutResId, @Nullable List<InfoListModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListModel.DataBean.ListBean item) {
        if(item.getPictureList()!=null&&item.getPictureList().size()>0) {
            RoundImageView iv_pic = helper.getView(R.id.iv_pic);
            Glide.with(mContext).load(item.getPictureList().get(0)).into(iv_pic);
        }
        helper.setText(R.id.tv_title,item.getContent());
        helper.setText(R.id.tv_time,item.getCreateTime());
        helper.setText(R.id.tv_num,item.getBrowseNum());
        helper.setText(R.id.tv_address,item.getAreaName());
        helper.getView(R.id.rl_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ShopDetailActivity.class);
                intent.putExtra("msgId",item.getMsgId());
                mContext.startActivity(intent);
            }
        });
    }
}
