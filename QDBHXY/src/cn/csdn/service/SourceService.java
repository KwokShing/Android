package cn.csdn.service;

import cn.csdn.domain.SourceDao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SourceService {
	private SourceHelper helper;
	private static SQLiteDatabase sqldb=null;
	public SourceService(Context context){
		helper=new SourceHelper(context);
		if(sqldb!=null){
			sqldb.close();
		}
		sqldb=helper.getReadableDatabase();
	}
	//刚开始就插入五个数据
	public void insert() throws Throwable{
		String sql="insert into soucer(first,second,three,four,five,six,seven,eight,nine,ten) values(?,?,?,?,?,?,?,?,?,?)";
		Object obj[]=new Object[]{"无","无","无","无","无","无","无","无","无","无"};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	
	//查询
	public Cursor select(Integer id) throws Throwable{		
		String sql="select * from soucer where id=?";
		return sqldb.rawQuery(sql, new String[]{String.valueOf(id)});
	}
	//修改
	public void update(SourceDao source) throws Throwable{
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set first=?,second=?,three=?,four=?,five=?,six=?,seven=?,eight=?,nine=?,ten=? where id=?";
		Object obj[]=new Object[]{source.getFirst(),source.getSecond(),source.getThree(),source.getFour(),source.getFive(),source.getSix(),source.getSeven(),source.getEight(),source.getNine(),source.getTen(),source.getId()};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	//增加  没有用到
	public void add(SourceDao source) throws Throwable{
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="insert into soucer(first,second,three,four,five,six,seven,eight,nine,ten) values(?,?,?,?,?,?,?,?,?,?)";
		Object obj[]=new Object[]{source.getFirst(),source.getSecond(),source.getThree(),source.getFour(),source.getFive(),source.getSix(),source.getSeven(),source.getEight(),source.getNine(),source.getTen()};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	//清除今天所有
	public void delete(Integer id) throws Throwable{
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set first=?,second=?,three=?,four=?,five=?,six=?,seven=?,eight=?,nine=?,ten=? where id=?";
		String str="无";
		Object obj[]=new Object[]{str,str,str,str,str,str,str,str,str,str,id};
		sqldb.execSQL(sql,obj);				
	}
	//清除这一节
	public void clean(Integer id,String what){
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set "+what+"=? where id=?";
		Object obj[]=new Object[]{"无",id};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	//修改指定的一节
	public void updatewhat(Integer id,String what,String changewhat){
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set "+what+"=? where id=?";
		Object obj[]=new Object[]{changewhat,id};
		sqldb.execSQL(sql,obj);
	}
}
