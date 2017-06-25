package com.example.expanablelistviewpre.receiver;

import com.example.expanablelistviewpre.SecondActiivty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TiaoZhuanReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// 跳转 传值 intent.getStringExtra("name");
		Intent intent2 = new Intent(context, SecondActiivty.class);
		intent2.putExtra("haoyou", intent.getStringExtra("name"));
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
	}

}
