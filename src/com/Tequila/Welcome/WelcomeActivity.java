package com.Tequila.Welcome;


import com.Tequila.Main.R;
import com.Tequila.datasource.DataSourceInstance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/*
 * @author WelcomeActivity
 * 
 * 功能：《无法开口的秘密》欢迎界面
 * 
 * */

public class WelcomeActivity extends Activity{
	
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		initView();
	}
	
	// 做一个2秒(2000毫秒)的延迟，之后做goIntroduceActivity()方法
	private void initView() {
		mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				goIntroduceActivity();
			}
		}, 1000);
	}

	// 跳转到IntroduceActivity(介绍界面)
	private void goIntroduceActivity() {
		
		String tag = DataSourceInstance.instance.getIntroduceTag();
		
		if ("0".equals(tag)) {
			DataSourceInstance.instance.updateIntroduceTag("1");
		} else if ("1".equals(tag)) {
			Intent intent = new Intent();
			intent.setClass(WelcomeActivity.this, IntroduceActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);  // 执行淡入淡出的跳转方式
			WelcomeActivity.this.finish();
		} else {
			Log.e("IntroduceActivity", "Tag Error: " + tag);
		}
	}
}