package com.example.sharepreferences;

import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText nameText;
	private EditText ageText;
	private PreferencesService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nameText = (EditText) this.findViewById(R.id.editText1);
		ageText = (EditText) this.findViewById(R.id.editText2);
		service = new PreferencesService(this);
		Map<String, String> params = service.getPreference();
		nameText.setText(params.get("name"));
		ageText.setText(params.get("age"));
	}

	public void save(View v) {

		String name = nameText.getText().toString();
		String age = ageText.getText().toString();
		service.save(name, Integer.valueOf(age));
		// Toast.makeText(MainActivity.this, R.string.success, 1).show();
		Toast.makeText(getApplicationContext(), R.string.success,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
