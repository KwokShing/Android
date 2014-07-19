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
		/*source.setFirst("Ӣ��0");
		source.setSecond("Ӣ��1");
		source.setThree("Ӣ��2");
		source.setFour("Ӣ��3");
		source.setFive("Ӣ��4");
		source.setSix("Ӣ��5");
		source.setSeven("Ӣ��6");
		source.setEight("Ӣ��7");
		source.setNine("Ӣ��8");
		source.setTen("Ӣ��9");*/
		source.setFirst("��ʷ0");
		source.setSecond("��ʷ1");
		source.setThree("��ʷ2");
		source.setFour("��ʷ3");
		source.setFive("��ʷ4");
		source.setSix("��ʷ5");
		source.setSeven("��ʷ6");
		source.setEight("��ʷ7");
		source.setNine("��ʷ8");
		source.setTen("��ʷ9");
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
