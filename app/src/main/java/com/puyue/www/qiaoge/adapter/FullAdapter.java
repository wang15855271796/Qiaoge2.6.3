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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.RoundImageView;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by ${王涛} on 2020/9/24
 */
public class FullAdapter extends BaseQuickAdapter<CouponModel.DataBean.ActivesBean,BaseViewHolder> {
    private CountDownTimer countDownTimer1;
    List<CouponModel.DataBean.ActivesBean> data;
    public FullAdapter(int layoutResId, @Nullable List<CouponModel.DataBean.ActivesBean> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponModel.DataBean.ActivesBean item) {
        ImageView iv_pic = helper.getView(R.id.iv_pic);
        Glide.with(mContext).load(data.get(0).getDefaultPic()).into(iv_pic);
        TextView tv_price = helper.getView(R.id.tv_price);
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_price.setText(data.get(0).getPrice());
        tv_name.setText(data.get(0).getProductName());
        TextView tv_fit = helper.getView(R.id.tv_fit);
        TextView tv_coupon = helper.getView(R.id.tv_coupon);
        TextView tv_full_desc = helper.getView(R.id.tv_full_desc);
        ImageView iv_given = helper.getView(R.id.iv_given);
        tv_coupon.setVisibility(View.VISIBLE);
        tv_coupon.setText(data.get(0).getSendGiftInfo());
        if(item.getSendGiftType().equals("赠礼")) {
            iv_given.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(data.get(0).getDefaultPic()).into(iv_given);
            tv_full_desc.setVisibility(View.VISIBLE);
            tv_full_desc.setText(data.get(0).getSendGiftType());
            tv_fit.setVisibility(View.GONE);
            tv_coupon.setVisibility(View.GONE);
            Log.d("wdassasswww....","000");
        }else {
            iv_given.setVisibility(View.GONE);
            tv_full_desc.setVisibility(View.GONE);
            tv_fit.setVisibility(View.VISIBLE);
            tv_coupon.setVisibility(View.VISIBLE);
            tv_coupon.setText(data.get(0).getSendGiftInfo());
            Log.d("wdassasswww....","111");
        }
        Log.d("wdassasswww....",item.getSendGiftType());
        if(countDownTimer1 == null) {
            countDownTimer1 = new CountDownTimer(1000,1000) {
                int i = 0;
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    try {
                        Glide.with(mContext).load(data.get(i).getDefaultPic()).into(iv_pic);
                        Log.d("wdassasswww....",data.get(i).getDefaultPic()+"555");
                        Log.d("wdassasswww....",i+"666");
                        tv_price.setText(data.get(i).getPrice());
                        tv_name.setText(data.get(i).getProductName());

                        if(item.getSendGiftType().equals("赠礼")) {
                            iv_given.setVisibility(View.VISIBLE);
                            Glide.with(mContext).load(data.get(i).getDefaultPic()).into(iv_given);
                            tv_full_desc.setVisibility(View.VISIBLE);
                            tv_full_desc.setText(data.get(i).getSendGiftType());
                            tv_fit.setVisibility(View.GONE);
                            tv_coupon.setVisibility(View.GONE);
                            Log.d("wdassasswww....","222");
                        }else {
                            tv_full_desc.setVisibility(View.GONE);
                            iv_given.setVisibility(View.GONE);
                            tv_fit.setVisibility(View.VISIBLE);
                            tv_coupon.setVisibility(View.VISIBLE);
                            tv_coupon.setText(data.get(i).getSendGiftInfo());
                            Log.d("wdassasswww....","333");
                        }



                        i++;
                        if(i==data.size()) {
                            i = 0;
                        }
                    }catch (Exception e) {

                    }
                    start();
                }
            }.start();
        }
    }

    public void cancle() {
        if(countDownTimer1!=null) {
            countDownTimer1.cancel();
        }

//        if(countDownTimer2!=null) {
//            countDownTimer2.cancel();
//        }
//        if(countDownTimer3!=null) {
//            countDownTimer3.cancel();
//        }
//        if(countDownTimer4!=null) {
//            countDownTimer4.cancel();
//        }
//        if(countDownTimer5!=null) {
//            countDownTimer5.cancel();
//        }
    }


    public void start() {
        if(countDownTimer1!=null) {
            countDownTimer1.start();
        }

//        if(countDownTimer2!=null) {
//            countDownTimer2.start();
//        }
//        if(countDownTimer3!=null) {
//            countDownTimer3.start();
//        }
//        if(countDownTimer4!=null) {
//            countDownTimer4.start();
//        }
//        if(countDownTimer5!=null) {
//            countDownTimer5.start();
//        }
    }


//    List<CouponModel.DataBean.ActivesBean> fullActive;
//    Context mActivity;
//    CouponModel.DataBean.ActivesBean activesBean;
//    public FullAdapter(FragmentActivity mActivity, List<CouponModel.DataBean.ActivesBean> fullActive) {
//        this.mActivity = mActivity;
//        this.fullActive = fullActive;
//    }
//
//
//    @NonNull
//    @Override
//    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_full_lists, viewGroup, false);
//        BaseViewHolder holder = new BaseViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
//        try {
//            activesBean = fullActive.get(position % fullActive.size());
//            viewHolder.tv_name.setText(activesBean.getProductName());
//            viewHolder.tv_price.setText(activesBean.getPrice());
//            Glide.with(mActivity).load(activesBean.getDefaultPic()).into(viewHolder.iv_pic);
//            Glide.with(mActivity).load(activesBean.getDefaultPic()).into(viewHolder.iv_given);
//            viewHolder.tv_full_desc.setText(activesBean.getSendGiftType());
//        }catch (Exception e) {
//
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return Integer.MAX_VALUE;
//    }
//
//    public class BaseViewHolder extends RecyclerView.ViewHolder {
//        RoundImageView iv_pic;
//        TextView tv_name;
//        TextView tv_price;
//        TextView tv_full_desc;
//        RoundImageView iv_given;
//        public BaseViewHolder(View view) {
//            super(view);
//            tv_full_desc = (TextView) view.findViewById(R.id.tv_full_desc);
//            iv_given = (RoundImageView) view.findViewById(R.id.iv_given);
//            iv_pic = (RoundImageView) view.findViewById(R.id.iv_pic);
//            tv_name = (TextView) view.findViewById(R.id.tv_name);
//            tv_price = (TextView) view.findViewById(R.id.tv_price);
//        }
//    }
}
