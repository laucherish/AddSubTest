package com.laucherish.addsubtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 为了方便，我没有把加减号集成到控件中，你在布局里面自己添加即可
 *
 * Created by laucherish on 2018/7/1.
 */

public class AddSubView extends View {

    // 刻度中间小圆圈
    private Bitmap circleBitmap;

    private Paint paint = new Paint();

    // 最大刻度
    private int MAX = 5;

    // 当前刻度
    private int current = 0;
    // 控件高度
    private int height;
    // 控件宽度
    private int width;
    // 两个刻度矩形之间的间隔
    private int rectMargin;
    // 刻度矩形高
    private int rectH;
    // 刻度矩形宽
    private int recW;
    // 刻度最左侧距离控件最左侧距离
    private int leftMargin;

    private Context context;

    public AddSubView(Context context) {
        super(context);
        init(context);
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        circleBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_circle);

        // 设置初始化的属性值，按照UI具体需求修改吧
        rectH = dp2px(8);
        recW = dp2px(40);
        height = dp2px(80);
        width = dp2px(260);
        rectMargin = dp2px(5);
        leftMargin = dp2px(15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制背景颜色
        paint.setColor(getResources().getColor(R.color.colorWhite));
        canvas.drawRect(0, 0, width, height, paint);

        // 绘制被选中的刻度矩形
        paint.setColor(getResources().getColor(R.color.colorGreen));
        for (int i = 0; i < current; i++) {
            float left = leftMargin + (i + 1) * rectMargin + i * recW;
            float right = left + recW;
            canvas.drawRect(left, (height - rectH) / 2, right, (height - rectH) / 2 + rectH,
                    paint);
        }

        // 绘制没有被选中的刻度矩形
        paint.setColor(getResources().getColor(R.color.colorBg));
        for (int i = current; i < MAX; i++) {
            float left = leftMargin + (i + 1) * rectMargin + i * recW;
            float right = left + recW;
            canvas.drawRect(left, (height - rectH) / 2, right, (height - rectH) / 2 + rectH,
                    paint);
        }

        // 绘制刻度中间小圆圈
        float circleLeft = (float) (leftMargin + current * (rectMargin + recW) + 0.5 * rectMargin - circleBitmap.getWidth() / 2);
        canvas.drawBitmap(circleBitmap, circleLeft, (height - circleBitmap.getHeight()) / 2, paint);

    }

    /**
     * 设置控件宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    /**
     * 刻度加
     */
    public void add() {
        if (current < MAX) {
            current++;
            postInvalidate();
        }
    }

    /**
     * 刻度减
     */
    public void sub() {
        if (current > 0) {
            current--;
            postInvalidate();
        }
    }

    /**
     * 获取当前刻度
     *
     * @return 刻度值
     */
    public int getCurrent() {
        return current;
    }

    /**
     * 设置初始刻度
     *
     * @param current 刻度值
     */
    public void setCurrent(int current) {
        this.current = current;
        postInvalidate();
    }

    /**
     * dp转换成px
     *
     * @param dpValue dp
     * @return px
     */
    public int dp2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
