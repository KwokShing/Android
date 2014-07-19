package com.example.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.utils.StreamTool;

public class ImageService {

	public static byte[] getImage(String path) throws Exception {
		// TODO Auto-generated method stub
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();		
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5000);
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			return StreamTool.read(inStream);
		}
		return null;
	}

}
