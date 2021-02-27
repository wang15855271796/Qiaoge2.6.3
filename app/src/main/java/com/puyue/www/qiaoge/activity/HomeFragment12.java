package com.puyue.www.qiaoge.activity;

import android.animation.IntEvaluator;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xrecyclerview.DensityUtil;
import com.puyue.www.qiaoge.FixBounceV26Behavior;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.RoundImageView;
import com.puyue.www.qiaoge.adapter.CommonCouponAdapter;
import com.puyue.www.qiaoge.adapter.CommonsAdapter;
import com.puyue.www.qiaoge.adapter.CommonssAdapter;
import com.puyue.www.qiaoge.adapter.CommonsssAdapter;
import com.puyue.www.qiaoge.adapter.CouponListAdapter;
import com.puyue.www.qiaoge.adapter.FullAdapter;
import com.puyue.www.qiaoge.adapter.HotAdapter;
import com.puyue.www.qiaoge.adapter.MyAdapter;
import com.puyue.www.qiaoge.adapter.Skill2Adapter;
import com.puyue.www.qiaoge.adapter.Skill3Adapter;
import com.puyue.www.qiaoge.adapter.Skill5Adapter;
import com.puyue.www.qiaoge.adapter.Team3Adapter;
import com.puyue.www.qiaoge.adapter.TeamAdapter;
import com.puyue.www.qiaoge.adapter.home.CommonAdapter;
import com.puyue.www.qiaoge.adapter.home.ReductionProductActivity;
import com.puyue.www.qiaoge.api.home.IndexInfoModel;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.dialog.ChooseHomeDialog;
import com.puyue.www.qiaoge.dialog.CouponDialog;
import com.puyue.www.qiaoge.dialog.CouponListDialog;
import com.puyue.www.qiaoge.dialog.HomeActivityDialog;
import com.puyue.www.qiaoge.dialog.PrivacyDialog;
import com.puyue.www.qiaoge.dialog.TurnTableDialog;
import com.puyue.www.qiaoge.event.CouponListModel;
import com.puyue.www.qiaoge.event.TurnModel;
import com.puyue.www.qiaoge.fragment.home.CommonFragment;
import com.puyue.www.qiaoge.fragment.home.IndexRecommendAdapter;
import com.puyue.www.qiaoge.fragment.home.InfoFragment;
import com.puyue.www.qiaoge.fragment.home.MustFragment;
import com.puyue.www.qiaoge.fragment.home.NewAdapter;
import com.puyue.www.qiaoge.fragment.home.NewFragment;
import com.puyue.www.qiaoge.fragment.home.RvIconAdapter;
import com.puyue.www.qiaoge.fragment.home.SkillAdapter;
import com.puyue.www.qiaoge.fragment.home.VerticalBannerAdapter;
import com.puyue.www.qiaoge.model.OrderModel;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.model.mine.UpdateModel;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;
import com.puyue.www.qiaoge.view.AutoScrollRecyclerView;
import com.puyue.www.qiaoge.view.HIndicators;
import com.puyue.www.qiaoge.view.SnapUpCountDownTimerViewss;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.taobao.library.VerticalBannerView;
import com.wang.avi.AVLoadingIndicatorView;
import com.yuruiyin.appbarlayoutbehavior.AppBarLayoutBehavior;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${王涛} on 2021/2/22
 */
public class HomeFragment12 extends BaseFragment {

    Unbinder bind;
    @BindView(R.id.rg_new)
    RadioGroup rg_new;
    @BindView(R.id.rb_new)
    RadioButton rb_new;
    @BindView(R.id.rb_must_common)
    RadioButton rb_must_common;
    @BindView(R.id.rb_reduce)
    RadioButton rb_reduce;
    @BindView(R.id.rb_common)
    RadioButton rb_common;
    @BindView(R.id.tv_title1)
    TextView tv_title1;
    @BindView(R.id.tv_title2)
    TextView tv_title2;
    @BindView(R.id.tv_title3)
    TextView tv_title3;
    @BindView(R.id.tv_title4)
    TextView tv_title4;
    @BindView(R.id.rl_bar)
    RelativeLayout rl_bar;
    @BindView(R.id.ll_parent)
    RelativeLayout ll_parent;
    @BindView(R.id.ll_scroll)
    LinearLayout ll_scroll;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rl_grand)
    RelativeLayout rl_grand;
    @BindView(R.id.rl_inSide)
    RelativeLayout rl_inSide;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.ll_parent_top)
    RelativeLayout ll_parent_top;
    List<String> list = new ArrayList<>();
    @Override
    public int setLayoutId() {
        return R.layout.test11;
    }

    @Override
    public void initViews(View view) {

    }

    int scrollLength;
    int scrollLength1;
    int topHeight;
    int topHeight1;
    @Override
    public void findViewById(View view) {
        bind = ButterKnife.bind(this,view);
        initFragment();
        setListener();
        for (int i = 0; i < 50; i++) {
            list.add("sss");
        }
        recycler.setAdapter(new MyAdapter(R.layout.item_full,list));
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        recycler.setNestedScrollingEnabled(false);
        ll_scroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int bar_height = rl_bar.getHeight();
                int scroll_height = ll_scroll.getHeight();
                scrollLength = Math.abs(scroll_height - bar_height);
                topHeight = DensityUtil.dip2px(scrollLength, mActivity);
                int grand_height = rl_grand.getHeight();
                scrollLength1 = Math.abs(grand_height - bar_height);
                topHeight1 = DensityUtil.dip2px(scrollLength1, mActivity);
                ll_scroll.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int y) {
                        int abs_y = Math.abs(y);
                        int appbarScrollLength = Math.abs(DensityUtil.dip2px(y, mActivity));
                        if (appbarScrollLength >= topHeight1) {
                            ll_parent_top.setVisibility(View.VISIBLE);
                            Log.d("wdadas...","11");
                        } else {
                            ll_parent_top.setVisibility(View.GONE);
                            Log.d("wdadas...","22");
                        }
                    }
                });
            }
        });



    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }

    private Fragment getFragment() {
        Fragment fragment = mBaseFragment.get(position);
        return fragment;
    }
    private void setListener() {
        rg_new.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中框架页面
        rg_new.check(R.id.rb_must_common);
    }

    int position;
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_new:
                    position = 1;
                    rb_reduce.setTextColor(Color.parseColor("#333333"));
                    rb_common.setTextColor(Color.parseColor("#333333"));
                    rb_must_common.setTextColor(Color.parseColor("#333333"));
                    rb_new.setTextColor(Color.parseColor("#17BD60"));
                    tv_title1.setTextColor(Color.parseColor("#ffffff"));
                    tv_title1.setBackgroundResource(R.drawable.shape_greenss);

                    tv_title2.setTextColor(Color.parseColor("#999999"));
                    tv_title2.setBackgroundResource(R.drawable.shape_white);

                    tv_title3.setTextColor(Color.parseColor("#999999"));
                    tv_title3.setBackgroundResource(R.drawable.shape_white);

                    tv_title4.setTextColor(Color.parseColor("#999999"));
                    tv_title4.setBackgroundResource(R.drawable.shape_white);


                    break;

                case R.id.rb_must_common:
                    position = 0;
                    rb_reduce.setTextColor(Color.parseColor("#333333"));
                    rb_common.setTextColor(Color.parseColor("#333333"));
                    rb_new.setTextColor(Color.parseColor("#333333"));
                    rb_must_common.setTextColor(Color.parseColor("#17BD60"));

                    tv_title2.setTextColor(Color.parseColor("#ffffff"));
                    tv_title2.setBackgroundResource(R.drawable.shape_greenss);

                    tv_title1.setTextColor(Color.parseColor("#999999"));
                    tv_title1.setBackgroundResource(R.drawable.shape_white);

                    tv_title3.setTextColor(Color.parseColor("#999999"));
                    tv_title3.setBackgroundResource(R.drawable.shape_white);

                    tv_title4.setTextColor(Color.parseColor("#999999"));
                    tv_title4.setBackgroundResource(R.drawable.shape_white);

                    break;

                case R.id.rb_reduce:
                    position = 2;
                    rb_reduce.setTextColor(Color.parseColor("#17BD60"));
                    rb_common.setTextColor(Color.parseColor("#333333"));
                    rb_must_common.setTextColor(Color.parseColor("#333333"));
                    rb_new.setTextColor(Color.parseColor("#333333"));
                    tv_title2.setTextColor(Color.parseColor("#999999"));
                    tv_title2.setBackgroundResource(R.drawable.shape_white);

                    tv_title1.setTextColor(Color.parseColor("#999999"));
                    tv_title1.setBackgroundResource(R.drawable.shape_white);

                    tv_title3.setTextColor(Color.parseColor("#ffffff"));
                    tv_title3.setBackgroundResource(R.drawable.shape_greenss);

                    tv_title4.setTextColor(Color.parseColor("#999999"));
                    tv_title4.setBackgroundResource(R.drawable.shape_white);

                    break;

                case R.id.rb_common:
                    position = 3;
                    rb_reduce.setTextColor(Color.parseColor("#333333"));
                    rb_common.setTextColor(Color.parseColor("#17BD60"));
                    rb_must_common.setTextColor(Color.parseColor("#333333"));
                    rb_new.setTextColor(Color.parseColor("#333333"));
                    rb_reduce.setTextColor(Color.parseColor("#333333"));

                    tv_title2.setTextColor(Color.parseColor("#999999"));
                    tv_title2.setBackgroundResource(R.drawable.shape_white);

                    tv_title1.setTextColor(Color.parseColor("#999999"));
                    tv_title1.setBackgroundResource(R.drawable.shape_white);

                    tv_title3.setTextColor(Color.parseColor("#999999"));
                    tv_title3.setBackgroundResource(R.drawable.shape_white);

                    tv_title4.setTextColor(Color.parseColor("#ffffff"));
                    tv_title4.setBackgroundResource(R.drawable.shape_greenss);

                    break;
            }
            //根据位置得到对应的Fragment
            Fragment to = getFragment();
            //替换到Fragment
            switchFrament(mContent,to);
        }
    }
    //
    private Fragment mContent;
    private void switchFrament(Fragment from,Fragment to) {
        if(from != to){ //才切换
            mContent = to;
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction(); //开启事务
            //判断to有没有被添加
            if(!to.isAdded()){//to没有被添加
                //1.from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //2.添加to
                if(to != null){
                    ft.add(R.id.fl_container,to).commit();
                }
            }else{ //to已经被添加
                //1.from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //2.显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }
    }
    //
    private List<Fragment> mBaseFragment;
    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(MustFragment.getInstance());
        mBaseFragment.add(NewFragment.getInstance());
        mBaseFragment.add(ReductionProductActivity.getInstance());
        mBaseFragment.add(CommonFragment.getInstance());
    }

//
//
//    @BindView(R.id.appbar)
//    AppBarLayout appbar;
//    @BindView(R.id.ll_scroll)
//    LinearLayout ll_scroll;
//    @BindView(R.id.rl_grand)
//    RelativeLayout rl_grand;
//    @BindView(R.id.ll_fixed)
//    LinearLayout ll_fixed;
//    @BindView(R.id.ll_parent)
//    RelativeLayout ll_parent;
//    @BindView(R.id.rl_inSide)
//    RelativeLayout rl_inSide;
//    @BindView(R.id.rl_bar)
//    RelativeLayout rl_bar;
//    @BindView(R.id.tv_city)
//    TextView tv_city;
//    @BindView(R.id.tv_search)
//    TextView tv_search;
//    @BindView(R.id.rl_search)
//    RelativeLayout rl_search;
//    @BindView(R.id.iv_location)
//    ImageView iv_location;
//    List<String> list = new ArrayList<>();
//    private static final float ENDMARGINLEFT = 50;
//    private static final float ENDMARGINTOP = 5;
//    private static final float STARTMARGINLEFT = 20;
//    private static final float STARTMARGINTOP = 72;
//    private int evaluatemargin;
//    private int evaluatetop;
//    private RelativeLayout.LayoutParams layoutParams;
//    int scrollLength;
//    int topHeight;
//    int scrollLength1;
//    int topHeight1;
//
//    @Override
//    public int setLayoutId() {
//        return R.layout.test11;
//    }
//
//    @Override
//    public void initViews(View view) {
//
//    }
//
//    @Override
//    public void findViewById(View view) {
//        bind = ButterKnife.bind(this,view);
//        initFragment();
//        setListener();
//
//        ll_scroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int bar_height = rl_bar.getHeight();
//                int scroll_height = ll_scroll.getHeight();
//                scrollLength = Math.abs(scroll_height - bar_height);
//                topHeight = DensityUtil.dip2px(scrollLength, mActivity);
//
//                int grand_height = rl_grand.getHeight();
//                scrollLength1 = Math.abs(grand_height - bar_height);
//                topHeight1 = DensityUtil.dip2px(scrollLength1, mActivity);
//                ll_scroll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
//
//
//        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int y) {
//                int abs_y = Math.abs(y);
//                int appbarScrollLength = Math.abs(DensityUtil.dip2px(y, mActivity));
//                if (appbarScrollLength >= topHeight1) {
//                    if (rl_inSide.getParent() != ll_fixed) {
//                        ll_parent.removeView(rl_inSide);
//                        ll_fixed.addView(rl_inSide);
//                    }
//                } else {
//                    if (rl_inSide.getParent() != ll_parent) {
//                        ll_fixed.removeView(rl_inSide);
//                        ll_parent.addView(rl_inSide);
//                    }
//                }
//
//                //滑动距离小于顶部栏从透明到不透明所需的距离
//                if ((scrollLength - abs_y) > 0) {
//                    //估值器
//                    IntEvaluator evaluator = new IntEvaluator();
//                    float percent = (float) (scrollLength - abs_y) / scrollLength;
//
//                    if (percent <= 1) {
//                        //透明度
//                        int evaluate = evaluator.evaluate(percent, 255, 0);
//                        rl_bar.getBackground().setAlpha(evaluate);
//                        //搜索栏左右margin值
//                        evaluatemargin = evaluator.evaluate(percent, DensityUtil.dip2px(ENDMARGINLEFT,mActivity), DensityUtil.dip2px(STARTMARGINLEFT,mActivity));
//                        //搜索栏顶部margin值
//                        evaluatetop = evaluator.evaluate(percent,  DensityUtil.dip2px(ENDMARGINTOP,mActivity), DensityUtil.dip2px(STARTMARGINTOP,mActivity));
//
//                        layoutParams = (RelativeLayout.LayoutParams) rl_search.getLayoutParams();
//                        layoutParams.setMargins(evaluatemargin, evaluatetop, evaluatemargin, 0);
//                        if(evaluatetop<100) {
//                            layoutParams.setMargins(evaluatemargin, 80, evaluatemargin, 0);
//                            rl_search.requestLayout();
//                        }else {
//                            layoutParams.setMargins(evaluatemargin, evaluatetop, evaluatemargin, 0);
//                            rl_search.requestLayout();
//                        }
//                        iv_location.setImageResource(R.mipmap.icon_address2);
//                        tv_city.setAlpha(percent);
//                        rl_search.requestLayout();
//                    }
//                } else {
//                    rl_bar.getBackground().setAlpha(255);
//                    if(layoutParams!=null){
//                        layoutParams.setMargins(DensityUtil.dip2px(ENDMARGINLEFT,mActivity),80, DensityUtil.dip2px(ENDMARGINLEFT,mActivity), 0);
//                        rl_search.requestLayout();
//                        iv_location.setImageResource(R.mipmap.icon_address1);
//                        tv_city.setAlpha(0);
//                    }
//
//                }
//            }
//        });
//
//    }
//
//    @Override
//    public void setViewData() {
//
//    }
//
//    @Override
//    public void setClickEvent() {
//
//    }
//

//
}
