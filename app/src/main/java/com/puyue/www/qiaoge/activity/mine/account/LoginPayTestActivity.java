package com.puyue.www.qiaoge.activity.mine.account;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${王涛} on 2020/11/18
 */
public class LoginPayTestActivity extends BaseSwipeActivity {
    @BindView(R.id.et_pay)
    EditText et_pay;
    @BindView(R.id.tv_next)
    TextView tv_next;
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_pay_test);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
    }

    @Override
    public void setViewData() {

    }

    @Override
    public void setClickEvent() {

    }
}
