package com.yc.fragmentfactorydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent intent1 = new Intent(context, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 非常重要，如果缺少的话，程序将在启动时报错
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //自启动APP（Activity）
            Log.i("aaaa", "onReceive: ");
            context.startActivity(intent1);
            //自启动服务（Service）
            //  context.startService(intent);
        }

    }

    public static void chatWithQQ(final Context context, String qq) {
        try {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq;
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(context, "手机QQ未安装或该版本不支持", Toast.LENGTH_SHORT).show();
        }

    }
}
