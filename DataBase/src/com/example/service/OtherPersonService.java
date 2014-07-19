package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OtherPersonService {
	private DBOpenHepler dbOpenHelper;

	public OtherPersonService(Context context) {
		super();
		this.dbOpenHelper = new DBOpenHepler(context);
	}

	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.getName());
		values.put("phone", person.getPhone()); //nullÖµ×Ö¶Î
		db.insert("test", null, values);

		db.execSQL("insert into test (name,phone) values(?,?)", new Object[] {
				person.getName(), person.getPhone() });
	}

	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from test where id=?", new Object[] { id });
	}

	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("update test set name=?,phone=? where id=?", new Object[] {
				person.getName(), person.getPhone(), person.getId() });
	}

	public Person find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from test where id=?",
				new String[] { id.toString() });
		if (cursor.moveToFirst()) {
			int personid = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			return new Person(personid, name, phone);
		}
		cursor.close();
		return null;
	}

	public List<Person> getScrollData(int offset, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from test order by id limit ?,?",
				new String[] { String.valueOf(offset),
						String.valueOf(maxResult) });
		while (cursor.moveToNext()) {
			int personid = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			persons.add(new Person(personid, name, phone));
		}
		cursor.close();
		return persons;

	}

	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from test", null);
		cursor.moveToNext();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
}
