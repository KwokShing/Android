package xu.yujing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.csdn.service.SourceService;

public class SourceList extends Activity implements OnItemClickListener{
	private ListView list;
	private SourceService service;	
	private Intent intent;
	private String string[]={"first","second","three","four","five","six","seven","eight","nine","ten"};
	static int week=1;
	String time[]={"8:00-8:50","9:00-9:50","10:10-11:00","11:10-12:00","14:00-14:50","15:00-15:50","16:10-17:00","17:10-18:00","19:0-19:50","20:00-20:50"};
	TextView view;
	String xingqiji="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.list);
		findViews();
		intent=this.getIntent();
		week=intent.getIntExtra("week", 1);	
		xingqiji=intent.getStringExtra("weekwhat");
		view.setText(xingqiji);
		try {
			Cursor cursor=service.select(week);					
			cursor.moveToFirst();
			List<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
			/*HashMap<String, String>title = new HashMap<String, String>();
			title.put( "num" , "节数" );
			title.put( "source" , "课程 " );
			title.put( "address" , "地点 " );
			data.add(title);*/
			for(int i=1;i<=10;i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("num", "第"+i+"节");
				map.put("string",string[i-1]);
				map.put("source",cursor.getString(i));
				//Log.i("TAG",cursor.getString(i));
				map.put("time", time[i-1]);
				//Log.i("TAG",time[i-1]);
				data.add(map);//把所有数据都添加上去
			}
			//最后适配器
			SimpleAdapter adapter = new SimpleAdapter(SourceList.this ,
					data, R.layout.source_list , new String[]{ "num" , "source" ,
					"time" },
					new int []{R.id.text1, R.id.text2 , R.id.text3});
			list.setAdapter(adapter);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		list.setOnItemClickListener(this);
	}
	public void findViews() {
		view=(TextView) findViewById(R.id.title);
		list=(ListView) findViewById(R.id.list);
		service=new SourceService(this);
	}
	int changeid=1;
	String str="first";
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		ListView listView = (ListView)parent;
		HashMap<String, String> itemData = (HashMap<String,
		String>)listView.getItemAtPosition(position);
		//Log.i("TAG",id+"");	
		str=itemData.get("string");
		//Log.i("TAG",str);
		changeid=(int) id;
		AlertDialog.Builder builder=new AlertDialog.Builder(SourceList.this);
		builder.setTitle("操作");
		builder.setMessage("课程的信息："+itemData.get("source")+"课   "+itemData.get("num")+"   时间："+itemData.get("time"));
		builder.setPositiveButton("修改", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {				
				changeid();
			}});
		builder.setNeutralButton("清空", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				cleanwhat(week,str);
			}});
		builder.setNegativeButton("清除今天所有", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				cleanid(week);
			}});
		builder.create().show();
	}
	//修改
	public void changeid(){
		Intent intentchange=new Intent(SourceList.this,SourceChange.class);
		intentchange.putExtra("week", week);
		intentchange.putExtra("what", str);//first星期几
		intentchange.putExtra("weekwhat", xingqiji);
		this.finish();
		startActivity(intentchange);
	}
	//清除今天所有
	public void cleanid(Integer id){
		try {
			service.delete(week);
			Intent intentthis=new Intent(SourceList.this,SourceList.class);
			intentthis.putExtra("week", id);
			intentthis.putExtra("weekwhat", xingqiji);
			this.finish();
			startActivity(intentthis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}	
	//清除这一节
	public void cleanwhat(Integer id,String what){
		service.clean(id, what);
		Intent intentthis=new Intent(SourceList.this,SourceList.class);
		intentthis.putExtra("week", id);
		intentthis.putExtra("weekwhat", xingqiji);
		this.finish();
		startActivity(intentthis);
	}
}
