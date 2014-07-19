package com.r8c.nfc_demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NfcDemoActivity extends Activity {
	//NFC����������
	private NfcAdapter nfcAdapter = null;
	//����nfc������Ϣ��Intent����
	private Intent nfcIntent = null;
	//������ͼ����
	private PendingIntent pi = null;
	//�˵�����޷���Ӧ�ʹ����Intent
	private IntentFilter tagDetected = null;
	//�ı��ؼ�������
	private TextView promt = null;
	//�Ƿ�֧��NFC���ܵı�ǩ
	private boolean isNFC_support = false;
	//����д��ɾ��ť�ؼ�������
	private Button readBtn, writeBtn, deleteBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfc_demo);
		//�ؼ��İ�
		promt = (TextView) findViewById(R.id.promt);
		readBtn = (Button) findViewById(R.id.read_btn);
		writeBtn = (Button) findViewById(R.id.write_btn);
		deleteBtn = (Button) findViewById(R.id.delete_btn);
		//���ı��ؼ���ֵ��ʼ�ı�
		promt.setText("�ȴ�RFID��ǩ");
		//��������д��ɾ��ť�ؼ�

		//��ʼ���豸֧��NFC����
		isNFC_support = true;
		//�õ�Ĭ��nfc������
		nfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());
		//��ʾ��Ϣ����
		String metaInfo = "";
		//�ж��豸�Ƿ�֧��NFC������NFC
		if (nfcAdapter == null) {
			metaInfo = "�豸��֧��NFC��";
			Toast.makeText(this, metaInfo, Toast.LENGTH_SHORT).show();
			isNFC_support = false;
		}
		if (!nfcAdapter.isEnabled()) {
			metaInfo = "����ϵͳ������������NFC���ܣ�";
			Toast.makeText(this, metaInfo, Toast.LENGTH_SHORT).show();
			isNFC_support = false;
		}

		if (isNFC_support == true) {
			this.init_NFC();
		} else {
			promt.setTextColor(Color.RED);
			promt.setText(metaInfo);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nfc_demo, menu);
		return true;
	}


	private String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();

		if (src == null || src.length <= 0) {
			return null;
		}
		char[] buffer = new char[2];
		for (int i = 0; i < src.length; i++) {
			buffer[0] = Character.toUpperCase(Character.forDigit(
					(src[i] >>> 4) & 0x0F, 16));
			buffer[1] = Character.toUpperCase(Character.forDigit(src[i] & 0x0F,
					16));
			System.out.println(buffer);
			stringBuilder.append(buffer);
		}
		return stringBuilder.toString();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		if (isNFC_support == true) {
			stopNFC_Listener();
		}

		if (isNFC_support == false)
			return;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if (isNFC_support == false)
			return;

		startNFC_Listener();

		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(this.getIntent()
				.getAction())) {
			// �����intent
			processIntent(this.getIntent());
		}
	}

	private Tag tagFromIntent;

	/**
	 * Parses the NDEF Message from the intent and prints to the TextView
	 */
	public void processIntent(Intent intent) {
		if (isNFC_support == false)
			return;

		// ȡ����װ��intent�е�TAG
		tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

		promt.setTextColor(Color.BLUE);
		String metaInfo = "";
		metaInfo += "��ƬID��" + bytesToHexString(tagFromIntent.getId()) + "\n";

		Log.e("ID",tagFromIntent.getId().toString());
//		Toast.makeText(this, "�ҵ���Ƭ", Toast.LENGTH_SHORT).show();

		
		promt.setText(metaInfo);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);

		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
			processIntent(intent);
		}
	}


	private void startNFC_Listener() {
		nfcAdapter.enableForegroundDispatch(this, pi,
				new IntentFilter[] { tagDetected }, null);
	}

	private void stopNFC_Listener() {
		nfcAdapter.disableForegroundDispatch(this);
	}

	private void init_NFC() {
		nfcIntent = new Intent(getApplicationContext(), NfcDemoActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		pi = PendingIntent.getActivity(this, 0, new Intent(this, getClass())
				.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
	}
}
