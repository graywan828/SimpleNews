package com.wgh.simplenews.images.view;

import com.wgh.simplenews.beans.ImageBean;

import java.util.List;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/10
 * @description
 */

public interface ImagesView {

    void showProgress();

    void hideProgress();

    void addImages(List<ImageBean> images);

}
