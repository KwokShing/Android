package com.android.intent_result;

import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);

		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FirstActivity.this,
						SecondActivity.class);
				intent.putExtra("text1", "能看到吗？");
				intent.putExtra("text2", "哈哈");
				startActivityForResult(intent, 123);
				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 123) {
			if (resultCode == 123444) {
				String result = data.getStringExtra("resultString");
				Toast.makeText(FirstActivity.this, "返回值：" + result,
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
