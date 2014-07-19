package com.example.netimage;

import com.example.service.ImageService;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText pathText;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pathText = (EditText) this.findViewById(R.id.editText1);
		imageView = (ImageView) this.findViewById(R.id.imageView1);
		Button button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String path = pathText.getText().toString();
				try {
					byte[] data = ImageService.getImage(path);
					Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
							data.length);
					imageView.setImageBitmap(bitmap);
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), R.string.failure, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
