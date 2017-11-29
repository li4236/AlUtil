package org.al.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by li4236 on 16/1/26.
 * 转圈自定义视图
 */
public class onCircleView extends View {
    public int index;
    private static final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public int circleRadius;
    public int color = Color.WHITE;

    public onCircleView(Context context, int index, int circleRadius) {
        this(context);
        this.index = index;
        this.circleRadius = circleRadius;
        mPaint.setStrokeWidth(1);
    }

    public onCircleView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelected()) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(0xffF06D5D);

        } else {
            mPaint.setStyle(Paint.Style.FILL);

            mPaint.setColor(0xfff0f0f0);
        }

        mPaint.setAntiAlias(true);

        //绘制空心圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, circleRadius, mPaint);
    }
}
