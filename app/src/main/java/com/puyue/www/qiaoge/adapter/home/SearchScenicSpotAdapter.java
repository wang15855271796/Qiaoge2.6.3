package com.puyue.www.qiaoge.adapter.home;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.model.home.SearchScenicSpotModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/20.
 */

public class SearchScenicSpotAdapter extends BaseQuickAdapter<SearchScenicSpotModel.DataBean.ListBean, BaseViewHolder> {

    public SearchScenicSpotAdapter(int layoutResId, List<SearchScenicSpotModel.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchScenicSpotModel.DataBean.ListBean model) {
        GlideModel.disPlayErrorPlace(mContext,model.defaultScenicSpotUrl,helper.getView(R.id.iv_item_goods_img));
      //  Glide.with(mContext).load(model.defaultScenicSpotUrl).crossFade().placeholder(R.mipmap.icon_default_rec).error(R.mipmap.icon_default_rec).into((ImageView) helper.getView(R.id.iv_item_goods_img));
        helper.setText(R.id.tv_item_goods_name, model.name);
        helper.setText(R.id.tv_item_goods_specification, "");
        helper.setText(R.id.tv_item_goods_price, model.monthSalesVolume);
        helper.setText(R.id.tv_item_goods_sales, model.inventory);
//        helper.setText(R.id.tv_item_goods_amount, "库存:"+model.totalReservation);
//        if (model.flag == 1) {
//            //未售完
//            helper.setImageResource(R.id.iv_item_goods_type, 0);
//        } else {
//            helper.setImageResource(R.id.iv_item_goods_type, R.mipmap.icon_sold_out);
//        }
    }
}