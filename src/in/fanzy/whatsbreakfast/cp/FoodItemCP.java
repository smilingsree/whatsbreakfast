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
