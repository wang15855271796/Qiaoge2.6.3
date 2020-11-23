package com.puyue.www.qiaoge.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.api.mine.coupon.MyCouponsAPI;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.fragment.mine.coupons.MyCouponsUsedAdapter;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserDeductByStateModel;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/11/13(不可使用)
 */
public class CouponsUnUseFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private PtrClassicFrameLayout ptrClassicFrameLayout ;
    private MyCouponsUsedAdapter adapter;
    private int pageNum = 1;
    private LinearLayout data;
    private  LinearLayout noData;
    TextView tv_desc;
    private List<queryUserDeductByStateModel.DataBean.ListBean > lists =new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.fragment_cupons_overdue;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void findViewById(View view) {
        tv_desc = view.findViewById(R.id.tv_desc);
        recyclerView=view.findViewById(R.id.recyclerView);
        data= view .findViewById(R.id.data);
        noData= view.findViewById(R.id.noData);
        ptrClassicFrameLayout=view.findViewById(R.id.ptrClassicFrameLayout);
    }

    @Override
    public void setViewData() {
        pageNum = 1;
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


}
