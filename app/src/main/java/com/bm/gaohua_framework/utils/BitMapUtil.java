package com.bm.gaohua_framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 
 * Copyright © 2014 高骅. All rights reserved.
 * 
 * @Description BitMap工具集合
 * @author 高骅
 * @date 2014-10-19 上午12:25:56
 */
public class BitMapUtil {
	/**
	 * 私有实例对象,不对外暴露
	 */
	private static BitMapUtil bUtil;

	private BitMapUtil() {

	}

	/**
	 * 
	 * @author 高骅
	 * @Description 得到BitMap工具类实例
	 * @return BitMapUtil
	 * @date 2014-11-10 下午1:41:10
	 */
	public static BitMapUtil getInstance() {
		if (bUtil == null) {
			bUtil = new BitMapUtil();
		}
		return bUtil;
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 压缩图片
	 * @return Bitmap
	 * @date 2014-10-19 上午12:25:02
	 */
	public Bitmap scaleImg(Bitmap bm, int newWidth, int newHeight) {
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 设置想要的大小
		int newWidth1 = newWidth;
		int newHeight1 = newHeight;
		// 计算缩放比例
		float scaleWidth = ((float) newWidth1) / width;
		float scaleHeight = ((float) newHeight1) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		matrix.postRotate(0);

		return Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 取得屏幕大小
	 * @return int[]
	 * @date 2014-10-19 上午12:34:33
	 */
	public int[] getScreenSize(Context context) {
		int[] size = new int[2];
		DisplayMetrics metrics = new DisplayMetrics();
		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
		size[0] = metrics.widthPixels;
		size[1] = metrics.heightPixels;
		return size;
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 设置圆角图片
	 * @return Bitmap
	 * @date 2014-10-19 上午12:36:52
	 */
	public Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		try {
			if (bitmap != null) {
				Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
				Canvas canvas = new Canvas(output);

				final int color = 0xff424242;
				final Paint paint = new Paint();
				final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
				final RectF rectF = new RectF(rect);
				final float roundPx = pixels;

				paint.setAntiAlias(true);
				canvas.drawARGB(0, 0, 0, 0);
				paint.setColor(color);
				canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

				paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
				canvas.drawBitmap(bitmap, rect, rect, paint);

				return output;
			}
		} catch (Exception e) {
		}

		return bitmap;
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 把图片切成方形
	 * @return Bitmap
	 * @date 2014-10-19 上午12:37:40
	 */
	public Bitmap cutSquareBitmap(Bitmap bitmap) {
		try {
			if (bitmap != null) {
				Bitmap result;
				int w = bitmap.getWidth();// 输入长方形宽
				int h = bitmap.getHeight();// 输入长方形高
				int nw;// 输出正方形宽
				if (w > h) {
					// 宽大于高
					nw = h;
					result = Bitmap.createBitmap(bitmap, (w - nw) / 2, 0, nw, nw);
				} else {
					// 高大于宽
					nw = w;
					result = Bitmap.createBitmap(bitmap, 0, (h - nw) / 2, nw, nw);
				}
				return result;
			}
		} catch (Exception e) {
		}
		return bitmap;
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 把图片转成圆形
	 * @return Bitmap
	 * @date 2014-10-19 上午12:38:22
	 */
	public Bitmap toRoundBitmap(Bitmap bitmap) {
		if (bitmap != null) {
			bitmap = cutSquareBitmap(bitmap);
			return toRoundCorner(bitmap, bitmap.getWidth() / 2);
		}
		return bitmap;
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 图片压缩处理
	 * @param pathName
	 *            本地图片路径
	 * @param reqWidth
	 *            手机屏幕宽度
	 * @return Bitmap
	 * @date 2014-10-29 下午1:45:20
	 */
	public Bitmap decodeSampledBitmapFromResource(String pathName, int reqWidth) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		FileInputStream is = null;
		try {
			is = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		BitmapFactory.decodeStream(is, null, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		// is只能用一次，第二次解析时为空
		InputStream stream = null;
		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

		Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
		try {
			if (is != null) {
				is.close();
			}
			if (stream != null) {
				stream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
		// 源图片的宽度
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (width > reqWidth) {
			// 计算出实际宽度和目标宽度的比率
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 把bipmap转成base64字符串
	 * @return String
	 * @date 2014-10-19 上午12:41:06
	 */
	public String bitmapToBase64(Bitmap bitmap) {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64Util.encode(bitmapBytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Package com.bm.east.activity
	 * @author 高骅
	 * @Description 图片合成并保存在SD卡上 bitmap1 原始图片1 bitmap2 原始图片2 合并后的图片为bitmap3
	 *              picName 图片名
	 * @return void
	 * @date 2015-4-3 下午3:30:19
	 */
	public void photoMergeToSdCard(Bitmap bitmap1, Bitmap bitmap2, String picName) {
		String name = picName + ".jpg";
		String pathString = Environment.getExternalStorageDirectory() + "/DCIM/Camera/";
		String cropFilePath = pathString + name;
		Bitmap bitmap3 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight() * 2, bitmap1.getConfig());
		Canvas canvas = new Canvas(bitmap3);
		canvas.drawBitmap(bitmap1, new Matrix(), null);
		canvas.drawBitmap(bitmap2, 0, bitmap3.getHeight() / 2, null);
		try {
			FileOutputStream stream = new FileOutputStream(cropFilePath);
			bitmap3.compress(Bitmap.CompressFormat.JPEG, 80, stream);
			try {
				stream.flush();
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	};

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 把两张图片合成一张 并返回位图对象
	 * @return Bitmap
	 * @date 2015-4-17 下午2:33:42
	 */
	public Bitmap photoMerge(Bitmap bitmap1, Bitmap bitmap2, String picName) {
		Bitmap bitmap3 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight() * 2, bitmap1.getConfig());
		Canvas canvas = new Canvas(bitmap3);
		canvas.drawBitmap(bitmap1, new Matrix(), null);
		canvas.drawBitmap(bitmap2, 0, bitmap3.getHeight() / 2, null);
        return bitmap3;
	};

	/**
	 * 
	 * @author 高骅
	 * @Description 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
	 * 
	 *              A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
	 * 
	 *              B.本地路径:url="file://mnt/sdcard/photo/image.png";
	 * 
	 *              C.支持的图片格式 ,png, jpg,bmp,gif等等
	 * 
	 * @return Bitmap
	 * @date 2015-1-22 下午5:08:36
	 */
	public Bitmap GetLocalOrNetBitmap(String url) {
		Bitmap bitmap = null;
		InputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new URL(url).openStream(), 2048);
			final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
			out = new BufferedOutputStream(dataStream, 2048);
			copy(in, out);
			out.flush();
			byte[] data = dataStream.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			data = null;
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void copy(InputStream in, OutputStream out) throws IOException {
		byte[] b = new byte[2048];
		int read;
		while ((read = in.read(b)) != -1) {
			out.write(b, 0, read);
		}
	}

}
