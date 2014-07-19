package com.example.netactivity2;

import com.example.service.WebService;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NetActivity extends Activity {
	private EditText picaddress;
	private Button button;
	private ImageView imageView;
	private static final String TAG = "bitmap";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_net);
		button = (Button) this.findViewById(R.id.button);
		imageView = (ImageView) this.findViewById(R.id.image);
		picaddress = (EditText) this.findViewById(R.id.imageaddress);
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				String address = picaddress.getText().toString();

				byte[] data = WebService.getImage(address); // 得到图片的输入流

				// 二进制数据生成位图

				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				Log.i(TAG, bitmap.toString());
				imageView.setImageBitmap(bitmap);

				// error

				// e.printStackTrace();
				// Log.i("TAG", String.valueOf(bit));

				/*
				 * Toast.makeText(NetActivity.this, R.string.error,
				 * Toast.LENGTH_LONG).show();
				 */

			}
		});
	}
}