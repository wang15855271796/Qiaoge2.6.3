package com.puyue.www.qiaoge.activity;

import android.animation.IntEvaluator;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.xrecyclerview.DensityUtil;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.MyAdapter;
import com.puyue.www.qiaoge.adapter.home.ReductionProductActivity;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.fragment.home.CommonFragment;
import com.puyue.www.qiaoge.fragment.home.HomeFragment;
import com.puyue.www.qiaoge.fragment.home.MustFragment;
import com.puyue.www.qiaoge.fragment.home.NewFragment;
import com.puyue.www.qiaoge.view.selectmenu.MyScrollView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static io.dcloud.common.util.ReflectUtils.getApplicationContext;

/**
 * Created by ${王涛} on 2021/2/20
 */
public class HomeFragment9 extends BaseFragment {
    Unbinder bind;
    @BindView(R.id.rg_new)
    RadioGroup rg_new;
//    @BindView(R.id.ll_parent_top)
//    RelativeLayout ll_parent_top;
    @BindView(R.id.ll_parent)
    RelativeLayout ll_parent;
//    @BindView(R.id.rl_inSide_top)
//    RelativeLayout rl_inSide_top;
    @BindView(R.id.scrollView)
    MyScrollView scrollView;
    @BindView(R.id.fl_container)
    FrameLayout fl_container;

    @BindView(R.id.ll_fixed)
    LinearLayout llFixed;
//    @BindView(R.id.recycler)
//    RecyclerView recycler;
    @BindView(R.id.rl_inSide)
    RelativeLayout rlInsideFixed;
    @BindView(R.id.rl_parent)
    RelativeLayout rl_parent;
    @BindView(R.id.rl_grand)
    RelativeLayout rl_grand;
    @BindView(R.id.ll_scroll)
    LinearLayout ll_scroll;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;
    @BindView(R.id.rl_bar)
    RelativeLayout rl_bar;

    private static final float ENDMARGINLEFT = 50;
    private static final float ENDMARGINTOP = 5;
    private static final float STARTMARGINLEFT = 20;
    private static final float STARTMARGINTOP = 70;

    private int evaluatemargin;
    private int evaluatetop;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    public int setLayoutId() {
        return R.layout.test9;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void findViewById(View view) {
        bind = ButterKnife.bind(this, view);
        initFragment();
        setListener();
        setScroll();

    }

    int topHeight;
    int scrollLength;
    int grand_height;
    MyAdapter normalAdapter;
    List<String> list = new ArrayList<>();
    private void setScroll() {

        for (int i = 0; i < 10; i++) {
            list.add("sss");
        }
        rl_bar.getBackground().setAlpha(0);
        topHeight = DensityUtil.dip2px(138,mActivity);
        normalAdapter = new MyAdapter(R.layout.item_full,list);
//        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
//        recycler.setAdapter(normalAdapter);
        ll_scroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int parent_height = rl_parent.getHeight();
                int bar_height = rl_bar.getHeight();
                scrollLength = Math.abs(parent_height-bar_height);
                grand_height = rl_grand.getHeight()-DensityUtil.dip2px(72,mActivity);
                ll_scroll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        scrollView.setScrollChangeListener(new MyScrollView.ScrollChangedListener() {
            @Override
            public void onScrollChangedListener(int x, int y, int oldX, int oldY) {
                Log.d("wsdads......",scrollLength+"aa");
                Log.d("wsdads......",grand_height+"bb");
                int abs_y = Math.abs(y);
                if (y >=grand_height) {
                    if (rlInsideFixed.getParent() != llFixed) {
                        ll_parent.removeView(rlInsideFixed);
                        llFixed.addView(rlInsideFixed);
                        EventBus.getDefault().post(new ScrollEvent(true));
                    }

                } else {
                    if (rlInsideFixed.getParent() != ll_parent) {
                        llFixed.removeView(rlInsideFixed);
                        ll_parent.addView(rlInsideFixed);
                        EventBus.getDefault().post(new ScrollEvent(false));
                    }
                }


                //滑动距离小于顶部栏从透明到不透明所需的距离
                if ((scrollLength - abs_y) > 0) {
                    //估值器
                    IntEvaluator evaluator = new IntEvaluator();
                    float percent = (float) (scrollLength - abs_y) / scrollLength;

                    if (percent <= 1) {
                        //透明度
                        int evaluate = evaluator.evaluate(percent, 255, 0);

                        rl_bar.getBackground().setAlpha(evaluate);
                        //搜索栏左右margin值
                        evaluatemargin = evaluator.evaluate(percent, DensityUtil.dip2px(ENDMARGINLEFT,mActivity), DensityUtil.dip2px(STARTMARGINLEFT,mActivity));

                        //搜索栏顶部margin值
                        evaluatetop = evaluator.evaluate(percent,  DensityUtil.dip2px(ENDMARGINTOP,mActivity), DensityUtil.dip2px(STARTMARGINTOP,mActivity));
                        layoutParams = (RelativeLayout.LayoutParams) rl_search.getLayoutParams();
                        layoutParams.setMargins(evaluatemargin, evaluatetop, evaluatemargin, 0);
                        rl_search.requestLayout();
                    }

                } else {
                    rl_bar.getBackground().setAlpha(255);
                    if(layoutParams!=null){
                        layoutParams.setMargins(DensityUtil.dip2px(ENDMARGINLEFT,mActivity),DensityUtil.dip2px(5,mActivity), DensityUtil.dip2px(ENDMARGINLEFT,mActivity), 0);
                        rl_search.requestLayout();
                    }
                }
            }
        });
    }

    @Override
    public void setViewData() {

    }

    private void setListener() {
        rg_new.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中框架页面
        rg_new.check(R.id.rb_must_common);
    }

    private List<Fragment> mBaseFragment;
    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(MustFragment.getInstance());
        mBaseFragment.add(NewFragment.getInstance());
        mBaseFragment.add(ReductionProductActivity.getInstance());
        mBaseFragment.add(CommonFragment.getInstance());
    }

    @Override
    public void setClickEvent() {

    }

    int position;
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_new:
                    position = 1;
//                    rb_info.setTextColor(Color.parseColor("#333333"));
//                    rb_common.setTextColor(Color.parseColor("#333333"));
//                    rb_must_common.setTextColor(Color.parseColor("#333333"));
//                    rb_new.setTextColor(Color.parseColor("#17BD60"));
//                    tv_title1.setTextColor(Color.parseColor("#ffffff"));
//                    tv_title1.setBackgroundResource(R.drawable.shape_greenss);
//
//                    tv_title2.setTextColor(Color.parseColor("#999999"));
//                    tv_title2.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title3.setTextColor(Color.parseColor("#999999"));
//                    tv_title3.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title4.setTextColor(Color.parseColor("#999999"));
//                    tv_title4.setBackgroundResource(R.drawable.shape_white);


                    break;

                case R.id.rb_must_common:
                    position = 0;
//                    rb_info.setTextColor(Color.parseColor("#333333"));
//                    rb_common.setTextColor(Color.parseColor("#333333"));
//                    rb_must_common.setTextColor(Color.parseColor("#333333"));
//                    rb_new.setTextColor(Color.parseColor("#333333"));
//
//                    rb_must_common.setTextColor(Color.parseColor("#17BD60"));
//                    tv_title2.setTextColor(Color.parseColor("#ffffff"));
//                    tv_title2.setBackgroundResource(R.drawable.shape_greenss);
//
//                    tv_title1.setTextColor(Color.parseColor("#999999"));
//                    tv_title1.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title3.setTextColor(Color.parseColor("#999999"));
//                    tv_title3.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title4.setTextColor(Color.parseColor("#999999"));
//                    tv_title4.setBackgroundResource(R.drawable.shape_white);

                    break;

                case R.id.rb_info:
                    position = 2;
//                    rb_info.setTextColor(Color.parseColor("#17BD60"));
//                    rb_common.setTextColor(Color.parseColor("#333333"));
//                    rb_must_common.setTextColor(Color.parseColor("#333333"));
//                    rb_new.setTextColor(Color.parseColor("#333333"));
//                    tv_title2.setTextColor(Color.parseColor("#999999"));
//                    tv_title2.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title1.setTextColor(Color.parseColor("#999999"));
//                    tv_title1.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title3.setTextColor(Color.parseColor("#ffffff"));
//                    tv_title3.setBackgroundResource(R.drawable.shape_greenss);
//
//                    tv_title4.setTextColor(Color.parseColor("#999999"));
//                    tv_title4.setBackgroundResource(R.drawable.shape_white);

                    break;

                case R.id.rb_common:
                    position = 3;
//                    rb_info.setTextColor(Color.parseColor("#333333"));
//                    rb_common.setTextColor(Color.parseColor("#17BD60"));
//                    rb_must_common.setTextColor(Color.parseColor("#333333"));
//                    rb_new.setTextColor(Color.parseColor("#333333"));
//                    rb_info.setTextColor(Color.parseColor("#333333"));
//
//                    tv_title2.setTextColor(Color.parseColor("#999999"));
//                    tv_title2.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title1.setTextColor(Color.parseColor("#999999"));
//                    tv_title1.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title3.setTextColor(Color.parseColor("#999999"));
//                    tv_title3.setBackgroundResource(R.drawable.shape_white);
//
//                    tv_title4.setTextColor(Color.parseColor("#ffffff"));
//                    tv_title4.setBackgroundResource(R.drawable.shape_greenss);

                    break;
            }
            //根据位置得到对应的Fragment
            Fragment to = getFragment();
            //替换到Fragment
            switchFrament(mContent,to);
        }
    }

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
    private Fragment getFragment() {
        Fragment fragment = mBaseFragment.get(position);
        return fragment;
    }
}
