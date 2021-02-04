package com.puyue.www.qiaoge.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.RoundImageView;

import com.puyue.www.qiaoge.activity.home.IssueEditInfoActivity;

import com.puyue.www.qiaoge.api.home.InfoListAPI;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.StringSpecialHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.InfoListModel;
import com.puyue.www.qiaoge.utils.ToastUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2021/1/4
 */
public class MyIssueAdapter extends BaseQuickAdapter<InfoListModel.DataBean.ListBean,BaseViewHolder> {

    public MyIssueAdapter(int layoutResId, @Nullable List<InfoListModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoListModel.DataBean.ListBean item) {
        RoundImageView iv_pic = helper.getView(R.id.iv_pic);
        Glide.with(mContext).load(item.getPictureList().get(0)).into(iv_pic);
        ImageView iv_status = helper.getView(R.id.iv_status);
        TextView tv_deleted = helper.getView(R.id.tv_deleted);
        helper.setText(R.id.tv_title,item.getMsgType());
        helper.setText(R.id.tv_desc,item.getContent());
        helper.setText(R.id.tv_time,item.getCreateTime());
        helper.setText(R.id.tv_num,item.getBrowseNum()+"人看过");
        TextView tv_status = helper.getView(R.id.tv_status);
        TextView tv_edit = helper.getView(R.id.tv_edit);
        if(item.getCheckStatus().equals("1")) {
            //已审核
            tv_status.setText("审核信息已通过");
            iv_status.setBackgroundResource(R.mipmap.icon_dui);
        }else if(item.getCheckStatus().equals("0")) {
            //待审核
            tv_status.setText("审核中…");
            iv_status.setBackgroundResource(R.mipmap.icon_shengnue);
        }else {
            SpannableStringBuilder text = StringSpecialHelper.buildSpanColorStyle("审核未通过查看原因", 5, 4, Color.parseColor("#FF5C00"));
            tv_status.setText(text);
            iv_status.setBackgroundResource(R.mipmap.icon_gantan);
        }
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,IssueEditInfoActivity.class);
                intent.putExtra("msgId",item.getMsgId());
                mContext.startActivity(intent);
            }
        });
        tv_deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteCollection(item);
            }
        });
    }

    private void showDeleteCollection(InfoListModel.DataBean.ListBean item) {
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext, R.style.DialogStyle).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_delete_shop);
        TextView mTvCancel = (TextView) window.findViewById(R.id.tv_cancel);
        TextView mTvConfirm = (TextView) window.findViewById(R.id.tv_deleted);
        mTvCancel.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                alertDialog.dismiss();
            }
        });
        mTvConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                getCityList(item);
                alertDialog.dismiss();

            }
        });
    }

    private void getCityList(InfoListModel.DataBean.ListBean item) {
        InfoListAPI.InfoDeleted(mContext,item.getMsgId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(BaseModel infoListModel) {
                        if (infoListModel.success) {
                            ToastUtil.showSuccessMsg(mContext,infoListModel.message);
                        } else {
                            AppHelper.showMsg(mContext, infoListModel.message);
                        }
                    }
                });
    }

}
