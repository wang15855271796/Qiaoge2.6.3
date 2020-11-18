package com.puyue.www.qiaoge.activity.mine.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.LogoutsEvent;
import com.puyue.www.qiaoge.api.mine.LogoutAPI;
import com.puyue.www.qiaoge.api.mine.login.LoginAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.dialog.SecretSuccessDialog;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.helper.DialogHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.helper.UserInfoHelper;
import com.puyue.www.qiaoge.utils.EnCodeUtil;
import com.puyue.www.qiaoge.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2020/11/17
 */
public class SetLoginSecretActivity extends BaseSwipeActivity {
    @BindView(R.id.et_login)
    EditText et_login;
    @BindView(R.id.et_login_sure)
    EditText et_login_sure;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.iv_close_sure)
    ImageView iv_close_sure;
    @BindView(R.id.tv_next)
    TextView tv_next;

    String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTykrDv1TEKVjDeE29kVLo5M7mctlE65WlHSMN8RVL1iA9jXsF9SMNH1AErs2lqxpv18fd3TOAw0pBaG+cXOxApKdvRDKgxyuHnONOBzxr6EyWOQlRZt94auL1ESVbLdvYa7+cISkVe+MphfQh7uI/64tGQ34aRNmvFKv9PEeBTQIDAQAB";
    String phone;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {

        return false;
    }

    @Override
    public void setContentView() {


        setContentView(R.layout.activity_set_login_secret);
    }

    @Override
    public void findViewById() {
        phone = getIntent().getStringExtra("phone");
        ButterKnife.bind(this);
    }

    String phones;
    @Override
    public void setViewData() {
        phone = getIntent().getStringExtra("phone");
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secret1 = et_login.getText().toString();
                String secret2 = et_login_sure.getText().toString();
                if(!TextUtils.isEmpty(phone)){
                    try {
                        phones = EnCodeUtil.encryptByPublicKey(phone, publicKeyStr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(secret1 !=null && secret2 !=null) {
                        if(secret1.equals(secret2)) {
                            if(secret1.length()>=6&& secret2.length()>=6) {
                                if (StringHelper.isLetterDigit(et_login_sure.getText().toString())) {
                                    setSecret(phones,secret2);
                                } else {
                                    AppHelper.showMsg(mContext, "密码由6-16位数字与字母组成");
                                }
                            } else {
                                AppHelper.showMsg(mContext, "密码位数不足!");
                            }
                        }else {
                            AppHelper.showMsg(mContext, "两次密码不一致!");
                        }
                    }else {
                        AppHelper.showMsg(mContext, "密码不能为空");
                    }


                }

            }
        });
    }

    @Override
    public void setClickEvent() {

    }

    public static boolean ispsd(String psd) {
        Pattern p = Pattern
                .compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
        Matcher m = p.matcher(psd);

        return m.matches();
    }


    private void setSecret(String phones, String secret2) {
        LoginAPI.setSecret(mContext,phones,secret2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.success) {
                            SecretSuccessDialog secretSuccessDialog = new SecretSuccessDialog(mContext) {
                                @Override
                                public void Confirm() {
                                    requestLogout();
                                    dismiss();
                                }

                                @Override
                                public void Close() {
                                    requestLogout();
                                    dismiss();
                                }
                            };
                            secretSuccessDialog.show();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }

    private void requestLogout() {
        LogoutAPI.requestLogout(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        if (baseModel.success) {
                            logouts(mContext, -10000);
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }

    private void logouts(Context context, int mStateCode) {
        if (mStateCode == -10000 || mStateCode == -10001) {
            UserInfoHelper.saveUserId(context, "");
            UserInfoHelper.saveUserType(context, "");
            UserInfoHelper.saveUserHomeRefresh(context, "");
            UserInfoHelper.saveUserMarketRefresh(context, "");
            UserInfoHelper.saveChangeFlag(mActivity, "0");
            Intent intent = new Intent(SetLoginSecretActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
