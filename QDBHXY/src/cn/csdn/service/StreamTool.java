package cn.csdn.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {

	/**
	 * ���������ж�ȡ��������String�ַ���
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static String read(InputStream inStream) throws Exception{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len=inStream.read(buffer))!=-1) {
			outputStream.write(buffer, 0, len);
		}
		inStream.close();
		
		return new String(outputStream.toByteArray());
	}

}