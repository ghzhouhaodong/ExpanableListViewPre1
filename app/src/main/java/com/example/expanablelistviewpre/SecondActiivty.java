package com.example.expanablelistviewpre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActiivty extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView textView = new TextView(SecondActiivty.this);
		Intent intent = getIntent();
		textView.setText("来自---"+intent.getStringExtra("haoyou"));
		setContentView(textView);
	}

}
