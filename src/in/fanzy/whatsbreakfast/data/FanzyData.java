/**
 * 
 */
package in.fanzy.whatsbreakfast.data;

import in.fanzy.androidmvc.data.AbstractData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Base Data object.
 * 
 * @author gautham
 * 
 */
public abstract class FanzyData extends AbstractData {

	public boolean isDeleted = false;
	public long updated;

	public boolean needsUpdating(FanzyData data) {
		return data.updated > updated;
	}

	public static FanzyData getLocalData(Uri uri, Class className, Context context) {

		Cursor c = context.getContentResolver().query(uri, null, null, null, null);

		if (null != c && c.moveToFirst()) {

			try {
				FanzyData localData = (FanzyData) className.newInstance();
				localData.parseCursor(c);
				c.close();
				return localData;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (null != c)
			c.close();
		return null;
	}

	public abstract Uri getUri();

	public abstract ContentValues getContentValues();

	public abstract void parseCursor(Cursor cursor);
}
