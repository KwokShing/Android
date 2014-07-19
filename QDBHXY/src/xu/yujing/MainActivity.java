package xu.yujing;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
// �ر�ע��Ҫ���з��������Ȩ��
public class MainActivity extends Activity implements OnClickListener{
	
	private final String ADDRESS = "http://218.57.111.210:86/JWManager_Server/login?name=";
	private final String ADDRESS1 ="&pass=";
	private final String ADDRESS2 ="&key=";
	private String ADDRESSENTER="";
	//����http����
	private URLConnection urlConnect;
	//������������ַ
	private URL url;
	
	AutoCompleteTextView cardnumauto;
	EditText psw;
	Button login;
	CheckBox repsw;
	SharedPreferences sp;
	String cardNumStr;
	String passwordStr;
	RadioButton r1,r2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cardnumauto = (AutoCompleteTextView) findViewById(R.id.cardNumAuto);
        psw = (EditText) findViewById(R.id.psw);
        login = (Button) findViewById(R.id.logBT);
        r1 = (RadioButton) findViewById(R.id.teacher);
        r2 = (RadioButton) findViewById(R.id.student);
        sp = this.getSharedPreferences("passwordFile", MODE_PRIVATE);
        repsw = (CheckBox) findViewById(R.id.savePasswordCB);
        repsw.setChecked(true);//Ĭ�ϼ�ס����
        cardnumauto.setThreshold(1);//����1����ĸ�Ϳ�ʼ�Զ���ʾ
        psw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//�������룬��ʾ����
        cardnumauto.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				String[] allUserName = new String[sp.getAll().size()];// sp.getAll().size()���ص����ж��ٸ���ֵ��
				//���ص����ж��ٸ���ֵ��
				allUserName = sp.getAll().keySet().toArray(new String[0]);
				//sp.getAll()����һ��hash map
				//keySet()�õ����� a set of the keys
				// hash map ���� key-value���
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,
			        	allUserName);

				cardnumauto.setAdapter(adapter);//����������
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				psw.setText(sp.getString(cardnumauto.getText().toString(), ""));
				//�Զ���������
			}
		});
     //��½
        login.setOnClickListener(this);			
    }

	@Override
	public void onClick(View v) {
		DataInputStream dis = null;
		cardNumStr = cardnumauto.getText().toString();
		passwordStr = psw.getText().toString();
		String username = cardNumStr;
		String userpass = psw.getText().toString();
		if((cardNumStr.equals("1"))&&(passwordStr.equals("1"))){
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, coursejosn.class);
			startActivity(intent);
			
		}
		else if(r1.isChecked()){
		if (v == login) {
			
//			
//		
		Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT);	
		}
		}
		else if(r2.isChecked()){
			if (v == login) {
				
				try {
					ADDRESSENTER=ADDRESS+username+ADDRESS1+userpass+ADDRESS2+"stu";
					//ʵ����ַ
					url = new URL(ADDRESSENTER);
					//ʵ��Http����
					urlConnect = url.openConnection();
					//��ȡ������
					dis = new DataInputStream(urlConnect.getInputStream());
					//��ȡ�����
					//DataOutputStream dos = new DataOutputStream(urlConnect.getOutputStream());
					//��ȡ���������ص�����
					int temp = 0;
					ByteArrayBuffer baff = new ByteArrayBuffer(1000);
					while ((temp = dis.read()) != -1) {
						baff.append(temp);
						
					}
					//�����������ص���Ϣ��ʾ���ı�
					
					//tv.setText(EncodingUtils.getString(baff.toByteArray(), "UFT-8"));
					if ("success".equals(EncodingUtils.getString(baff.toByteArray(), "UFT-8"))) {		
						if(repsw.isChecked()){
							sp.edit().putString(cardNumStr, passwordStr).commit();
						}
						Toast.makeText(MainActivity.this, "��½�ɹ������ڶ�ȡ�û����ݣ�����", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent();
						intent.setClass(MainActivity.this, SourceList.class);
						startActivity(intent);
					}else{
							Toast.makeText(MainActivity.this, "�����������������", Toast.LENGTH_SHORT).show();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (dis != null)
							dis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}