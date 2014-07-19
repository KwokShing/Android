package com.example.sms;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	// private EditText button;
	private EditText numberText;
	private EditText contestText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) this.findViewById(R.id.button1);
		numberText = (EditText) this.findViewById(R.id.editText1);
		contestText = (EditText) this.findViewById(R.id.editText2);
		button.setOnClickListener(new ButtonClickListener());
	}

	private final class ButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			String number = numberText.getText().toString();
			String contest = contestText.getText().toString();
			SmsManager manager = SmsManager.getDefault();
			ArrayList<String> texts = manager.divideMessage(contest);
			for (String text : texts) {
				manager.sendTextMessage(number, null, text, null, null);
			}
			Toast.makeText(MainActivity.this, R.string.success, Toast.LENGTH_LONG).show();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
