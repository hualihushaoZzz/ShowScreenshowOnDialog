package com.example.showscreenshowondialog.FunctionClass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.showscreenshowondialog.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Create by $冯日天 on 2019/11/29
 */
public class ShowImageDialog extends Dialog {

    private View mView ;
    private Context mContext;
    private TextView mTtile;
    private List<String> mImgUrls;
    private List<String> mTitles;
    private List<PhotoView> mViews;
    private ShowImagesAdapter mAdapter;
    private ShowImagesViewPager mViewPager;
    private int currentPosition;

    public ShowImageDialog(@NonNull Context context, String[] imagespath,int position){
        super(context, R.style.transparentBgDialog);
        this.mContext=context;
        mImgUrls=new ArrayList<>();
        for (int i=0;i<imagespath.length;i++){
            mImgUrls.add(imagespath[i]);
        }
        currentPosition=position;
        initView();
        initData();
    }

    private void initView() {
        this.mView = View.inflate(mContext, R.layout.dialog_image_brower, null);
        mViewPager =  mView.findViewById(R.id.vp_images);
        mTtile =  mView.findViewById(R.id.tv_image_index);
        mTitles = new ArrayList<>();
        mViews = new ArrayList<>();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.mView);
        Window window=getWindow();
        WindowManager.LayoutParams wl=window.getAttributes();
        wl.x=0;
        wl.y=0;
        wl.width= Config.EXACT_SCREEN_WIDTH;
        wl.height= Config.EXACT_SCREEN_HEIGHT;
        wl.gravity= Gravity.CENTER;
        window.setAttributes(wl);
    }


    private void initData(){

        final PhotoViewAttacher.OnPhotoTapListener listener = new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                dismiss();
            }
        };

        for (int i = 0; i < mImgUrls.size(); i++) {
            final PhotoView photoView = new uk.co.senab.photoview.PhotoView(mContext);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            photoView.setOnPhotoTapListener(listener);
            //点击图片外围（无图片处）监听
            /**
             photoView.setOnViewTapListener(new OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y){
            dismiss();
            }
            });
             **/
            Glide.with(mContext).load(mImgUrls.get(i)).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    photoView.setImageDrawable(resource);
                }
            });
            mViews.add(photoView);
            mTitles.add(i + "");
        }

        mAdapter = new ShowImagesAdapter(mViews, mTitles);
        mViewPager.setAdapter(mAdapter);

        try {
            Class c = Class.forName("androidx.viewpager.widget.ViewPager");
            Field field =c.getDeclaredField("mCurItem");
            field.setAccessible(true);
            field.setInt(mViewPager, currentPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mTtile.setText((currentPosition+1) + "/" + mImgUrls.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTtile.setText(position + 1 + "/" + mImgUrls.size());
                PhotoView photoView = mViews.get(position);
                photoView.setOnPhotoTapListener(listener);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
