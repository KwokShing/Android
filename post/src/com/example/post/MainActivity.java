package com.example.post;

import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private EditText name;
	private EditText age;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name = (EditText) this.findViewById(R.id.editText1);
		age = (EditText) this.findViewById(R.id.editText2);
		Button button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					DefaultHttpClient client = new DefaultHttpClient();
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					NameValuePair pair1 = new BasicNameValuePair("name", name
							.getText().toString());
					NameValuePair pair2 = new BasicNameValuePair("age", age
							.getText().toString());
					list.add(pair1);
					list.add(pair2);
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
							list, "UTF-8");
					HttpPost post = new HttpPost("http://yunfeng.zju.edu.cn/");
					post.setEntity(entity);
					HttpResponse response = client.execute(post);

					if (response.getStatusLine().getStatusCode() == 200) {
						InputStream in = response.getEntity().getContent();
						// 接收服务器的数据，并在Toast上显示
						String str = readString(in);
						Toast.makeText(MainActivity.this, str,
								Toast.LENGTH_LONG).show();

					} /*
					 * else Toast.makeText(MainActivity.this, "POST提交失败",
					 * Toast.LENGTH_LONG).show();
					 */
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainActivity.this, "POST提交失败",
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	protected String readString(InputStream in) throws Exception {
		byte[] data = new byte[1024];
		int length = 0;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		while ((length = in.read(data)) != -1) {
			bout.write(data, 0, length);
		}
		return new String(bout.toByteArray(), "UTF-8");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
