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
			        webView.getSettings().setJavaScriptEnabled(true);//允许webkit执行js代码；  
			        //对象，插件名称  
			        //window.open()  
			        //document.write()  
			        //contact.xxx  
			        webView.addJavascriptInterface(new ContactPlugin(), "contact");//为webkit添加插件，即java类  
			        webView.loadUrl("file:///android_asset/index.html");//加载html文件  
			        //webView.loadUrl("http://192.168.1.100:8080/web/index.html");  
			    }  
			      
			    private final class ContactPlugin{  
			        public void getContacts(){  
			            List<Contact> contacts = service.getContacts();  
			            //OO的方法将对象的数据转成json字符串；  
			            try {  
			                JSONArray jsonArray = new JSONArray();//新建json数组  
			                for(Contact contact : contacts){  
			                    JSONObject item = new JSONObject();//新建json对象  
			                    item.put("id", contact.getId());  
			                    item.put("stdd", contact.getSkdd()+contact.getSksj());  
			                    item.put("sksj", contact.getSksj());  
			                    item.put("bjmc", contact.getSksj());  
			                    jsonArray.put(item);  
			                }  
			                String json = jsonArray.toString();//得到json字符串；  
			//              webView.loadUrl("javascript:show('[{""},{}]')");  
			                webView.loadUrl("javascript:show('"+ json +"')"); //调用js的show方法；  
			                  
			            } catch (JSONException e) {  
			                e.printStackTrace();  
			            }  
			        }  
			          
			           }  
		 

		
	}

