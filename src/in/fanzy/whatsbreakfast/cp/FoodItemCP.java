/**
 * 
 */
package in.fanzy.whatsbreakfast.cp;

import android.net.Uri;

/**
 * 
 * Content Provider for a Food Item.
 * 
 * @author smilingsree
 * 
 */
public class FoodItemCP extends AbstractContentProvider {
	public static final String AUTHORITY = "in.fanzy.whatsbreakfast.cp.FoodItemCP";

	// private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	// + "/" + STR_CONTENTS);

	// Table name.
	public static final String TABLE_NAME = "food_items";

	public static final String KEY_NAME = "name";
	public static final String KEY_PREP_TIME = "prep_time";
	public static final String KEY_CALORIES = "calories";
	public static final String KEY_FOOD_TYPE = "food_type";
	public static final String KEY_SPICE_LEVEL = "spice_level";
	public static final String KEY_USER_ID = "user_id";

	/*
	 * SQL FOR CREATION.
	 */
	public static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME
			+ " TEXT NOT NULL, " + KEY_PREP_TIME + " INTEGER NOT NULL, "
			+ KEY_CALORIES + " INTEGER DEFAULT 0, " + KEY_FOOD_TYPE
			+ " INTEGER NOT NULL, " + KEY_SPICE_LEVEL + " INTEGER DEFAULT 0, "
			+ KEY_USER_ID + " INTEGER NOT NULL, " + KEY_UPDATED
			+ " INTEGER NOT NULL )";

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.whatsbreakfast.cp.AbstractContentProvider#getTableName()
	 */
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.whatsbreakfast.cp.AbstractContentProvider#getContentUri()
	 */
	@Override
	public Uri getContentUri() {
		return getUri();
	}

	public static Uri getUri() {
		return Uri.parse("content://" + AUTHORITY + "/" + STR_CONTENTS);
	}

	public static Uri getUri(long id) {
		return Uri.parse("content://" + AUTHORITY + "/" + STR_CONTENT + "/" + id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.whatsbreakfast.cp.AbstractContentProvider#getUriForId(long)
	 */
	@Override
	public Uri getUriForId(long id) {
		return getUri(id);
	}
}
