package com.Tequila.datasource;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Tequila.datasource.db.CustomDBHelper;

public class DataSourceInstance {
	
	public static final DataSourceInstance instance = new DataSourceInstance();
	
	private DataSourceInstance() {
	}
	
	// 取出IntroduceTag表的tag值，如果为0为初始化，1为跳过介绍界面
	public String getIntroduceTag() {
		String tag = "";
		
		SQLiteDatabase db = CustomDBHelper.instance.getReadableDatabase();
		
		Cursor c = db.rawQuery("select tag from IntroduceTag" , null);
		
		while (c.moveToNext()) {
			tag = c.getString(c.getColumnIndex("Tag"));
		}
		
		return tag;
	}
	
	public void updateIntroduceTag(String tag) {
		
		SQLiteDatabase db = CustomDBHelper.instance.getWritableDatabase();
		db.beginTransaction();
		
		db.execSQL("update IntroduceTag set Tag = ?", new String[] { tag });
		
		db.setTransactionSuccessful();
		db.endTransaction();
	}
}