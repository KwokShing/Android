package com.chenww.camera.ui;

/**
 * �������յ�Activity
 * @author Chenww
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class CameraActivity extends Activity {

	private SurfaceView mySurfaceView;
	private SurfaceHolder myHolder;
	private Camera myCamera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// ��title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ȫ��
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// ���ò���
		setContentView(R.layout.activity_camera);

		Log.d("Demo", "oncreate");

		// ��ʼ��surface
		initSurface();

		new Thread(new Runnable() {
			@Override
			public void run() {
				// ��ʼ��camera���Խ�����
					initCamera();
			}
		}).start();

	}

	// ��ʼ��surface
	@SuppressWarnings("deprecation")
	private void initSurface() {
		// ��ʼ��surfaceview
		mySurfaceView = (SurfaceView) findViewById(R.id.camera_surfaceview);

		// ��ʼ��surfaceholder
		myHolder = mySurfaceView.getHolder();
		myHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	// ��ʼ������ͷ
	private void initCamera() {

		if (openFacingFrontCamera()) {
			Log.d("Demo", "openCameraSuccess");
			// ���жԽ�
			autoFocus();
		} else {
			Log.d("Demo", "openCameraFailed");
		}

	}

	// �Խ�������
	private void autoFocus() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// �Զ��Խ�
		myCamera.autoFocus(myAutoFocus);

		// �Խ�������
		myCamera.takePicture(null, null, myPicCallback);
	}

	private boolean openFacingFrontCamera() {
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		// ������������ͷ
		for (int camIdx = 0, cameraCount = Camera.getNumberOfCameras(); camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo);
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				try {
					myCamera = Camera.open(camIdx);
				} catch (RuntimeException e) {
					return false;
				}
			}
		}

		try {
			myCamera.setPreviewDisplay(myHolder);
		} catch (IOException e) {
			e.printStackTrace();
			myCamera.stopPreview();
			myCamera.release();
			myCamera = null;
		}

		myCamera.startPreview();

		return true;
	}

	// �Զ��Խ��ص�����(��ʵ��)
	private AutoFocusCallback myAutoFocus = new AutoFocusCallback() {
		@Override
		public void onAutoFocus(boolean success, Camera camera) {
		}
	};

	// ���ճɹ��ص�����
	private PictureCallback myPicCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			CameraActivity.this.finish();

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			Matrix matrix = new Matrix();
			matrix.preRotate(90);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);

			// ����������ͼƬ�ļ�
			String FilePath = Environment.getExternalStorageDirectory()
					.toString() + "/test";
			String FileName = System.currentTimeMillis() + ".jpg";
			File pictureFile = new File(FilePath, FileName);
			try {
				FileOutputStream fos = new FileOutputStream(pictureFile);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				fos.close();
			} catch (Exception error) {
				Log.d("Demo", "������Ƭʧ��" + error.toString());
				error.printStackTrace();
				myCamera.stopPreview();
				myCamera.release();
				myCamera = null;
			} finally {

				Log.d("Demo", "��ȡ��Ƭ�ɹ�");
				myCamera.stopPreview();
				myCamera.release();
				myCamera = null;
			}
		}
	};
}
