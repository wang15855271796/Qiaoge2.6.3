package com.puyue.www.qiaoge.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
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
import com.puyue.www.qiaoge.activity.home.FullGiftActivity;
import com.puyue.www.qiaoge.activity.home.SpecialGoodDetailActivity;
import com.puyue.www.qiaoge.adapter.home.SeckillGoodActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;


import java.util.List;

/**
 * Created by ${王涛} on 2020/8/29(满赠)
 */
public class CommonssAdapter extends  RecyclerView.Adapter<CommonssAdapter.BaseViewHolder> {
    private CountDownTimer countDownTimer1;

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
            Log.d("wdadssssssssss....","000");
            activesBean = fullActive.get(position % fullActive.size());
            viewHolder.tv_price.setText(activesBean.getMinMaxPrice());
            Glide.with(mActivity).load(activesBean.getDefaultPic()).into(viewHolder.iv_pic);
            viewHolder.tv_name.setText(activesBean.getProductName());
            if(activesBean.getSendGiftType().equals("赠礼")) {
                viewHolder.iv_given.setVisibility(View.VISIBLE);
                Glide.with(mActivity).load(activesBean.getSendGiftPic()).into(viewHolder.iv_given);
                viewHolder.tv_full_desc.setVisibility(View.VISIBLE);
                viewHolder.tv_full_desc.setText(activesBean.getSendGiftType());
                viewHolder.tv_fit.setVisibility(View.GONE);
                viewHolder.rl_coupon.setVisibility(View.GONE);
                viewHolder.rl_gift.setVisibility(View.VISIBLE);
            }else if(activesBean.getSendGiftType().equals("送券")){
                viewHolder.rl_coupon.setVisibility(View.VISIBLE);
                viewHolder.rl_gift.setVisibility(View.GONE);
                viewHolder.tv_full_desc.setVisibility(View.GONE);
                viewHolder.iv_given.setVisibility(View.GONE);
                viewHolder.tv_fit.setVisibility(View.VISIBLE);
                viewHolder.tv_fit.setText(activesBean.getRoleAmount());
                viewHolder.tv_coupon.setText(activesBean.getSendGiftInfo());
//                Log.d("wdadssssssssss....","111");
            }else {
                viewHolder.tv_full_desc.setVisibility(View.VISIBLE);
                viewHolder.tv_full_desc.setText(activesBean.getSendGiftType());
                viewHolder.tv_fit.setVisibility(View.GONE);
                viewHolder.rl_coupon.setVisibility(View.GONE);
                viewHolder.rl_gift.setVisibility(View.VISIBLE);
                viewHolder.iv_given.setVisibility(View.VISIBLE);
                Glide.with(mActivity).load(activesBean.getSendGiftPic()).into(viewHolder.iv_given);
//                Log.d("wdadssssssssss....","222");
            }
        }catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void cancle() {
        if (countDownTimer1 != null) {
            countDownTimer1.cancel();
        }
    }

    public void start() {
        if(countDownTimer1!=null) {
            countDownTimer1.start();
        }
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        RoundImageView iv_pic;
        TextView tv_coupon;
        TextView tv_price;
        TextView tv_full_desc;
        TextView tv_fit;
        RoundImageView iv_given;
        RelativeLayout rl_gift;
        RelativeLayout rl_coupon;
        TextView tv_name;
        public BaseViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            rl_coupon = (RelativeLayout) view.findViewById(R.id.rl_coupon);
            rl_gift = (RelativeLayout) view.findViewById(R.id.rl_gift);
            tv_fit = (TextView) view.findViewById(R.id.tv_fit);
            tv_full_desc = (TextView) view.findViewById(R.id.tv_full_desc);
            iv_given = (RoundImageView) view.findViewById(R.id.iv_given);
            iv_pic = (RoundImageView) view.findViewById(R.id.iv_pic);
            tv_coupon = (TextView) view.findViewById(R.id.tv_coupon);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
        }
    }
}
