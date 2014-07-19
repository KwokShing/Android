package com.example.test;

import java.util.List;

import android.util.Log;
import com.example.domain.Person;
import com.example.service.DBOpenHepler;
import com.example.service.PersonService;

import android.test.AndroidTestCase;

public class PersonServiceTest extends AndroidTestCase {

	private static final String TAG = "PersonServiceTest";

	public void testCreateDB() throws Exception {
		DBOpenHepler dbOpenHelper = new DBOpenHepler(getContext());
		dbOpenHelper.getWritableDatabase();
	}

	public void testSave() throws Exception {
		PersonService service = new PersonService(this.getContext());
		for (int i = 0; i < 20; i++) {
			Person person = new Person("zxc" + i, "13" + i);
			service.save(person);
		}
	}

	public void testDelete() throws Exception {
		PersonService service = new PersonService(this.getContext());
		service.delete(1);
	}

	public void testUpdate() throws Exception {
		PersonService service = new PersonService(this.getContext());
		Person person = service.find(1);
		person.setName("qwe");
		service.update(person);
	}

	public void testFind() throws Exception {
		PersonService service = new PersonService(this.getContext());
		Person person = service.find(1);
		Log.i(TAG, person.toString());
	}

	public void testScrollData() throws Exception {
		PersonService service = new PersonService(this.getContext());
		List<Person> persons = service.getScrollData(0, 5);
		for (Person person : persons)
			Log.i(TAG, person.toString());
	}

	public void testCount() throws Exception {
		PersonService service = new PersonService(this.getContext());
		long count = service.getCount();
		Log.i(TAG, String.valueOf(count));
	}

}
