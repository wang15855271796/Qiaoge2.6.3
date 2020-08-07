package com.puyue.www.qiaoge.adapter.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.helper.GlideRoundTransform;
import com.puyue.www.qiaoge.listener.NoDoubleClickListener;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.view.GlideModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/18.
 */

public class GetProductListAdapter extends BaseQuickAdapter<GetProductListModel.DataBean.ListBean, BaseViewHolder> {

    private ImageView ivSoldOut;// 是否售完
    private TextView priceReduction;
    private  ImageView shoppingCart;
    private  onClick onClickImage;
    private TextView tv_item_goods_spc;

    public GetProductListAdapter(int layoutResId, List<GetProductListModel.DataBean.ListBean> data, onClick onClickImage) {
        super(layoutResId, data);
        this.onClickImage=onClickImage;
    }

    @Override
    protected void convert(final BaseViewHolder helper, GetProductListModel.DataBean.ListBean model) {
        ivSoldOut=helper.getView(R.id.iv_sold_out);
        priceReduction=helper.getView(R.id.priceReduction);
        tv_item_goods_spc=helper.getView(R.id.tv_item_goods_spc);
        shoppingCart=helper.getView(R.id.shoppingCart);
        helper.setText(R.id.tvBay, model.monthSalesVolume);
        helper.setText(R.id.tv_item_goods_name, model.productName);
        helper.setText(R.id.tv_item_goods_specification, model.spec);
        helper.setText(R.id.tv_item_goods_price, "￥"+model.price);
        helper.setText(R.id.tv_item_goods_spc,"/"+model.productUnitName);
        GlideModel.displayTransForms(mContext,model.imgUrl,helper.getView(R.id.iv_item_goods_img));
      //  Glide.with(mContext).load(model.imgUrl).crossFade().transform(new GlideRoundTransform(mContext, 3)).
               // error(R.mipmap.icon_default_rec).into((ImageView) helper.getView(R.id.iv_item_goods_img));
        priceReduction.setVisibility(View.GONE);





        if (model.flag == 1) {
            //未售完
            ivSoldOut.setVisibility(View.GONE);
            shoppingCart.setVisibility(View.VISIBLE);
        } else {
            ivSoldOut.getBackground().setAlpha(150);
            ivSoldOut.setVisibility(View.VISIBLE);
            shoppingCart.setEnabled(false);
        }
        shoppingCart.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                onClickImage.shoppingCartOnclick(helper.getLayoutPosition());
            }
        });
    }
    public  interface  onClick{
        void shoppingCartOnclick(int position);
    }
}