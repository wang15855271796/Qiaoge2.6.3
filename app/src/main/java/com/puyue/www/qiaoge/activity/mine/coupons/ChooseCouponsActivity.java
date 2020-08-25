package com.puyue.www.qiaoge.activity.mine.coupons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ChooseCouponsAdapter;
import com.puyue.www.qiaoge.api.cart.CartBalanceAPI;
import com.puyue.www.qiaoge.api.home.GetCommentListByPageAPI;
import com.puyue.www.qiaoge.api.mine.coupon.userChooseDeductAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.ChooseCoupon1Event;
import com.puyue.www.qiaoge.event.ChooseCouponEvent;
import com.puyue.www.qiaoge.helper.ActivityResultHelper;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.CartBalanceModel;
import com.puyue.www.qiaoge.model.home.GetCommentListByPageModel;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author daff
 * @date 2018/9/23.
 * 备注  选择优惠劵
 */
public class ChooseCouponsActivity extends BaseSwipeActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private String activityBalanceVOStr;
    private String normalProductBalanceVOStr;
    private String giftDetailNo="";
    private ChooseCouponsAdapter adapter;
    ImageView iv_select_all;
    private List<UserChooseDeductModel.DataBean> list = new ArrayList<>();


    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.acticity_choose_coupons);
    }

    @Override
    public void findViewById() {
        iv_select_all = (ImageView) findViewById(R.id.iv_select_all);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        iv_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list!=null) {
                    //未选
                    adapter.setStat();
                    EventBus.getDefault().post(new ChooseCoupon1Event());
                    finish();
                }
            }
        });
    }

    @Override
    public void setViewData() {
        activityBalanceVOStr = getIntent().getStringExtra("activityBalanceVOStr");
        normalProductBalanceVOStr = getIntent().getStringExtra("normalProductBalanceVOStr");
        giftDetailNo = getIntent().getStringExtra("giftDetailNo");
        userChooseDeduct();
        setRecyclerView();
    }


    private void setRecyclerView() {

        adapter = new ChooseCouponsAdapter(R.layout.item_choose_copons, list, new ChooseCouponsAdapter.ImageOnclick() {
            @Override
            public void Onclick(int position, String giftDetailNo) {
                UserChooseDeductModel.DataBean info = list.get(position);
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).setFlag(!list.get(i).isFlag());
                        if (list.get(i).isFlag()) {
                            EventBus.getDefault().post(new ChooseCouponEvent(info.getGiftDetailNo()));
                            finish();

                        } else {
                            finish();
                        }
                    } else {
                        list.get(i).setFlag(false);
                    }
                }

                adapter.notifyDataSetChanged();

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void setClickEvent() {
        toolbar.setNavigationOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                finish();
            }
        });
    }


    private void userChooseDeduct() {
        userChooseDeductAPI.requestData(mContext, activityBalanceVOStr, normalProductBalanceVOStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserChooseDeductModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserChooseDeductModel model) {
                        if (model.success) {
                            if (model.getData().size()> 0) {
                                list.addAll(model.getData());
                                for (int i = 0; i < list.size(); i++) {
                                    if (model.getData().get(i).getGiftDetailNo().equals(giftDetailNo)) {
                                        model.getData().get(i).setFlag(true);

                                    } else {
                                        model.getData().get(i).setFlag(false);

                                    }

                                }
                            }

                            adapter.notifyDataSetChanged();

                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }

                    }
                });
    }


}
