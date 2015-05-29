package com.bm.gaohua_framework.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.constants.Constants;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.bm.gaohua_framework.utils.BitMapUtil;
import com.bm.gaohua_framework.utils.LogUtils;
import com.bm.gaohua_framework.utils.ToastUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 照片选择
 * @author 高骅 
 * @date 2015-3-19 下午8:36:31
 */
public class ChoosePhotoActivity extends Activity implements IBaseActivity {

	/**
	 * 显示图片
	 */
	private ImageView iv_photo;
	/**
	 * 剪裁返回的bitmap
	 */
	private Bitmap photo;
	/**
	 * 照片file对象
	 */
	private File photoFile;
	/**
	 * 照片名字
	 */
	private static String photoFileName = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_choose_photo);
		initView();
	}

	public void OnClick(View v) {
		selectAvatarDialog();
	}

	@Override
	public void close(View v) {
		this.finish();
	}

	@Override
	public void initView() {
		iv_photo = (ImageView) this.findViewById(R.id.iv_photo);
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.activity
	 * @author 高骅
	 * @Description 头像选择界面
	 * @return void
	 * @date 2015-3-19 下午8:51:13
	 */
	private void selectAvatarDialog() {
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.dialog_selectphot);
		TextView tv_fromphoto = (TextView) window.findViewById(R.id.tv_fromphoto);
		TextView tv_fromcamera = (TextView) window.findViewById(R.id.tv_fromcamera);
		tv_fromphoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intentx = new Intent(Intent.ACTION_PICK, null);
				intentx.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intentx, Constants.PHOTO_PICKED_REQUEST_DATA);
				alertDialog.dismiss();
			}
		});
		tv_fromcamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String sdStatus = Environment.getExternalStorageState();
				// 检测sd卡是否可用
				if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
					ToastUtil.getInterface().showToast(ChoosePhotoActivity.this, "请检查SD卡是否可用", 1);
				}
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				photoFileName = "GaoHua" + "EditPicture" + ".jpg";
				// 判断存储照片的文件夹目录是否存在，如果不存在就创建该目录
				File pathdir = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
				if (!pathdir.exists()) {
					pathdir.mkdirs();
				}
				if (photoFile == null) {
					photoFile = new File(pathdir, photoFileName);
				}
				// 指定调用相机拍照后的照片存储的路径
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
				startActivityForResult(intent, Constants.CAMERA_REQUEST_DATA);
				alertDialog.cancel();
			}
		});
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			/**
			 * 相机拍照
			 */
			case Constants.CAMERA_REQUEST_DATA:
				String fileName = "/sdcard/DCIM/Camera/" + photoFileName;
				File temp = new File(fileName);
				startPhotoZoom(Uri.fromFile(temp));
				break;
			/**
			 * 相册选择
			 */
			case Constants.PHOTO_PICKED_REQUEST_DATA:
				startPhotoZoom(data.getData());
				break;
			/**
			 * 剪裁返回
			 */
			case Constants.PIC_EDIT_REQUEST_DATA:
				Bundle extras = data.getExtras();
				if (extras != null) {
					photo = extras.getParcelable("data");
					String sdStatus = Environment.getExternalStorageState();
					// 检测sd卡是否可用
					if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
						ToastUtil.getInterface().showToast(ChoosePhotoActivity.this, "请检查SD卡是否可用", 1);
					}
					String name = "GaoHua" + "CropEditPicture" + ".jpg";
					String pathString = Environment.getExternalStorageDirectory() + "/DCIM/Camera/";
					String cropFilePath = pathString + name;

					try {
						File fl = new File(pathString);
						if (!fl.exists()) {
							fl.mkdirs();
						}
						FileOutputStream cropFileOutputStream = new FileOutputStream(cropFilePath);
						photo.compress(Bitmap.CompressFormat.JPEG, 100, cropFileOutputStream);// 把数据写入文件
						cropFileOutputStream.flush();
						cropFileOutputStream.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				iv_photo.setImageBitmap(BitMapUtil.getInstance().toRoundBitmap(photo));
				break;
			}
		}
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 图片剪裁
	 * @return void
	 * @date 2015-2-4 下午1:15:29
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, Constants.PIC_EDIT_REQUEST_DATA);
	}
}
