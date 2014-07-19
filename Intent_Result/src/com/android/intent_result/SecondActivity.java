package com.android.intent_result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		final Intent intent = getIntent();
		String getText1 = intent.getStringExtra("text1");
		String getText2 = intent.getStringExtra("text2");
		TextView textView = (TextView) findViewById(R.id.text);
		textView.setText("Activity1传过来的值："+getText1 +getText2);
		
		
		
		Button backButton = (Button) findViewById(R.id.btn_back);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText editText = (EditText) findViewById(R.id.edit);
				String data = editText.getText().toString();
				
				intent.putExtra("resultString", data);
				setResult(123444, intent);
				finish();
			}
		});
	}

}
