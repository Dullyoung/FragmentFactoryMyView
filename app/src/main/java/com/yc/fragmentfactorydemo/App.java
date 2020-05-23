package com.yc.fragmentfactorydemo;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

public class App extends Application {
    public App() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyReceiver myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(myReceiver,intentFilter);
    }
}
