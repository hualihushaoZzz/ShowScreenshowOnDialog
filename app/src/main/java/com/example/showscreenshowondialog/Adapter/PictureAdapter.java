package com.example.showscreenshowondialog.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.showscreenshowondialog.FunctionClass.Picture;
import com.example.showscreenshowondialog.R;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * Create by $冯日天 on 2019/11/28
 */
public class PictureAdapter extends BaseAdapter {

    List<Picture> pictures;
    private Context mContext;

    public PictureAdapter(String[] titles,String[] images,Context context) {
        super();
        pictures = new ArrayList<Picture>();
        for (int i=0;i<images.length;i++){
            Picture picture=new Picture(titles[i],images[i]);
            pictures.add(picture);
        }
        Log.d(TAG, "PictureAdapter: pictures.size "+pictures.size());
        mContext=context;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int i) {
        return pictures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(mContext,R.layout.gridview_item,null);
            viewHolder.mImageView=convertView.findViewById(R.id.image_jrcs);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //从list取出对象
        Picture picture=pictures.get(i);
        //设置item的内容
        Glide.with(mContext).load(picture.getImageId()).into(viewHolder.mImageView);
        return convertView;
    }


    private static class ViewHolder{
        public ImageView mImageView;
    }


}
