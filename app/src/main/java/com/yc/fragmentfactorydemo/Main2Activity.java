package com.yc.fragmentfactorydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.animation.ValueAnimator.INFINITE;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MyView myView = findViewById(R.id.mImage);
        Point point=new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(point);
        myView.setScreenSize(point);
        myView.startUi();
        findViewById(R.id.btn).setOnClickListener(v->{
            myView.clear();
        });
        SongTextView songTextView=findViewById(R.id.stv);
        songTextView.startUi();

    }


    @Override
    public void onBackPressed() {

        finish();
    }


    @Override
    protected void onDestroy() {


        super.onDestroy();

    }
}
