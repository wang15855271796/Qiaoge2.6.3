package com.puyue.www.qiaoge.activity.home;

import android.app.Activity;
import android.widget.PopupWindow;

import com.puyue.www.qiaoge.model.home.CityChangeModel;
import com.puyue.www.qiaoge.view.CascadingMenuViewOnSelectListener;

import java.util.ArrayList;

/**
 * Created by ${王涛} on 2021/2/3
 */
public class CascadingMenuPopWindow  extends PopupWindow {
    private Activity context;
    private CascadingMenuViews cascadingMenuView;
    private ArrayList<CityChangeModel.DataBean> areas;
    //提供给外的接口
    private CascadingMenuViewOnSelectListener menuViewOnSelectListener;

    public CascadingMenuPopWindow(Activity mActivity, ArrayList<CityChangeModel.DataBean> listCity) {
        super(mActivity);
        this.context=mActivity;
        this.areas=listCity;
        init();
    }


    public void setMenuItems() {
        this.areas = areas;
    }
    public void setMenuViewOnSelectListener(CascadingMenuViewOnSelectListener menuViewOnSelectListener) {
        this.menuViewOnSelectListener = menuViewOnSelectListener;
    }

    public void init(){
        //实例化级联菜单
        cascadingMenuView=new CascadingMenuViews(context,areas);
        setContentView(cascadingMenuView);
        //设置回调接口
        cascadingMenuView.setCascadingMenuViewOnSelectListener(new MCascadingMenuViewOnSelectListener());
    }
    //级联菜单选择回调接口
    class MCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener{

        @Override
        public void getValue(CityChangeModel.DataBean menuItem) {
            if(menuViewOnSelectListener!=null){
                menuViewOnSelectListener.getValue(menuItem);
            }
        }

        @Override
        public void getValues(CityChangeModel.DataBean.CityNamesBean area) {
            if(menuViewOnSelectListener!=null){
                menuViewOnSelectListener.getValues(area);
                dismiss();
            }
        }

        @Override
        public void cloese() {
            if(menuViewOnSelectListener!=null){
                dismiss();
            }
        }

    }
}
