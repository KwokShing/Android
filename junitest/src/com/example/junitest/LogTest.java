package com.example.junitest;

import android.test.AndroidTestCase;
import android.util.Log;

public class LogTest extends AndroidTestCase {
	private static final String TAG="LogTest";
	public void testOutLog() throws Throwable{
		Log.i(TAG, "info");
	}
	public void testOutLog2() throws Throwable{
		System.out.println("zxcv");
	}

}
