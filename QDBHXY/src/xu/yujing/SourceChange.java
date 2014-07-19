package xu.yujing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.csdn.service.SourceService;

public class SourceChange extends Activity implements android.view.View.OnClickListener{
	private SourceService service;	
	private EditText edit;
	private Button save,cancel;
	private String changewhat="无";
	private String what="";
	private String xingqiji;
	private int id=1;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.change);
		Intent intent=this.getIntent();
		id=intent.getIntExtra("week", 1);//id号
		what=intent.getStringExtra("what");//first,second
		xingqiji=intent.getStringExtra("weekwhat");
		findViews();		
	}
	public void findViews() {
		service=new SourceService(this); 
		edit=(EditText) findViewById(R.id.what);	
		save=(Button) findViewById(R.id.save);
		cancel=(Button) findViewById(R.id.cancel);
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.save:
			changewhat=edit.getText().toString();
			Log.i("TAG",changewhat);
			service.updatewhat(id, what,changewhat);
			Toast.makeText(SourceChange.this, "修改成功", Toast.LENGTH_LONG).show();
			Intent intent=new Intent(SourceChange.this,SourceList.class);
			intent.putExtra("week", id);
			intent.putExtra("what", what);
			intent.putExtra("weekwhat", xingqiji);
			this.finish();
			startActivity(intent);
			break;
		case R.id.cancel:
			Intent intent1=new Intent(SourceChange.this,SourceList.class);
			intent1.putExtra("week", id);
			intent1.putExtra("what", what);
			intent1.putExtra("weekwhat", xingqiji);
			this.finish();
			startActivity(intent1);
			break;
		}
	}
}
