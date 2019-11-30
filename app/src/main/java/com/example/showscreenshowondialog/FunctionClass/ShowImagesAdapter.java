package com.example.showscreenshowondialog.FunctionClass;


import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;
import uk.co.senab.photoview.PhotoView;


/**
 * Create by $冯日天 on 2019/11/29
 */
public class ShowImagesAdapter extends PagerAdapter {

    private List<PhotoView> views;
    private List<String> titles;

    public ShowImagesAdapter(List<PhotoView> views, List<String> titles) {
        this.views = views;
        this.titles = titles;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        (container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? "" : titles.get(position);
    }

}
