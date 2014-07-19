package com.example.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHepler extends SQLiteOpenHelper {

	public DBOpenHepler(Context context) {
		super(context, "database", null, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {// 数据库第一次创建的时候被调用
		// TODO Auto-generated method stub
		db.execSQL("create table test(id integer primary key autoincrement, name varchar(20))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//数据库版本号发生变更的时候调用
		// TODO Auto-generated method stub
		db.execSQL("alter table test add phone varchar(12) null");

	}

}
