package com.puyue.www.qiaoge.activity;

import android.animation.IntEvaluator;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xrecyclerview.DensityUtil;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.home.SearchStartActivity;
import com.puyue.www.qiaoge.base.BaseFragment;
import com.puyue.www.qiaoge.view.ObservableScrollView;
import com.puyue.www.qiaoge.view.selectmenu.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${王涛} on 2021/2/19
 */
public class HomeFragment6 extends BaseFragment {
    Unbinder bind;
    private static final float ENDMARGINLEFT = 50;
    private static final float ENDMARGINTOP = 5;
    private static final float STARTMARGINLEFT = 20;
    private static final float STARTMARGINTOP = 60;
    @BindView(R.id.rv_bar)
    RelativeLayout rv_bar;
    @BindView(R.id.rv_search)
    RelativeLayout rv_search;
    @BindView(R.id.iv_search)
    LinearLayout iv_search;
    @BindView(R.id.llFixed)
    LinearLayout llFixed;
    @BindView(R.id.inside_fixed_bar_parent)
    LinearLayout insideFixedBarParent;
    @BindView(R.id.rl_inside_fixed)
    RelativeLayout rl_inside_fixed;
    @BindView(R.id.lv_searchview)
    RecyclerView lv_searchview;
    @BindView(R.id.sv_search)
    MyScrollView sv_search;
    private int evaluatemargin;
    private int evaluatetop;
    private RelativeLayout.LayoutParams layoutParams;
    private int scrollLength;//顶部栏从透明变成不透明滑动的距离
    int topHeight;
    List<String> list = new ArrayList<>();
    int i;
    @Override
    public int setLayoutId() {
        return R.layout.test6;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void findViewById(View view) {
        bind = ButterKnife.bind(this, view);

        for (int j = 0; j < 50; j++) {
            list.add("sss");
        }
        lv_searchview.setAdapter(new searchAdapter(R.layout.item_full, list));
        lv_searchview.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_bar.post(new Runnable() {
            @Override
            public void run() {
                int height = iv_search.getHeight();
                i = DensityUtil.px2dip(height,mActivity);
                int height_rv = rv_bar.getHeight();
                int height_iv = iv_search.getHeight();
                scrollLength = Math.abs(height_iv - height_rv);
                Log.d("dwdwdw...",scrollLength+"");
                //把顶部bar设置为透明
                rv_bar.getBackground().setAlpha(0);

                topHeight = DensityUtil.dip2px(i-50,mActivity);
                Log.d("wdasdssds.......",i+"aa");
                Log.d("wdasdssds.......",height+"bb");
                Log.d("wdasdssds.......",topHeight+"cc");
                sv_search.setScrollChangeListener(new MyScrollView.ScrollChangedListener() {

                    @Override
                    public void onScrollChangedListener(int x, int y, int oldX, int oldY) {
                        int abs_y = Math.abs(y);
                        Log.d("wdasdssds.......",y+"cc");
                        if(abs_y>scrollLength) {
                            rl_inside_fixed.setBackgroundColor(Color.parseColor("#333333"));
                        }else {
                            rl_inside_fixed.setBackgroundColor(Color.parseColor("#999999"));
                        }
                        Log.d("dasdasdwdsds.....",abs_y+"aa");
                        if (y >= topHeight) {
                            if (rl_inside_fixed.getParent() != llFixed) {
                                insideFixedBarParent.removeView(rl_inside_fixed);
                                llFixed.addView(rl_inside_fixed);
                                lv_searchview.setNestedScrollingEnabled(true);
                            }
                        } else {
                            if (rl_inside_fixed.getParent() != insideFixedBarParent) {
                                llFixed.removeView(rl_inside_fixed);
                                insideFixedBarParent.addView(rl_inside_fixed);
                                lv_searchview.setNestedScrollingEnabled(false);
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

                                rv_bar.getBackground().setAlpha(evaluate);
                                //搜索栏左右margin值
                                evaluatemargin = evaluator.evaluate(percent, DensityUtil.dip2px(ENDMARGINLEFT,mActivity), DensityUtil.dip2px(STARTMARGINLEFT,mActivity));

                                //搜索栏顶部margin值
                                evaluatetop = evaluator.evaluate(percent, DensityUtil.dip2px(ENDMARGINTOP,mActivity), DensityUtil.dip2px(STARTMARGINTOP,mActivity));
//                                Log.d("dasdasdwdsds.....",evaluatemargin+"bb");
                                layoutParams = (RelativeLayout.LayoutParams) rv_search.getLayoutParams();
                                layoutParams.setMargins(evaluatemargin, evaluatetop, evaluatemargin, 0);
                                rv_search.requestLayout();
                            }

                        } else {
                            rv_bar.getBackground().setAlpha(255);
                            if(layoutParams!=null){
                                layoutParams.setMargins(DensityUtil.dip2px(ENDMARGINLEFT,mActivity),DensityUtil.dip2px(5,mActivity), DensityUtil.dip2px(ENDMARGINLEFT,mActivity), 0);
                                rv_search.requestLayout();
                            }
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

    private class searchAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

        public searchAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_name,item);
        }
    }
}
