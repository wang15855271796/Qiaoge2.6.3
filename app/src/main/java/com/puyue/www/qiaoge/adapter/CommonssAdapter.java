package com.puyue.www.qiaoge.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.RoundImageView;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.adapter.home.SeckillGoodActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;


import java.util.List;

/**
 * Created by ${王涛} on 2020/8/29
 */
public class CommonssAdapter extends  RecyclerView.Adapter<CommonssAdapter.BaseViewHolder> {
    List<CouponModel.DataBean.ActivesBean> fullActive;
    Context mActivity;
    CouponModel.DataBean.ActivesBean activesBean;
    public CommonssAdapter(FragmentActivity mActivity, List<CouponModel.DataBean.ActivesBean> fullActive) {
        this.mActivity = mActivity;
        this.fullActive = fullActive;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_full_list, viewGroup, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        try {
            activesBean = fullActive.get(position % fullActive.size());
            viewHolder.tv_name.setText(activesBean.getProductName());
            viewHolder.tv_price.setText(activesBean.getPrice());
            Glide.with(mActivity).load(activesBean.getDefaultPic()).into(viewHolder.iv_pic);
        }catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        RoundImageView iv_pic;

        TextView tv_name;
        TextView tv_price;
        public BaseViewHolder(View view) {
            super(view);

            iv_pic = (RoundImageView) view.findViewById(R.id.iv_pic);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
        }
    }
}
