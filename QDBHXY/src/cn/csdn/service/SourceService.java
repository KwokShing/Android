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
	//�տ�ʼ�Ͳ����������
	public void insert() throws Throwable{
		String sql="insert into soucer(first,second,three,four,five,six,seven,eight,nine,ten) values(?,?,?,?,?,?,?,?,?,?)";
		Object obj[]=new Object[]{"��","��","��","��","��","��","��","��","��","��"};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	
	//��ѯ
	public Cursor select(Integer id) throws Throwable{		
		String sql="select * from soucer where id=?";
		return sqldb.rawQuery(sql, new String[]{String.valueOf(id)});
	}
	//�޸�
	public void update(SourceDao source) throws Throwable{
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set first=?,second=?,three=?,four=?,five=?,six=?,seven=?,eight=?,nine=?,ten=? where id=?";
		Object obj[]=new Object[]{source.getFirst(),source.getSecond(),source.getThree(),source.getFour(),source.getFive(),source.getSix(),source.getSeven(),source.getEight(),source.getNine(),source.getTen(),source.getId()};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	//����  û���õ�
	public void add(SourceDao source) throws Throwable{
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="insert into soucer(first,second,three,four,five,six,seven,eight,nine,ten) values(?,?,?,?,?,?,?,?,?,?)";
		Object obj[]=new Object[]{source.getFirst(),source.getSecond(),source.getThree(),source.getFour(),source.getFive(),source.getSix(),source.getSeven(),source.getEight(),source.getNine(),source.getTen()};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	//�����������
	public void delete(Integer id) throws Throwable{
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set first=?,second=?,three=?,four=?,five=?,six=?,seven=?,eight=?,nine=?,ten=? where id=?";
		String str="��";
		Object obj[]=new Object[]{str,str,str,str,str,str,str,str,str,str,id};
		sqldb.execSQL(sql,obj);				
	}
	//�����һ��
	public void clean(Integer id,String what){
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set "+what+"=? where id=?";
		Object obj[]=new Object[]{"��",id};
		sqldb.execSQL(sql,obj);
		sqldb.close();
	}
	//�޸�ָ����һ��
	public void updatewhat(Integer id,String what,String changewhat){
		//SQLiteDatabase sqldb=helper.getReadableDatabase();
		String sql="update soucer set "+what+"=? where id=?";
		Object obj[]=new Object[]{changewhat,id};
		sqldb.execSQL(sql,obj);
	}
}
