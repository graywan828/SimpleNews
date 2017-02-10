package com.wgh.simplenews.images.presenter;

import com.wgh.simplenews.base.BasePresenter;
import com.wgh.simplenews.images.view.ImagesView;

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
        
    }

}
