package com.puyue.www.qiaoge.fragment.mine.coupons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.coupons.UseOrNotUseActivity;
import com.puyue.www.qiaoge.adapter.coupon.MyCouponsAdapter;
import com.puyue.www.qiaoge.api.mine.coupon.MyCouponsAPI;
import com.puyue.www.qiaoge.api.mine.coupon.userChooseDeductAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.event.ChooseCoupon1Event;
import com.puyue.www.qiaoge.fragment.mine.order.AllOrderFragment;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserDeductByStateModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static cn.com.chinatelecom.account.api.CtAuth.mContext;

/**
 * Created by ${daff} on 2018/9/20
 * 优惠券可使用
 */
public class CouponsUseFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private PtrClassicFrameLayout ptrClassicFrameLayout ;
    private MyCouponsUsedAdapter adapter;
    private int pageNum = 1;
    private LinearLayout data;
    private  LinearLayout noData;
    TextView tv_desc;
    ImageView iv_select_all;
    private List<queryUserDeductByStateModel.DataBean.ListBean > lists =new ArrayList<>();
    private List<UserChooseDeductModel.DataBean> list = new ArrayList<>();
    public static CouponsUseFragment newInstance(String giftDetailNo, String activityBalanceVOStr, String normalProductBalanceVOStr) {
        Bundle args = new Bundle();
        args.putString("giftDetailNo", giftDetailNo);
        args.putString("activityBalanceVOStr", activityBalanceVOStr);
        args.putString("normalProductBalanceVOStr", normalProductBalanceVOStr);
        CouponsUseFragment fragment = new CouponsUseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    String giftDetailNo;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        giftDetailNo = getArguments().getString("giftDetailNo");
        activityBalanceVOStr = getArguments().getString("activityBalanceVOStr");
        normalProductBalanceVOStr = getArguments().getString("normalProductBalanceVOStr");
    }
    @Override
    public int setLayoutId() {
        return R.layout.fragment_cupons_overdue;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void findViewById(View view) {
        iv_select_all = view.findViewById(R.id.iv_select_all);
        tv_desc = view.findViewById(R.id.tv_desc);
        recyclerView=view.findViewById(R.id.recyclerView);
        data= view .findViewById(R.id.data);
        noData= view.findViewById(R.id.noData);
        ptrClassicFrameLayout=view.findViewById(R.id.ptrClassicFrameLayout);

        iv_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list!=null) {
                    //未选
//                    adapter.setStat();
                    EventBus.getDefault().post(new ChooseCoupon1Event());
                    mActivity.finish();

                }
                statModel = true;
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setViewData() {
        pageNum = 1;
        userChooseDeduct();
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;

            }
        });

        adapter = new MyCouponsUsedAdapter(R.layout.item_my_coupons,lists,getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(-1)) {
                    ptrClassicFrameLayout.setEnabled(false);
                } else {
                    ptrClassicFrameLayout.setEnabled(true);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNum++;
            }
        }, recyclerView);
    }

    @Override
    public void setClickEvent() {

    }
    UserChooseDeductModel models;
    private String activityBalanceVOStr;
    private String normalProductBalanceVOStr;
    boolean statModel;
    private void userChooseDeduct() {
        userChooseDeductAPI.requestData(mContext, "0",activityBalanceVOStr, normalProductBalanceVOStr,"0")
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
                            models = model;

                            if (model.getData().size()> 0) {

                                list.addAll(model.getData());
                                adapter.notifyDataSetChanged();
                                for (int i = 0; i < list.size(); i++) {
                                    if (model.getData().get(i).getGiftDetailNo().equals(giftDetailNo)) {
                                        model.getData().get(i).setFlags(true);
                                        if(statModel) {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_ok);
                                        }else {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_no);
                                        }
                                    } else {
                                        model.getData().get(i).setFlags(false);
                                        if(statModel) {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_ok);
                                        }else {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_no);
                                        }
                                    }
                                }
                            }
                        } else {
                            AppHelper.showMsg(mContext, model.message);
                        }
                    }
                });
    }

}
