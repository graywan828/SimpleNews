package com.wgh.simplenews.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wgh.simplenews.R;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/9
 * @description 图片加载工具类
 */

public class GlideUtils {

    public static void display(Context context, ImageView imageView,String url){
        if(imageView ==null){
            return;
        }
        Glide.with(context).load(url).placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail).crossFade().into(imageView);
    }

}
