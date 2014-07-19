package xu.yujing;



import cn.csdn.service.SourceHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SmActivity extends Activity implements OnClickListener{
	Button button1,button2,button3,button4,button5;
	Intent intent;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        //创建数据库
        SourceHelper helper=new SourceHelper(this);
		helper.getReadableDatabase();
        findViews();
    }
	private void findViews() {
		button1=(Button) findViewById(R.id.button1);
		button2=(Button) findViewById(R.id.button2);
		button3=(Button) findViewById(R.id.button3);
		button4=(Button) findViewById(R.id.button4);
		button5=(Button) findViewById(R.id.button5);
		
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			intent=new Intent(SmActivity.this,SourceList.class);
			intent.putExtra("week", 1);
			intent.putExtra("weekwhat", "星期一");
			startActivity(intent);
			break;
		case R.id.button2:
			intent=new Intent(SmActivity.this,SourceList.class);
			intent.putExtra("week", 2);
			intent.putExtra("weekwhat", "星期二");
			startActivity(intent);
			break;
		case R.id.button3:
			intent=new Intent(SmActivity.this,SourceList.class);
			intent.putExtra("week", 3);
			intent.putExtra("weekwhat", "星期三");
			startActivity(intent);
			break;
		case R.id.button4:
			intent=new Intent(SmActivity.this,SourceList.class);
			intent.putExtra("week", 4);
			intent.putExtra("weekwhat", "星期四");
			startActivity(intent);
			break;
		case R.id.button5:
			intent=new Intent(SmActivity.this,SourceList.class);
			intent.putExtra("week", 5);
			intent.putExtra("weekwhat", "星期五");
			startActivity(intent);
			break;
		}
	}
}