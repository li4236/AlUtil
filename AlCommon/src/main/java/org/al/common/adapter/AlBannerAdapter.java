package org.al.common.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.al.common.impl.AlImageLoader;
import org.al.common.view.onCircleView;


/**
 * 广告的适配器
 */
public class AlBannerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {


    private ViewPager mViewPager;

    private Context mContext;

    private AlImageLoader mAlImageLoader;

    public AlBannerAdapter(Context context, int size, ViewPager viewPager, AlImageLoader alImageLoader) {


        this.mViewPager = viewPager;

        this.mContext = context;

        this.mAlImageLoader = alImageLoader;

        this.circleCount = size;

        if (size > 1) {
            this.circleCount = size - 2;
        }


        if (size > 1) {

            myHandler = new TimeHandler();


            //头部添加一个
//            adverDatas.add(0, adverDatas.get(adverDatas.size() - 1));
//            //尾部添加一个
//            adverDatas.add(adverDatas.get(1));


            this.mViewPager.setOnPageChangeListener(this);

            myHandler.sendEmptyMessageDelayed(SCROLL_WHAT, SCROLL_WHAT);
        }

        this.mViewPager.setAdapter(this);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return circleCount == 0 ? 0 : circleCount;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // TODO Auto-generated method stub
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);


        ImageView imageView = new ImageView(mContext);

        imageView.setLayoutParams(margin);

//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //加载网络数据图片

//        x.image().bind(imageView, mAdverDatas.get(position).getAdImg(), options);

//        Glide.with(mContext.getApplicationContext()).load(mAdverDatas.get(position).getAdImg()).centerCrop().into(imageView);

        mAlImageLoader.initView(mContext, position, imageView);


        container.addView(imageView);

        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                if (mAlImageLoader != null) {

                    mAlImageLoader.onClick(mContext, arg0);
//                    _callback.onClick(arg0, mAdverDatas.get(position));
                }
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub
        if (state == ViewPager.SCROLL_STATE_IDLE) {//当滑动停止时，判断是否到达边界
            if (curIndex <= 0) {
                mViewPager.setCurrentItem(circleCount, false);//瞬间跳动到倒数第二页
                curIndex = circleCount;
            } else if (curIndex >= circleCount + 1) {
                mViewPager.setCurrentItem(1, false);//瞬间跳动到第二页
                curIndex = 1;
            }
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        setCurrentItem(arg0);

    }

    /**
     * 滑动页，同时改变相应的圆点状态
     *
     * @param item 当前页码
     */
    public void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        curIndex = item;
        mViewPager.setCurrentItem(item);

        if (item == 0) item = circleCount - 1;

        else if (item == circleCount + 1) item = 0;

        else item = item - 1;


        if (circleLayout == null)
            return;
        for (int i = 0; i < circleCount; i++) {//遍历标题，改变选中的背景
            onCircleView child = (onCircleView) circleLayout.getChildAt(i);
            boolean isSelected = (i == item);
            child.setSelected(isSelected);
        }
    }

    class TimeHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            setCurrentItem(curIndex + 1);

            myHandler.removeMessages(SCROLL_WHAT);

            myHandler.sendEmptyMessageDelayed(SCROLL_WHAT, SCROLL_Time);
        }

    }

    /**
     * 当前显示的序号
     */
    private int curIndex = 0;

    /**
     * 圆点数目
     */
    private int circleCount = 0;

    private final int SCROLL_WHAT = 99;

    private final int SCROLL_Time = 6000;

    private TimeHandler myHandler;

    private LinearLayout circleLayout;

    public void setCircleLayout(LinearLayout circleLayout) {

        this.circleLayout = circleLayout;
    }


    private AlAdverOnClickListener _callback;


    public void setCallback(AlAdverOnClickListener callback) {

        this._callback = callback;


    }
}
