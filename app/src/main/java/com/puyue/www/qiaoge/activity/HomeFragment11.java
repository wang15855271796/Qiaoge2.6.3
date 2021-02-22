package com.puyue.www.qiaoge.activity;

import android.animation.IntEvaluator;
import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xrecyclerview.DensityUtil;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.RoundImageView;
import com.puyue.www.qiaoge.adapter.CommonCouponAdapter;
import com.puyue.www.qiaoge.adapter.CommonsAdapter;
import com.puyue.www.qiaoge.adapter.CommonssAdapter;
import com.puyue.www.qiaoge.adapter.CommonsssAdapter;
import com.puyue.www.qiaoge.adapter.CouponListAdapter;
import com.puyue.www.qiaoge.adapter.FullAdapter;
import com.puyue.www.qiaoge.adapter.HotAdapter;
import com.puyue.www.qiaoge.adapter.Skill2Adapter;
import com.puyue.www.qiaoge.adapter.Skill3Adapter;
import com.puyue.www.qiaoge.adapter.Skill5Adapter;
import com.puyue.www.qiaoge.adapter.Team3Adapter;
import com.puyue.www.qiaoge.adapter.TeamAdapter;
import com.puyue.www.qiaoge.adapter.home.CommonAdapter;
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
public class HomeFragment11 extends BaseFragment {

    Unbinder bind;
    @BindView(R.id.rv_auto_view1)
    AutoScrollRecyclerView rv_auto_view1;
    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;
    @BindView(R.id.rv_coupon1)
    RecyclerView rv_coupon1;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.rl_full)
    RelativeLayout rl_full;
    @BindView(R.id.rl_team)
    RelativeLayout rl_team;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.indicator)
    HIndicators indicator;
    @BindView(R.id.rv_icon)
    RecyclerView rv_icon;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ll_driver)
    LinearLayout ll_driver;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_message)
    RelativeLayout rl_message;
    @BindView(R.id.homeMessage)
    ImageView homeMessage;
    @BindView(R.id.rl_more)
    RelativeLayout rl_more;
    @BindView(R.id.rl_more3)
    RelativeLayout rl_more3;
    @BindView(R.id.rl_more6)
    RelativeLayout rl_more6;
    @BindView(R.id.rl_more4)
    RelativeLayout rl_more4;
    @BindView(R.id.rb_new)
    RadioButton rb_new;
    @BindView(R.id.rb_must_common)
    RadioButton rb_must_common;
    @BindView(R.id.rb_reduce)
    RadioButton rb_reduce;
    @BindView(R.id.rb_common)
    RadioButton rb_common;
    @BindView(R.id.ll_line)
    LinearLayout ll_line;
    @BindView(R.id.rv_team)
    AutoScrollRecyclerView rv_team;
    @BindView(R.id.tv_team_title)
    TextView tv_team_title;
    @BindView(R.id.rv_given)
    AutoScrollRecyclerView rv_given;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.v4)
    View v4;
    @BindView(R.id.tv_title1)
    TextView tv_title1;
    @BindView(R.id.tv_title2)
    TextView tv_title2;
    @BindView(R.id.tv_title3)
    TextView tv_title3;
    @BindView(R.id.tv_title4)
    TextView tv_title4;
    @BindView(R.id.ll_small_title)
    LinearLayout ll_small_title;
    @BindView(R.id.ll_active)
    LinearLayout ll_active;
    @BindView(R.id.iv_empty)
    RoundImageView iv_empty;
    @BindView(R.id.snap)
    SnapUpCountDownTimerViewss snap;
    @BindView(R.id.lav_activity_loading)
    AVLoadingIndicatorView lav_activity_loading;
    @BindView(R.id.rl_address)
    RelativeLayout rl_address;
    @BindView(R.id.tv_change)
    TextView tv_change;
    @BindView(R.id.tv_change_address)
    TextView tv_change_address;
    @BindView(R.id.tv_skill_title)
    TextView tv_skill_title;
    @BindView(R.id.tv_times)
    TextView tv_times;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    @BindView(R.id.rv_recommend)
    RecyclerView rv_recommend;
    @BindView(R.id.ll_bgc)
    RelativeLayout ll_bgc;
    @BindView(R.id.rv_skill)
    RecyclerView rv_skill;
    @BindView(R.id.rg_new)
    RadioGroup rg_new;
    @BindView(R.id.ll_skill)
    LinearLayout ll_skill;
    @BindView(R.id.rv_auto_view)
    AutoScrollRecyclerView rv_auto_view;
    @BindView(R.id.rv_auto_team)
    AutoScrollRecyclerView rv_auto_team;
    @BindView(R.id.rv_hot)
    RecyclerView rv_hot;
    @BindView(R.id.rv_hot1)
    RecyclerView rv_hot1;
    @BindView(R.id.tv_coupon_more)
    TextView tv_coupon_more;
    @BindView(R.id.iv_full2_right)
    ImageView iv_full2_right;
    @BindView(R.id.iv_full_right)
    ImageView iv_full_right;
    @BindView(R.id.iv_team_right)
    ImageView iv_team_right;
    @BindView(R.id.iv_team2_right)
    ImageView iv_team2_right;
    @BindView(R.id.tv_look_more)
    TextView tv_look_more;
    @BindView(R.id.verticalBanner)
    VerticalBannerView verticalBanner;


    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.ll_scroll)
    LinearLayout ll_scroll;
    @BindView(R.id.rl_grand)
    RelativeLayout rl_grand;
    @BindView(R.id.ll_fixed)
    LinearLayout ll_fixed;
    @BindView(R.id.ll_parent)
    RelativeLayout ll_parent;
    @BindView(R.id.rl_inSide)
    RelativeLayout rl_inSide;
    @BindView(R.id.rl_bar)
    RelativeLayout rl_bar;

    List<String> list = new ArrayList<>();
    private static final float ENDMARGINLEFT = 50;
    private static final float ENDMARGINTOP = 5;
    private static final float STARTMARGINLEFT = 20;
    private static final float STARTMARGINTOP = 72;
    private int evaluatemargin;
    private int evaluatetop;
    private RelativeLayout.LayoutParams layoutParams;
    int scrollLength;

    HotAdapter hotAdapter;
    Skill5Adapter skill5Adapter;
    ProductNormalModel productNormalModel;
    private NewAdapter newAdapter;
    IndexRecommendAdapter indexRecommendAdapter;
    CouponDialog couponDialog;
    FullAdapter fullAdapter;
    TeamAdapter teamAdapter;
    Team3Adapter team3Adapter;
    private String cell; // 客服电话
    private PrivacyDialog privacyDialog;
    ChooseHomeDialog chooseAddressDialog;
    CommonsssAdapter commonsssAdapter;
    List<String> recommendData;
    //    AnimationDrawable drawable;
    List<ProductNormalModel.DataBean.ListBean> listss = new ArrayList<>();
    //司机信息
    List<OrderModel.DataBean> driverList = new ArrayList<>();
    //八个icon集合
    List<IndexInfoModel.DataBean.IconsBean> iconList = new ArrayList<>();
    //秒杀集合
    List<HomeBaseModel.DataBean.SecKillListBean.KillsBean> skillList = new ArrayList<>();
    //秒杀预告集合
    List<HomeBaseModel.DataBean.SecKillListBean.KillsBean> skillAdvList = new ArrayList<>();
    //定时器
    Timer timer = new Timer();
    //新品集合
    List<HomeNewRecommendModel.DataBean.ListBean> newList = new ArrayList<>();
    //banner集合
    private List<String> bannerList = new ArrayList<>();
    //首页顶部推荐集合
    private List<String> recommendList = new ArrayList<>();
    private RvIconAdapter rvIconAdapter;
    Context context;
    int PageNum = 1;
    private MyOrderNumModel mModelMyOrderNum;
    private String token;
    private UpdateModel mModelUpdate;
    private boolean update;
    private boolean forceUpdate;
    private String content;
    private String url;//更新所用的url
    private AlertDialog mTypedialog;
    boolean flag;
    List<String> list1 = new ArrayList<>();
    private IndexInfoModel.DataBean data;
    //分类列表
    private List<IndexInfoModel.DataBean.ClassifyListBean> classifyList = new ArrayList<>();
    NewFragment newFragment;
    MustFragment mustFragment;
    InfoFragment infoFragment;
    CommonFragment commonFragment;
    private String questUrl;
    private CouponModel.DataBean data1;
    private int showType;
    private CommonCouponAdapter commonCouponAdapter;
    private CommonsAdapter commonsAdapter;
    private CommonAdapter commonAdapter;
    private List<CouponModel.DataBean.ActivesBean> actives = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> skillActive3 = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> skillActive2 = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> skillActive1 = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> couponActive1 = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> couponActive2 = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> couponActive3 = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> teamActive1 = new ArrayList<>();
    private List<CouponModel.DataBean.ActivesBean> fullActive1 = new ArrayList<>();

    private int spikeNum;
    private int teamNum;
    private int specialNum;
    private int fullGiftNum;
    private long currentTime;
    private long startTime;
    private long endTime;
    private Date currents;
    private Date starts;
    private VerticalBannerAdapter verticalBannerAdapter;
    private CouponListDialog couponListDialog;
    CouponListModel couponListModels;
    private CouponListAdapter couponListAdapter;
    private List<CouponListModel.DataBean.GiftsBean> lists;
    private List<TurnModel.DataBean> data2;
    private TurnTableDialog turnTableDialog;
    private SkillAdapter skillAdapter;
    private Skill2Adapter skill2Adapter;
    private Skill3Adapter skill3Adapter;
    private String deductAmountStr;
    private String offerStr;
    private String currentVersion;
    private boolean isUpdate;
    private HomeActivityDialog homeActivityDialog;
    CommonssAdapter commonssAdapter;
    int topHeight;
    int scrollLength1;
    int topHeight1;
    @Override
    public int setLayoutId() {
        return R.layout.test11;
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void findViewById(View view) {
        bind = ButterKnife.bind(this, view);


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
            }
        });


        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int y) {
                int abs_y = Math.abs(y);
                int appbarScrollLength = Math.abs(DensityUtil.dip2px(y, mActivity));
                if (appbarScrollLength >= topHeight1) {
                    if (rl_inSide.getParent() != ll_fixed) {
                        ll_parent.removeView(rl_inSide);
                        ll_fixed.addView(rl_inSide);
                    }
                } else {
                    if (rl_inSide.getParent() != ll_parent) {
                        ll_fixed.removeView(rl_inSide);
                        ll_parent.addView(rl_inSide);
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

    @Override
    public void setClickEvent() {

    }
}
