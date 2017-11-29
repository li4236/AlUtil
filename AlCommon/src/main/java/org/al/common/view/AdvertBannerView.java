package org.al.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.al.common.R;
import org.al.common.adapter.AlBannerAdapter;
import org.al.common.impl.AlImageLoader;
import org.al.common.impl.AlPositionCallback;
import org.al.common.utils.ComDisplayUtil;


/**
 * Created by li4236 on 16/1/26.
 * 广告自定义视图
 */
public class AdvertBannerView extends FrameLayout {

    private Context context;

    private ViewPager viewPager;

    private LinearLayout mBottomLayout;

    private int mPointBottom = 15;

    public AdvertBannerView(Context context) {

        super(context);

        this.context = context;

        ininView();
    }

    public AdvertBannerView(Context context, int pointBottom) {

        super(context);

        mPointBottom = pointBottom;

        this.context = context;

        ininView();
    }

    public AdvertBannerView(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.context = context;
        initAttrs(attrs);
        ininView();
    }

    public AdvertBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs);
        ininView();

    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = this.getContext().obtainStyledAttributes(attrs,
                R.styleable.advertBar);
        if (ta != null) {

            mPointBottom = ta.getInt(R.styleable.advertBar_bottomDistance,
                    15);


            ta.recycle();

        }
    }

    public void ininView() {

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        viewPager = new ViewPager(context);

        viewPager.setLayoutParams(params);

        LayoutParams mParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        mBottomLayout = new LinearLayout(context);

//        mBottomLayout.setBackgroundColor(0xffff6300);

        mBottomLayout.setOrientation(LinearLayout.HORIZONTAL);

        mParams.gravity = Gravity.BOTTOM;

        mBottomLayout.setGravity(Gravity.CENTER | Gravity.BOTTOM);

        mParams.setMargins(0, 0, 0, ComDisplayUtil.dip2px(mPointBottom,context));

        mBottomLayout.setLayoutParams(mParams);

        addView(viewPager);

        addView(mBottomLayout);


    }

    /**
     * 底部有圆圈
     */
    public void addCircleDatas(AlImageLoader alImageLoader) {

        int bannerSize = alImageLoader.getBannerSize();
        if (bannerSize == 0)
            return;
//        int cicleCount = datas.size();

        //初始化广告的适配器
        AlBannerAdapter mAdapter = new AlBannerAdapter(context, bannerSize, viewPager,alImageLoader);

        if (bannerSize == 1) {

            this.removeView(mBottomLayout);

        } else {


            for (int i = 0; i < bannerSize; i++) {

                addCircle(i);
            }

            mAdapter.setCircleLayout(mBottomLayout);
        }


        //设置图片点击事件
//        mAdapter.setCallback(callback);

    }

//    /**
//     * 底部没有点
//     */
//    public void addNoCircleDatas(List<AlBannerInfo> datas, AlAdverOnClickListener callback,AlImageLoader alImageLoader) {
//
//        if (datas == null || datas.size() == 0)
//            return;
//        this.removeView(mBottomLayout);
//
//        //初始化广告的适配器
//        AlBannerAdapter mAdapter = new AlBannerAdapter(context, datas, viewPager,alImageLoader);
//
//        //设置图片点击事件
//        mAdapter.setCallback(callback);
//
//
//    }


    /*添加底部圆圈*/
    private void addCircle(int index) {

        onCircleView circleView = new onCircleView(context, index, circleRadiu);

        int circleWidth = 2 * (circleRadiu + circlePadding);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(circleWidth, circleWidth);

        lp.setMargins(5, 0, 5, 0);

        circleView.setLayoutParams(lp);

        mBottomLayout.addView(circleView);
    }

    /**
     * @param gravity 控制底部圆圈的位置
     */
    public void setCirclePosition(int gravity) {
        if (mBottomLayout != null)
            mBottomLayout.setGravity(gravity | Gravity.BOTTOM);
    }


    /**
     * 获取底部圆点位置
     */
    public void setAdvertPosition(AlPositionCallback mItemCallback) {
        this.mItemCallback = mItemCallback;
    }


    private AlPositionCallback mItemCallback;

    private int circleRadiu = 8;

    private int circlePadding = 3;


}
