package cn.csdn.test;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;
import cn.csdn.domain.SourceDao;
import cn.csdn.service.SourceHelper;
import cn.csdn.service.SourceService;

public class SourceTest extends AndroidTestCase {
	public void createtable(){
		SourceHelper helper=new SourceHelper(this.getContext());
		helper.getReadableDatabase();
	}
	public void add() throws Throwable{
		SourceService sService=new SourceService(this.getContext());
		SourceDao source=new SourceDao();
		/*source.setFirst("英语0");
		source.setSecond("英语1");
		source.setThree("英语2");
		source.setFour("英语3");
		source.setFive("英语4");
		source.setSix("英语5");
		source.setSeven("英语6");
		source.setEight("英语7");
		source.setNine("英语8");
		source.setTen("英语9");*/
		source.setFirst("历史0");
		source.setSecond("历史1");
		source.setThree("历史2");
		source.setFour("历史3");
		source.setFive("历史4");
		source.setSix("历史5");
		source.setSeven("历史6");
		source.setEight("历史7");
		source.setNine("历史8");
		source.setTen("历史9");
		sService.add(source);
		
	}
	public void select() throws Throwable{
		SourceService sService=new SourceService(this.getContext());
		//SourceDao source=sService.select(1);
		Cursor cursor=sService.select(1);
		cursor.moveToFirst();
		Log.i("TAG",cursor.getString(1));
	}
}
