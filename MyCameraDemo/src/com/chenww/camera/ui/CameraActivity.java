package com.chenww.camera.ui;

/**
 * 用来拍照的Activity
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

		// 无title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// 设置布局
		setContentView(R.layout.activity_camera);

		Log.d("Demo", "oncreate");

		// 初始化surface
		initSurface();

		new Thread(new Runnable() {
			@Override
			public void run() {
				// 初始化camera并对焦拍照
					initCamera();
			}
		}).start();

	}

	// 初始化surface
	@SuppressWarnings("deprecation")
	private void initSurface() {
		// 初始化surfaceview
		mySurfaceView = (SurfaceView) findViewById(R.id.camera_surfaceview);

		// 初始化surfaceholder
		myHolder = mySurfaceView.getHolder();
		myHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	// 初始化摄像头
	private void initCamera() {

		if (openFacingFrontCamera()) {
			Log.d("Demo", "openCameraSuccess");
			// 进行对焦
			autoFocus();
		} else {
			Log.d("Demo", "openCameraFailed");
		}

	}

	// 对焦并拍照
	private void autoFocus() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 自动对焦
		myCamera.autoFocus(myAutoFocus);

		// 对焦后拍照
		myCamera.takePicture(null, null, myPicCallback);
	}

	private boolean openFacingFrontCamera() {
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		// 开启后置摄像头
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

	// 自动对焦回调函数(空实现)
	private AutoFocusCallback myAutoFocus = new AutoFocusCallback() {
		@Override
		public void onAutoFocus(boolean success, Camera camera) {
		}
	};

	// 拍照成功回调函数
	private PictureCallback myPicCallback = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			CameraActivity.this.finish();

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			Matrix matrix = new Matrix();
			matrix.preRotate(90);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);

			// 创建并保存图片文件
			String FilePath = Environment.getExternalStorageDirectory()
					.toString() + "/test";
			String FileName = System.currentTimeMillis() + ".jpg";
			File pictureFile = new File(FilePath, FileName);
			try {
				FileOutputStream fos = new FileOutputStream(pictureFile);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				fos.close();
			} catch (Exception error) {
				Log.d("Demo", "保存照片失败" + error.toString());
				error.printStackTrace();
				myCamera.stopPreview();
				myCamera.release();
				myCamera = null;
			} finally {

				Log.d("Demo", "获取照片成功");
				myCamera.stopPreview();
				myCamera.release();
				myCamera = null;
			}
		}
	};
}
