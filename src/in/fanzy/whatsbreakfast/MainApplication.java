/**
 * 
 */
package in.fanzy.whatsbreakfast;

import in.fanzy.whatsbreakfast.utils.CustomLog;
import in.fanzy.whatsbreakfast.utils.LruBitmapCache;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Application object for the whole app.
 * 
 * @author gautham
 * 
 */
public class MainApplication extends Application {
	public static final boolean DEVELOPER_MODE = true;
	private static final String DEBUG_TAG = "MainApplication";

	private int mAppActivityCount;
	private static RequestQueue mRequestQueue;
	private static ImageLoader mImageLoader;
	/** Application context **/
	private static Context mContext;

	public boolean isAppActive() {
		return mAppActivityCount > 0;
	}

	public void addActivityToStack() {
		++mAppActivityCount;
	}

	public void removeActivityToStack() {
		--mAppActivityCount;
	}

	public static RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	public static ImageLoader getImageLoader() {
		return mImageLoader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		MainApplication.mContext = getApplicationContext();
		MainApplication.mRequestQueue = Volley.newRequestQueue(this);
		final int maxSize = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE))
				.getMemoryClass();
		// Use 1/16th of the available memory for this memory cache.
		final int cacheSize = 1024 * 1024 * maxSize / 16;
		CustomLog.d(DEBUG_TAG, "Cache size is " + cacheSize);
		mImageLoader = new ImageLoader(getRequestQueue(), new LruBitmapCache(
				cacheSize));
	}

	/**
	 * Returns application context.
	 * 
	 * @return application context
	 */
	public static Context getContext() {
		return mContext;
	}
}
