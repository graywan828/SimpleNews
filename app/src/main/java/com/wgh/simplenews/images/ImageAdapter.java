package com.wgh.simplenews.images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wgh.simplenews.R;
import com.wgh.simplenews.beans.ImageBean;

import java.util.List;

/**
 * @version V1.0.0
 * @author: guanghui_wan
 * @date: 2017/2/14
 * @description
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemImageHolder> {

    private Context mContext;
    private List<ImageBean> mImageBeanList;
    private OnItemCLickListener mListener;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ItemImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        ItemImageHolder holder = new ItemImageHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemImageHolder holder, int position) {
        ImageBean bean = mImageBeanList.get(position);
        if(bean == null)
            return;
        holder.mTitle.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mImageBeanList == null ? 0 : mImageBeanList.size();
    }

    public void setData(List<ImageBean> imageBeanList) {
        mImageBeanList = imageBeanList;
        notifyDataSetChanged();
    }

    public void setOnItemCLickListener(OnItemCLickListener listener) {
        mListener = listener;
    }

    public interface OnItemCLickListener {
        void onItemClick(View view, int position);
    }

    public class ItemImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle;
        public ImageView mImage;

        public ItemImageHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mImage = (ImageView) itemView.findViewById(R.id.iv_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null) {
                mListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

}
