package com.lzt.tiptextviews.global;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Global {
	
	public static Context mContext;
	public static int mScreenWidth;
	public static int mScreenHeight;
	public static float mDensity;
	public static float mScaledDensity;
	//public static int MAXMEMONRY;
	
	//public static int systemType;
	public static void init(Context context) {
		if(mContext!=null)return;
		mContext = context;
		initScreenSize();
		//MAXMEMONRY = (int) (Runtime.getRuntime().maxMemory() / 1024);
	}

	public static void initScreenSize() {
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
		mDensity = dm.density;
		mScaledDensity = dm.scaledDensity;
	}


	public static int dp2px(int dp) {
		return (int) (dp * mDensity + 0.5f) ;
	}
	public static float dp2px(float dp) {
		return dp * mDensity + 0.5f ;
	}

	public static int sp2px(int sp){
		return (int)(sp*mScaledDensity+0.5f);
	}
	public static float sp2px(float sp){
		return sp*mScaledDensity+0.5f;
	}


}














