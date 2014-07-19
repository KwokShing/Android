package com.example.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.domain.Person;
import com.example.service.PersonService;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private ListView listview;
	private PersonService personService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		personService = new PersonService(this);

		listview = (ListView) this.findViewById(R.id.listView);
		show();
	}

	private void show() {
		// TODO Auto-generated method stub
		List<Person> persons = personService.getScrollData(0, 20);
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (Person person : persons) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("name", person.getName());
			item.put("phone", person.getPhone());
			item.put("amount", 1);
			item.put("id", person.getId());
			data.add(item);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
				new String[] { "name", "phone", "amount" }, new int[] {
						R.id.name, R.id.phone, R.id.amount });
		listview.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
