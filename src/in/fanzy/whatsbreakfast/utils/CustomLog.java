package in.fanzy.whatsbreakfast.utils;

import in.fanzy.whatsbreakfast.MainApplication;
import android.util.Log;

public class CustomLog {

	public static void d(String tag, String msg) {
		if (MainApplication.DEVELOPER_MODE) {
			Log.d(tag, msg);
		}
	}

}
