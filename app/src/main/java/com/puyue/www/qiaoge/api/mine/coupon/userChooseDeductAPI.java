package com.puyue.www.qiaoge.api.mine.coupon;

import android.content.Context;

import com.puyue.www.qiaoge.api.home.GetCommentListByPageAPI;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetCommentListByPageModel;
import com.puyue.www.qiaoge.model.mine.coupons.UserChooseDeductModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author daff
 * @date 2018/9/23.
 * 备注 收银台选择优惠券
 */
public class userChooseDeductAPI {

    private interface userChooseDeductService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.USER_CHOOSE_DEDUCT)
        Observable<UserChooseDeductModel> getData(
                @Field("activityBalanceVOStr") String activityBalanceVOStr,
                @Field("normalProductBalanceVOStr") String normalProductBalanceVOStr

        );
    }

    public static Observable<UserChooseDeductModel> requestData(Context context,String activityBalanceVOStr,
                                                                String normalProductBalanceVOStr) {
        userChooseDeductService service = RestHelper.getBaseRetrofit(context).create(userChooseDeductService.class);
        return service.getData(activityBalanceVOStr, normalProductBalanceVOStr);
    }

}
