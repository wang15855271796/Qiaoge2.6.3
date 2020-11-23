package com.puyue.www.qiaoge.activity.mine.coupons;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.mine.ViewPagerAdapter;
import com.puyue.www.qiaoge.api.mine.coupon.MyCouponsAPI;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import com.puyue.www.qiaoge.fragment.mine.coupons.CouponsOverdueFragment;
import com.puyue.www.qiaoge.fragment.mine.coupons.CouponsUseFragment;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserDeductByStateModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${daff} on 2018/9/20
 * 我的优惠券
 */
public class MyCouponsActivity extends BaseSwipeActivity {

    private int pageNum = 1;
    private  LinearLayout data;
    private  LinearLayout noData;
    private Toolbar toolbar;
    private List<queryUserDeductByStateModel.DataBean.ListBean> lists =new ArrayList<>();
    TabLayout tabLayout;
    ViewPager viewPager;
    private List<Fragment> list=new ArrayList<>();
    private List<String> stringList =new ArrayList<>();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
            setContentView(R.layout.activity_my_coupons);
    }

    @Override
    public void findViewById() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        data= (LinearLayout) findViewById(R.id.data);
        noData= (LinearLayout) findViewById(R.id.noData);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    public void setViewData() {
        String couponNum = getIntent().getStringExtra("couponsNum");
        pageNum = 1;
        stringList.add("未使用"+"("+couponNum+")");
        stringList.add("已使用");
        stringList.add("已过期/失效");
        //未使用
        list.add(new CouponsNotUseFragment());
        //已使用
        list.add(new CouponsUseFragment());
        //过期
        list.add(new CouponsOverdueFragment());





        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),list,stringList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
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


}
