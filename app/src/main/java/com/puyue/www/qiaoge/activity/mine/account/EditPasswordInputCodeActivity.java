package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.order.MyConfireOrdersActivity;
import com.puyue.www.qiaoge.api.mine.login.ChangeLoginPhoneAPI;
import com.puyue.www.qiaoge.api.mine.login.CheckCommonCodeAPI;
import com.puyue.www.qiaoge.api.mine.login.SendCodeAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.NetWorkHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.mine.login.ChangeLoginPhoneModel;
import com.puyue.www.qiaoge.model.mine.login.CheckPasswordCodeModel;
import com.puyue.www.qiaoge.utils.EnCodeUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/8.
 */

public class EditPasswordInputCodeActivity extends BaseSwipeActivity implements View.OnClickListener {

    public static final String TEL = "tel";
    public static final String SOURCE = "Source";
    private ImageView mIvBack;
    private TextView mEdit;
    private TextView mBtnNext;
    private TextView mTvTitle;
    private int mCode;
    TextView tv_yzm;
    String phone;
    EditText et_yzm;
    String et_yzms;
    TextView tv_stop;
    String mTel;
    String mSource;
    public static Intent getIntent(Context context, Class<?> cls, String type, String tel, String source,String forgetPsw,int orderDeliveryType,double payAmount) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtra(TEL, tel);
        intent.putExtra(SOURCE, source);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleExtra(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        mTel = getIntent().getStringExtra(TEL);
        mSource = getIntent().getStringExtra(SOURCE);
        return false;
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit_pay_password_input_code);
    }

    @Override
    public void findViewById() {
        tv_stop = (TextView) findViewById(R.id.tv_stop);
        et_yzm = (EditText) findViewById(R.id.et_yzm);
        tv_yzm = (TextView) findViewById(R.id.tv_yzm);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mEdit = (TextView) findViewById(R.id.tv_phone);
        mBtnNext = (TextView) findViewById(R.id.tv_next);
        mTvTitle = (TextView) findViewById(R.id.tv_pp_input_phone_title);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {
        mIvBack.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        tv_yzm.setOnClickListener(this);
        tv_stop.setOnClickListener(this);

        mEdit.setText(phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_stop:
                //直接修改登录密码界面
                Intent intent = new Intent(mContext,LoginTest1Activity.class);
                intent.putExtra("phone",mTel);
                startActivity(intent);
                break;
        }
    }
}
