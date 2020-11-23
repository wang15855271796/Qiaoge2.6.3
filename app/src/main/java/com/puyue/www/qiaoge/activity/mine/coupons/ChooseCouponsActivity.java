package com.puyue.www.qiaoge.activity.mine.coupons;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;


import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ChooseCouponsAdapter;
import com.puyue.www.qiaoge.adapter.mine.ViewPagerAdapter;
import com.puyue.www.qiaoge.api.mine.coupon.userChooseDeductAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.event.ChooseCoupon1Event;
import com.puyue.www.qiaoge.event.ChooseCouponEvent;
import com.puyue.www.qiaoge.fragment.CouponsUnUseFragment;
import com.puyue.www.qiaoge.fragment.mine.coupons.CouponsOverdueFragment;
import com.puyue.www.qiaoge.fragment.mine.coupons.CouponsUseFragment;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

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
    boolean statModel;
    private List<UserChooseDeductModel.DataBean> list = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager viewPager;
    private List<String> stringList =new ArrayList<>();
    private List<Fragment> list_fragment=new ArrayList<>();
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
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        iv_select_all = (ImageView) findViewById(R.id.iv_select_all);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        stringList.add("可使用");
        stringList.add("不可使用");
        //可使用
        list_fragment.add(new CouponsUseFragment());
        //不可使用
        list_fragment.add(new CouponsUnUseFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),list_fragment,stringList);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        iv_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list!=null) {
                    //未选
                    adapter.setStat();
                    EventBus.getDefault().post(new ChooseCoupon1Event());
                    finish();

                }
                statModel = true;
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void setViewData() {
        activityBalanceVOStr = getIntent().getStringExtra("activityBalanceVOStr");
        normalProductBalanceVOStr = getIntent().getStringExtra("normalProductBalanceVOStr");
        giftDetailNo = getIntent().getStringExtra("giftDetailNo");
        statModel = getIntent().getBooleanExtra("statModel",false);
        userChooseDeduct();
        setRecyclerView();
    }


    private void setRecyclerView() {

        adapter = new ChooseCouponsAdapter(R.layout.item_choose_copons, list, new ChooseCouponsAdapter.ImageOnclick() {
            @Override
            public void Onclick(int position, String giftDetailNo) {
                UserChooseDeductModel.DataBean info = list.get(position);
                statModel = false;
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

    UserChooseDeductModel models;
    private void userChooseDeduct() {
        userChooseDeductAPI.requestData(mContext, "0",activityBalanceVOStr, normalProductBalanceVOStr)
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

                                for (int i = 0; i < list.size(); i++) {
                                    if (model.getData().get(i).getGiftDetailNo().equals(giftDetailNo)) {
                                        model.getData().get(i).setFlag(true);
                                        if(statModel) {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_ok);
                                        }else {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_no);
                                        }
                                    } else {
                                        model.getData().get(i).setFlag(false);
                                        if(statModel) {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_ok);
                                        }else {
                                            iv_select_all.setBackgroundResource(R.mipmap.ic_pay_no);
                                        }


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
