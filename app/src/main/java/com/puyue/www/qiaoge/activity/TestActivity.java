package com.puyue.www.qiaoge.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.puyue.www.qiaoge.NewWebViewActivity;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.cart.PayResultActivity;
import com.puyue.www.qiaoge.activity.home.CommonGoodsDetailActivity;
import com.puyue.www.qiaoge.activity.home.EvaluationActivity;
import com.puyue.www.qiaoge.activity.mine.IntegralPayActivity;
import com.puyue.www.qiaoge.activity.mine.account.EditPasswordInputCodeActivity;
import com.puyue.www.qiaoge.activity.mine.coupons.MyCouponsActivity;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.order.MyOrdersActivity;
import com.puyue.www.qiaoge.activity.mine.order.NewOrderDetailActivity;
import com.puyue.www.qiaoge.activity.mine.order.SelfSufficiencyOrderDetailActivity;

import com.puyue.www.qiaoge.adapter.home.SearchReasultAdapter;
import com.puyue.www.qiaoge.adapter.home.SearchResultAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsDetailAdapter;
import com.puyue.www.qiaoge.adapter.market.GoodsRecommendAdapter;
import com.puyue.www.qiaoge.api.cart.AddCartAPI;
import com.puyue.www.qiaoge.api.cart.AddMountChangeAPI;
import com.puyue.www.qiaoge.api.cart.CheckPayPwdAPI;
import com.puyue.www.qiaoge.api.cart.GetPayResultAPI;
import com.puyue.www.qiaoge.api.cart.OrderPayAPI;
import com.puyue.www.qiaoge.api.cart.RecommendApI;
import com.puyue.www.qiaoge.api.home.ClickCollectionAPI;
import com.puyue.www.qiaoge.api.home.GetAllCommentListByPageAPI;
import com.puyue.www.qiaoge.api.home.GetProductDetailAPI;
import com.puyue.www.qiaoge.api.home.UpdateUserInvitationAPI;
import com.puyue.www.qiaoge.api.mine.AccountCenterAPI;
import com.puyue.www.qiaoge.api.mine.GetShareInfoAPI;
import com.puyue.www.qiaoge.api.mine.GetWalletAmountAPI;
import com.puyue.www.qiaoge.banner.Banner;
import com.puyue.www.qiaoge.banner.BannerConfig;
import com.puyue.www.qiaoge.banner.GlideImageLoader;
import com.puyue.www.qiaoge.banner.Transformer;
import com.puyue.www.qiaoge.banner.listener.OnBannerListener;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.constant.AppConstant;
import com.puyue.www.qiaoge.event.GoToMarketEvent;
import com.puyue.www.qiaoge.event.OnHttpCallBack;
import com.puyue.www.qiaoge.event.WeChatPayEvent;
import com.puyue.www.qiaoge.event.WeChatUnPayEvent;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.CollapsingToolbarLayoutStateHelper;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.PublicRequestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.cart.AddCartModel;
import com.puyue.www.qiaoge.model.cart.AddMountReduceModel;
import com.puyue.www.qiaoge.model.cart.CheckPayPwdModel;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;
import com.puyue.www.qiaoge.model.cart.GetPayResultModel;
import com.puyue.www.qiaoge.model.cart.OrderPayModel;
import com.puyue.www.qiaoge.model.home.ChoiceSpecModel;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.HasCollectModel;
import com.puyue.www.qiaoge.model.home.SearchResultsModel;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;
import com.puyue.www.qiaoge.model.market.GoodsDetailModel;
import com.puyue.www.qiaoge.model.mine.AccountCenterModel;
import com.puyue.www.qiaoge.model.mine.GetShareInfoModle;
import com.puyue.www.qiaoge.model.mine.GetWalletAmountModel;
import com.puyue.www.qiaoge.model.mine.NewWebModel;
import com.puyue.www.qiaoge.model.mine.wallet.NewWebPhoneModel;
import com.puyue.www.qiaoge.utils.SharedPreferencesUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by ${王涛} on 2019/9/29
 */
public abstract class TestActivity extends FrameLayout {
    private Context mContext;
    private static final int LOADING = 1;
    private static final int LOADERROR = 2;
    private static final int NETERROR = 3;
    private static final int LOADED = 4;
    private static final int NODATA = 5;
    private ImageView loadingView;
    private LinearLayout mlinearLayoutLoading;
    private ImageView noDataView;
    private LinearLayout mlinearLayoutNoData;
    private LinearLayout mlinearLayoutLoadError;
    private ImageView netErrorView;
    private LinearLayout mlinearLayoutNetError;
    private View successView;

    private int currentState = LOADING;
    private FrameLayout.LayoutParams params;

    public TestActivity(Context context) {
        super(context);
        this.mContext = context;
        createView();
    }

    public TestActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        createView();
    }

    public TestActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initData() {
        currentState = LOADING;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                int code = onLoad();
                if (code == 200) {
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentState = LOADED;
                            successView = onSuccessView();
                            addView(successView, params);
                        }
                    });
                } else if (code == 201) {
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentState = NODATA;
                        }
                    });

                }else if (code == 404) {
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentState = LOADERROR;
                        }
                    });

                } else if (code == -1) {
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentState = NETERROR;
                        }
                    });
                }
                refreshView();
            }
        }).start();
    }

    private void refreshView() {
        mlinearLayoutLoading.setVisibility(currentState == LOADING ? View.VISIBLE : View.GONE);
        mlinearLayoutNoData.setVisibility(currentState == NODATA ? View.VISIBLE : View.GONE);
        mlinearLayoutNetError.setVisibility(currentState == NETERROR ? View.VISIBLE : View.GONE);
        mlinearLayoutLoadError.setVisibility(currentState == LOADERROR ? View.VISIBLE : View.GONE);
        if (successView != null) {
            successView.setVisibility(currentState == LOADED ? View.VISIBLE : View.GONE);
        }
    }

    public void show() {
        initData();
    }

    private void createView() {
        params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        createLoadingView();

        createNoDataView();

        createNetErrorView();

        createLoadedErrorView();

        addView(mlinearLayoutLoading, params);
        addView(mlinearLayoutNoData, params);
        addView(mlinearLayoutNetError, params);
        addView(mlinearLayoutLoadError, params);
        refreshView();
    }

    private void createNetErrorView() {
        mlinearLayoutNetError  = new LinearLayout(mContext);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.gravity = Gravity.CENTER;
        mlinearLayoutNetError.setOrientation(LinearLayout.VERTICAL);

        netErrorView = new ImageView(mContext);
//        netErrorView.setImageResource(R.drawable.net_error);

        mlinearLayoutNetError.addView(netErrorView,linearLayoutParams);
        TextView textView = new TextView(mContext);
        textView.setText("网络错误，检查您的网络或点击重试");
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentState = LOADING;
                show();
                refreshView();
            }
        });

        mlinearLayoutNetError.addView(textView,linearLayoutParams);

        mlinearLayoutNetError.setVisibility(View.GONE);
    }

    private void createNoDataView() {
        mlinearLayoutNoData  = new LinearLayout(mContext);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.gravity = Gravity.CENTER;
        mlinearLayoutNoData.setOrientation(LinearLayout.VERTICAL);

        noDataView = new ImageView(mContext);
//        noDataView.setImageResource(R.drawable.nodata);
        mlinearLayoutNoData.addView(noDataView,linearLayoutParams);
        TextView textView = new TextView(mContext);
        textView.setText("没有数据可供显示！");
        mlinearLayoutNoData.addView(textView,linearLayoutParams);
        mlinearLayoutNoData.setVisibility(View.GONE);
    }
    private void createLoadedErrorView() {
        mlinearLayoutLoadError  = new LinearLayout(mContext);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.gravity = Gravity.CENTER;
        mlinearLayoutLoadError.setOrientation(LinearLayout.VERTICAL);

        noDataView = new ImageView(mContext);
//        noDataView.setImageResource(R.drawable.nodata);
        mlinearLayoutLoadError.addView(noDataView,linearLayoutParams);
        TextView textView = new TextView(mContext);
        textView.setText("加载失败！点击重试");
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentState = LOADING;
                show();
                refreshView();
            }
        });
        mlinearLayoutLoadError.addView(textView,linearLayoutParams);
        mlinearLayoutLoadError.setVisibility(View.GONE);
    }

    private void createLoadingView() {
        mlinearLayoutLoading  = new LinearLayout(mContext);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.gravity = Gravity.CENTER;
        mlinearLayoutLoading.setOrientation(LinearLayout.VERTICAL);

        loadingView = new ImageView(mContext);
        loadingView.setImageResource(R.drawable.node);
        AnimationDrawable animationDrawable = (AnimationDrawable) loadingView.getDrawable();
        animationDrawable.start();
        mlinearLayoutLoading.addView(loadingView,linearLayoutParams);
        TextView textView = new TextView(mContext);
        textView.setText("正在加载中");
        mlinearLayoutLoading.addView(textView,linearLayoutParams);
        mlinearLayoutLoading.setVisibility(View.GONE);
    }

    public abstract View onSuccessView();

    public abstract int onLoad();
}
