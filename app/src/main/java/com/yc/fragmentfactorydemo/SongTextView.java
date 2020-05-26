package com.yc.fragmentfactorydemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created  on 2016/3/13.
 */
public class SongTextView extends View {
    private int postIndex;
    private Paint mPaint;
    private int delta = 15;
    private float mTextHeight;
    private float mTextWidth;
    private String mText="文字精确高度文字精文字精确高度文字精文字精确高度文字精文字精确高度文字精";
    private PorterDuffXfermode xformode;
    public SongTextView(Context ctx)
    {
        this(ctx,null);
    }
    public SongTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SongTextView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xformode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint.setColor(Color.CYAN);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,20,getContext().getResources().getDisplayMetrics()));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setXfermode(null);
        mPaint.setTextAlign(Paint.Align.LEFT);
        //文字精确高度
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom-fontMetrics.descent-fontMetrics.ascent;
        mTextWidth  = mPaint.measureText(mText);
    }
    /**
     *计算 控件的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final  int mWidth;
        final  int mHeight;
        /**
         * 设置宽度
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY)// match_parent , accurate
            mWidth = widthSize;
        else
        {//UNSPECIFIED
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight()
                    + getMeasuredWidth();
            if (widthMode == MeasureSpec.AT_MOST)// wrap_content
                mWidth = Math.min(desireByImg, widthSize);
            else
                mWidth = desireByImg;
        }
        /***
         * 设置高度
         */
        int   heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int   heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY)// match_parent , accurate
            mHeight = heightSize;
        else
        {
            int desire = getPaddingTop() + getPaddingBottom()
                    + getMeasuredHeight();
            if (heightMode == MeasureSpec.AT_MOST)// wrap_content
                mHeight = Math.min(desire, heightSize);
            else
                mHeight = desire;
        }
        setMeasuredDimension( mWidth,  mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap srcBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas srcCanvas = new Canvas(srcBitmap);

        TextPaint tp = new TextPaint();
        tp.setColor(Color.BLUE);
        tp.setStyle(Paint.Style.FILL);
        tp.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,20,getContext().getResources().getDisplayMetrics()));

        int[] colors = {Color.RED, Color.GREEN, Color.BLUE};//颜色的数组
        float[] position = {0f, 0.7f, 1.0f};//颜色渐变位置的数组
        LinearGradient mLinearGradient = new LinearGradient(0, 0, tp.getTextSize() * mText.length(), 0, colors, position, Shader.TileMode.CLAMP);
        tp.setShader(mLinearGradient);



        StaticLayout myStaticLayout=StaticLayout.Builder.obtain(mText,0,mText.length(),tp,getWidth()).build();
        myStaticLayout.draw(srcCanvas);
        srcCanvas.save();
        srcCanvas.restore();

//        srcCanvas.drawText(mText, 0, mTextHeight, mPaint);
        //新建一个画布 先把文字画上去
        //然后设置Xfermode 设置后面和前面的覆盖的方式。 这里这是SRCIN 就是后面的只显示在前面的位置上
        //即 重新画一个矩形 只有矩形和文字重合的位置才显示颜色
        mPaint.setXfermode(xformode);
        mPaint.setColor(Color.RED);
        RectF rectF = new RectF(0,0,animatedValue+50,-mPaint.getFontMetrics().top+mPaint.getFontMetrics().descent);


        //descent 最下面到文字底部的距离 就是行间距
        //ascent最下面到文字顶部的距离 是个负数


        //然后再画矩形 矩形左上下位置都和bitmap一致  右侧逐渐变化，设置矩形为自己想要的颜色，
            //经过Xfermode  SRCin 就会只染色文字部分
       srcCanvas.drawRect(rectF, mPaint);

        canvas.drawBitmap(srcBitmap, 0, 0, null);
        init();

    }


    private void setTextViewStyles(TextView textView) {
        int[] colors = {Color.RED, Color.GREEN, Color.BLUE};//颜色的数组
        float[] position = {0f, 0.7f, 1.0f};//颜色渐变位置的数组
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getPaint().getTextSize() * textView.getText().length(), 0, colors, position, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(mLinearGradient);
        textView.invalidate();
    }




    ValueAnimator animator;
    int animatedValue;
    public void startUi() {
        animator = ValueAnimator.ofInt(0, (int) mTextWidth);//属性动画插值器
        Log.i("aaaa", "startUi: "+getMeasuredHeight());
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(-1);
        animator.setDuration(1500);//设置动画持续时间
//        animator.setRepeatCount(-1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener((animation -> {//lambda表达式
            animatedValue = (int) animation.getAnimatedValue();
            postInvalidateDelayed(16);//重绘刷新整个View
        }));
        animator.start();//开始执行动画
    }
}