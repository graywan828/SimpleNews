package com.wgh.simplenews.images.presenter;

import com.google.gson.reflect.TypeToken;
import com.wgh.simplenews.base.BasePresenter;
import com.wgh.simplenews.beans.ImageBean;
import com.wgh.simplenews.images.view.ImagesView;
import com.wgh.simplenews.network.OkHttpUtils;
import com.wgh.simplenews.utils.JsonUtils;

import java.util.List;

import rx.Subscriber;
import rx.functions.Func1;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/10
 * @description
 */

public class ImagePresenter extends BasePresenter<ImagesView>{

    public ImagePresenter(ImagesView imagesView) {
        attachView(imagesView);
    }

    public void loadImageData(){
        addSubscription(OkHttpUtils.getInstance()
                .getImageApi().getImages()
                .map(new Func1<String, List<ImageBean>>() {
                    @Override
                    public List<ImageBean> call(String s) {
                        return JsonUtils.deserialize(s,new TypeToken<List<ImageBean>>(){}.getType());
                    }
                }),
                new Subscriber<List<ImageBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ImageBean> images) {
                        mvpView.addImages(images);
                    }
                });
    }

}
