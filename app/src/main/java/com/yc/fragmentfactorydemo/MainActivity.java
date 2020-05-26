package com.yc.fragmentfactorydemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.autoclick.OtherActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    WindowManager windowManager;
    int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.mViewPager);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        startActivity(new Intent(this,Main2Activity.class));

    }

    public void go() {

//      startActivity(new Intent(this,Main2Activity.class));
        MyBean myBean = new MyBean(); //创建类对象

        Class<?> class1 = myBean.getClass();  //获取类

        Method method = null;
        try {

            Field field = class1.getDeclaredField("name");
            field.setAccessible(true);
            field.set(myBean, "Dullyoung gg");
            Log.i(TAG, "go: " + myBean.getName());
            Constructor constructor = class1.getDeclaredConstructor(int.class, int.class, int.class);
            constructor.setAccessible(true);
            constructor.newInstance(66, 77, 88);
            //获取方法 并设置参数类型 参数类型会自己加上 不用打
            method = class1.getDeclaredMethod("setA", int.class);
            //然后设置方法可访问
            method.setAccessible(true);
            //设置要传入的参数
            Object[] arg1s = {99};
            //然后后用获取到的方法引起  参数是类对象和要传入的参数
            method.invoke(myBean, arg1s);
            //会有一大堆Exception 直接用exception全部框起来省事
        } catch (Exception e) {
            Log.i(TAG, "go: " + e);
            e.printStackTrace();
        }
        Log.i("aaaa", "onCreate: " + myBean.getA());
    }

    String TAG = "aaaa";




}
