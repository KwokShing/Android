package com.example.sharepreferences;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {
	private Context context;

	public PreferencesService(Context context) {
		super();
		this.context = context;
	}

	/**
	 * ±£´æ²ÎÊý
	 * 
	 * @param name
	 * @param age
	 */
	public void save(String name, int age) {
		SharedPreferences service = context.getSharedPreferences("preference",
				context.MODE_PRIVATE);
		Editor editor = service.edit();
		editor.putString("name", name);
		editor.putInt("age", age);
		editor.commit();

	}

	public Map<String, String> getPreference() {
		Map<String, String> params = new HashMap<String, String>();
		SharedPreferences service = context.getSharedPreferences("preference",
				context.MODE_PRIVATE);
		params.put("name", service.getString("name", ""));
		params.put("age", String.valueOf(service.getInt("age", 0)));
		return params;

	}
}
