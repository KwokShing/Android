package xu.yujing;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.csdn.domain.Contact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class coursejosn extends Activity{
	private WebView webView;
	private ContactService service;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			        setContentView(R.layout.coursejosn);  
			          
			         service = new ContactService();  
			        webView = (WebView) this.findViewById(R.id.webView);  
			        webView.getSettings().setJavaScriptEnabled(true);//����webkitִ��js���룻  
			        //���󣬲������  
			        //window.open()  
			        //document.write()  
			        //contact.xxx  
			        webView.addJavascriptInterface(new ContactPlugin(), "contact");//Ϊwebkit��Ӳ������java��  
			        webView.loadUrl("file:///android_asset/index.html");//����html�ļ�  
			        //webView.loadUrl("http://192.168.1.100:8080/web/index.html");  
			    }  
			      
			    private final class ContactPlugin{  
			        public void getContacts(){  
			            List<Contact> contacts = service.getContacts();  
			            //OO�ķ��������������ת��json�ַ�����  
			            try {  
			                JSONArray jsonArray = new JSONArray();//�½�json����  
			                for(Contact contact : contacts){  
			                    JSONObject item = new JSONObject();//�½�json����  
			                    item.put("id", contact.getId());  
			                    item.put("stdd", contact.getSkdd()+contact.getSksj());  
			                    item.put("sksj", contact.getSksj());  
			                    item.put("bjmc", contact.getSksj());  
			                    jsonArray.put(item);  
			                }  
			                String json = jsonArray.toString();//�õ�json�ַ�����  
			//              webView.loadUrl("javascript:show('[{""},{}]')");  
			                webView.loadUrl("javascript:show('"+ json +"')"); //����js��show������  
			                  
			            } catch (JSONException e) {  
			                e.printStackTrace();  
			            }  
			        }  
			          
			           }  
		 

		
	}

