/**
 * 
 */
package in.fanzy.whatsbreakfast.cp;

import in.fanzy.whatsbreakfast.data.FanzyData;
import android.content.Context;

/**
 * Generic content provider wrapper.
 * 
 * @author gautham
 * 
 */
public class CPWrapper {

	public static void upsert(FanzyData data, Context context) {
		// Need to have a proper id.
		if (data.id == 0) {
			return;
		}

		// See if one already available.
		FanzyData localData = FanzyData.getLocalData(data.getUri(),
				data.getClass(), context);

		if (null != localData) {
			// See the freshness.
			if (localData.needsUpdating(data)) {
				// If tournament was deleted, delete it.
				if (data.isDeleted) {
					delete(data, context);
				} else {
					update(data, context);
				}
			}
		} else {
			// Insert. If deleted. nothing to insert.
			if (!data.isDeleted) {
				insert(data, context);
			}
		}
	}

	private static void delete(FanzyData data, Context context) {
		context.getContentResolver().delete(data.getUri(), null, null);
	}

	private static void update(FanzyData data, Context context) {

		context.getContentResolver().update(data.getUri(), data.getContentValues(),
				null, null);
	}

	public static void insert(FanzyData data, Context context) {
		context.getContentResolver().insert(data.getUri(), data.getContentValues());
	}
}
