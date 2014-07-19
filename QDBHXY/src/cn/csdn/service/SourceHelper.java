package cn.csdn.service;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SourceHelper extends SQLiteOpenHelper {
	static String name="source.db";
	static int version=1; 
	static String create="create table soucer(id integer primary key autoincrement,first varchar(20),second varchar(20),three varchar(20),four varchar(20),five varchar(20),six varchar(20),seven varchar(20),eight varchar(20),nine varchar(20),ten varchar(20))";
	Context context=null;
	public SourceHelper(Context context) {
		super(context, name, null, version);
		this.context=context;
	}
	//只在初次调用的时候会被执行
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(create);	
		String sql="insert into soucer(first,second,three,four,five,six,seven,eight,nine,ten) values(?,?,?,?,?,?,?,?,?,?)";
		Object obj[]=new Object[]{"无","无","无","无","无","无","无","无","无","无"};
		for(int i=0;i<5;i++){
			db.execSQL(sql,obj);
		}		
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
