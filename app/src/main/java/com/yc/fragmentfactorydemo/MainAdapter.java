package com.yc.fragmentfactorydemo;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

public class MainAdapter extends FragmentPagerAdapter {
    FragmentManager fragmentManager;
String TAG="aaaa";
    public MainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragmentManager = fm;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = FragmentFactory.create(position);
        return fragment;
    }

    @Override
    public int getCount() {

        return 5;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.i(TAG, "instantiateItem: "+position);
        //这里如果有懒加载，就从懒加载中取出Fragment，然后show 如果没有懒加载就会getItem生成新的。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            transaction.show(fragment);
        }
        /**
         * 使用的 commit方法是在Activity的onSaveInstanceState()之后调用的，这样会出错，因为
         * onSaveInstanceState方法是在该Activity即将被销毁前调用，来保存Activity数据的，如果在保存玩状态后
         * 再给它添加Fragment就会出错。解决办法就是把commit（）方法替换成 commitAllowingStateLoss()就行
         * 了，其效果是一样的。
         */
//        fragmentTransaction.commit();
        transaction.commitAllowingStateLoss();


        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.i(TAG, "destroyItem: "+position);
        Fragment fragment = FragmentFactory.fragments.get(position);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}
