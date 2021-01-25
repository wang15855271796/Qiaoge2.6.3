package com.puyue.www.qiaoge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.puyue.www.qiaoge.R;
import com.puyue.www.qiaoge.adapter.ShopImageViewsAdapter;
import com.puyue.www.qiaoge.api.home.InfoListAPI;
import com.puyue.www.qiaoge.api.home.InfoListModel;
import com.puyue.www.qiaoge.api.mine.order.SendImageAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.base.BaseSwipeActivity;
import com.puyue.www.qiaoge.helper.AppHelper;
import com.puyue.www.qiaoge.model.InfoDetailIssueModel;
import com.puyue.www.qiaoge.model.mine.order.SendImageModel;
import com.puyue.www.qiaoge.pictureselectordemo.FullyGridLayoutManager;
import com.puyue.www.qiaoge.pictureselectordemo.ShopImageViewAdapter;
import com.puyue.www.qiaoge.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${王涛} on 2021/1/11
 */
public class IssueEditInfoActivity extends BaseSwipeActivity {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_message_style)
    TextView tv_message_style;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    String msgId;
    ShopImageViewsAdapter shopImageViewAdapter;
    private List<String> picList = new ArrayList();
    @Override
    public boolean handleExtra(Bundle savedInstanceState) {
        return false;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_issue_edit);
    }

    @Override
    public void findViewById() {
        ButterKnife.bind(this);
    }

    @Override
    public void setViewData() {
        msgId = getIntent().getStringExtra("msgId");
        getCityList(msgId);

    }
    String returnPic;
    private void upImage(List<MultipartBody.Part> parts) {
        SendImageAPI.requestImgDetail(mContext, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SendImageModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SendImageModel baseModel) {
                        if (baseModel.success) {
                            returnPic = "";
                            if (baseModel.data != null) {
//                                for (int i = 0; i < baseModel.data.length; i++) {
//                                    returnPic += baseModel.data[i] + ",";
//                                }
                                String[] data = baseModel.data;

                                Gson gson = new Gson();
                                returnPic = gson.toJson(data);
                            }
                            Log.i("wwwwbbb", "onNext: " + returnPic);
                            //  sendMgs();
                        } else {
                            AppHelper.showMsg(mContext, baseModel.message);
                        }
                    }
                });
    }

    public List<MultipartBody.Part> filesToMultipartBodyParts(List<String> localUrls) {
        List<MultipartBody.Part> parts = new ArrayList<>(localUrls.size());
        for (String url : localUrls) {
            File file = new File(url);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("detailFiles", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    @Override
    public void setClickEvent() {

    }

    private void getCityList(String msgId) {
        InfoListAPI.InfoDetailIssue(mContext,msgId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InfoDetailIssueModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(InfoDetailIssueModel infoListModel) {
                        if (infoListModel.isSuccess()) {
                            InfoDetailIssueModel.DataBean data = infoListModel.getData();
                            et.setText(data.getContent());
                            tv_area.setText(data.getAreaName()+data.getCityName());
                            tv_message_style.setText(data.getMsgTypeName());
                            et_phone.setText(data.getContactPhone());
                            tv_address.setText(data.getDetailAddress());
                            List<String> pictureList = data.getPictureList();
                            GridLayoutManager manager = new GridLayoutManager(mContext,3);
                            shopImageViewAdapter = new ShopImageViewsAdapter(mContext, new ShopImageViewsAdapter.onAddPicClickListener() {
                                @Override
                                public void onAddPicClick() {
                                    showPop();
                                }
                            });

                            shopImageViewAdapter.setList(pictureList);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(shopImageViewAdapter);
                            shopImageViewAdapter.setOnItemClickListener(new ShopImageViewsAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, View v) {
                                    if (selectList.size() > 0) {
//                                        LocalMedia media = selectList.get(position);
//                                        String pictureType = media.getPictureType();
//                                        int mediaType = PictureMimeType.pictureToVideo(pictureType);
//                                        switch (mediaType) {
//                                            case 1:
//                                                // 预览图片 可自定长按保存路径
//                                                //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
//                                                PictureSelector.create(mActivity).externalPicturePreview(position, selectList);
//                                                break;
//                                            case 2:
//                                                // 预览视频
//                                                PictureSelector.create(mActivity).externalPictureVideo(media.getPath());
//                                                break;
//                                            case 3:
//                                                // 预览音频
//                                                PictureSelector.create(mActivity).externalPictureAudio(media.getPath());
//                                                break;
//                                        }
                                    }
                                }

                                @Override
                                public void deletPic(int position) {
                                    pictureList.remove(position);
                                    upImage(filesToMultipartBodyParts(pictureList));
                                }
                            });

                        } else {
                            AppHelper.showMsg(mContext, infoListModel.getMessage());
                        }
                    }
                });
    }
    PopupWindow pop;
    private int maxSelectNum = 6;
    private List<String> selectList = new ArrayList<>();
    private void showPop() {

        View bottomView = View.inflate(IssueEditInfoActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = (TextView) bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);


        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(IssueEditInfoActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum - selectList.size())
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .compress(true)
                                .isCamera(false)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(IssueEditInfoActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .compress(true)
                                .setOutputCameraPath("/CustomPath")
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    for (int i = 0; i < images.size(); i++) {
                        picList.add(images.get(i).getCompressPath());
                    }
//                    Log.d("wddassssss......",picList.toString());
                    shopImageViewAdapter.setLists(picList);
//                    shopImageViewAdapter.notifyDataSetChanged();
//                    upImage(filesToMultipartBodyParts(picList));
                    break;
            }
        }
    }

    private void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }
}
