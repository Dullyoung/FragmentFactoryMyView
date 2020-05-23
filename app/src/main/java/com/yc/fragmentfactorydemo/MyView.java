package com.yc.fragmentfactorydemo;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyView extends View {
    Paint mOuterPaint, mInnerPaint, mTextPaintX, mTextPaint;
    Bitmap bitmap;
    Point screenSize = new Point();
    ;
    Rect rect;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setClickable(true);
        this.setFocusable(true);
        config();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    public void setScreenSize(Point point) {
        screenSize = point;
    }

    public MyView(Context context) {
        super(context);
        this.setClickable(true);
        this.setFocusable(true);

    }

    void config() {

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setColor(Color.BLUE);
        mOuterPaint.setStrokeWidth(50);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(Color.RED);
        mInnerPaint.setStrokeWidth(50);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setTextSize(20);
        mTextPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setFakeBoldText(true);


        //@param left矩形左侧的X坐标。
        //*@param top矩形顶部的Y坐标。
        //*@param right矩形右侧的X坐标。
        //*@param Bottom矩形底部的Y坐标
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);





        //画俩圆弧
//        RectF rectF = new RectF(50, 50, screenSize.x - 50, screenSize.x - 50);
//
//        canvas.drawArc(rectF, 0, 360, false, mOuterPaint);//绘制地下蓝色的
//        String mText = "" + animatedValue / 60;
//        Rect rect = new Rect();
//        mTextPaint.getTextBounds(mText, 0, mText.length(), rect);//获取文本框
//        canvas.drawText(mText, screenSize.x / 2 - mTextPaint.getTextSize(), screenSize.x / 2, mTextPaint);//绘制文字

//        canvas.drawArc(rectF, animatedValue, 20f, false, mInnerPaint);

//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        paint.setAntiAlias(true);
//        paint.setStrokeWidth(20);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setPathEffect(new CornerPathEffect(50));
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        if (lines != null && lineCount > 0) {
//            for (Path p : lines
//            ) {
//                canvas.drawPath(p, paint);
//            }
//        }
//画爱心
//        int width=screenSize.x;
//        int height=screenSize.x;
//        //左半面
//        Path path = new Path();
//        path.moveTo(width / 2, height / 4);
//
//        path.cubicTo((width * 6) / 7, height / 9, (width * 12) / 13, (height * 2) / 5, width / 2, (height * 7) / 12);
//        canvas.drawPath(path, mInnerPaint);
//        //右半面
//        Path path2 = new Path();
//        path2.moveTo(width / 2, height / 4);
//        path2.cubicTo(width / 7, height / 9, width / 13, (height * 2) / 5, width / 2, (height * 7) / 12);
//        canvas.drawPath(path2, mInnerPaint);


    }


    public void clear() {
        lines.clear();
        lineCount = lines.size();
        invalidate();
    }

    int animatedValue = 0;
    ValueAnimator animator;

    public void pause() {
        if (!animator.isPaused()) {
            animator.pause();

        } else

            animator.resume();


    }

    public void startUi() {
        animator = ValueAnimator.ofInt(0, screenSize.x-50);//属性动画插值器
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);//设置动画持续时间
//        animator.setRepeatCount(-1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener((animation -> {//lambda表达式
            animatedValue = (int) animation.getAnimatedValue();
            postInvalidateDelayed(16);//重绘刷新整个View
        }));
        animator.start();//开始执行动画
    }

    String TAG = "aaaa";


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    List<Path> lines = new ArrayList<>();
    int lineCount = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {// 按下这个屏幕
            Path path = new Path();
            path.moveTo(event.getX(), event.getY());
            lines.add(path);
            lineCount = lines.size();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {// 在屏幕上移动
            lines.get(lineCount - 1).lineTo(event.getX(), event.getY());
            invalidate();
        }
        return true;

    }


    int hex2rgb(String startColor, String endColor) {
        if (startColor.indexOf("#") != 0 || endColor.indexOf("#") != 0 || startColor.length() != 7 || endColor.length() != 7) {
            Log.i(TAG, "rgb2hex: 必须传入已#开头的16进制颜色值");
            return 0;
        }
        startColor = startColor.replace("#", "");
        endColor = endColor.replace("#", "");


        int sRed = Hex2Int(startColor.substring(0, 2));
        int sGreen = Hex2Int(startColor.substring(2, 4));
        int sBlue = Hex2Int(startColor.substring(4, 6));

        int eRed = Hex2Int(endColor.substring(0, 2));
        int eGreen = Hex2Int(endColor.substring(2, 4));
        int eBlue = Hex2Int(endColor.substring(4, 6));

        int red = eRed > sRed ? (((eRed - sRed) * animatedValue) / screenSize.x) + sRed : (((sRed - eRed) * animatedValue) / screenSize.x) + eRed;
        int green = eGreen > sGreen ? (((eGreen - sGreen) * animatedValue) / screenSize.x) + sGreen : (((sGreen - eGreen) * animatedValue) / screenSize.x) + eGreen;
        int blue = eBlue > sBlue ? (((eBlue - sBlue) * animatedValue) / screenSize.x) + sBlue : (((sBlue - eBlue) * animatedValue) / screenSize.x) + eBlue;

        return Color.parseColor("#FF" + int2hex(red) + int2hex(green) + int2hex(blue));
    }

    String int2hex(int a) {
        int a1 = a / 16;
        int a2 = a % 16;
        String a11;
        String a22;
        switch (a1) {
            case 10:
                a11 = "A";
                break;
            case 11:
                a11 = "B";
                break;
            case 12:
                a11 = "C";
                break;
            case 13:
                a11 = "D";
                break;
            case 14:
                a11 = "E";
                break;
            case 15:
                a11 = "F";
                break;
            default:
                a11 = a1 + "";
                break;
        }
        switch (a2) {
            case 10:
                a22 = "A";
                break;
            case 11:
                a22 = "B";
                break;
            case 12:
                a22 = "C";
                break;
            case 13:
                a22 = "D";
                break;
            case 14:
                a22 = "E";
                break;
            case 15:
                a22 = "F";
                break;
            default:
                a22 = a2 + "";
                break;

        }
        return a11 + a22;
    }

    int Hex2Int(String hex2) {
        String a = hex2.substring(0, 1);
        String b = hex2.substring(1, 2);
        int aa, bb;
        switch (a) {
            case "A":
            case "a":
                aa = 10;
                break;
            case "B":
            case "b":
                aa = 11;
                break;
            case "C":
            case "c":
                aa = 12;
                break;
            case "D":
            case "d":
                aa = 13;
                break;
            case "E":
            case "e":
                aa = 14;
                break;
            case "F":
            case "f":
                aa = 15;
                break;
            default:
                aa = Integer.parseInt(a);
                break;
        }
        switch (b) {
            case "A":
            case "a":
                bb = 10;
                break;
            case "B":
            case "b":
                bb = 11;
                break;
            case "C":
            case "c":
                bb = 12;
                break;
            case "D":
            case "d":
                bb = 13;
                break;
            case "E":
            case "e":
                bb = 14;
                break;
            case "F":
            case "f":
                bb = 15;
                break;
            default:
                bb = Integer.parseInt(a);
                break;
        }

        return aa + bb * 16;

    }

}
