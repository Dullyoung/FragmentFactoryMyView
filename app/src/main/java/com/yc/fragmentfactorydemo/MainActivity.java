package com.yc.fragmentfactorydemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0);
        startActivity(new Intent(this,Main2Activity.class));

    }
    public void go(View view){
//        if (checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
//        }else {
//            Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+10086));
//            startActivity(intent);
//        }

      startActivity(new Intent(this,Main2Activity.class));
    }
}
