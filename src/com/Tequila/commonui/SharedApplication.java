package com.Tequila.commonui;

import java.util.Locale;
import java.util.TimeZone;

import android.content.Context;

import com.Tequila.datasource.db.CustomDBHelper;
import com.mobilepos.MainApp;

/*
 * @author SharedApplication
 * 
 * 程序安装成功执行的初始化类
 * 
 * */

public class SharedApplication extends MainApp{
	
	public static Context context;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		context = this;
		new CustomDBHelper(context).getReadableDatabase().close();
		
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));		// 设置默认时区的，设置的是上海的时区。
		Locale.setDefault(Locale.CHINA);								// 设置默认语言环境 	
	}
}
