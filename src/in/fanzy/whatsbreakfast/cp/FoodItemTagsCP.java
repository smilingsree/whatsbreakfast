package in.fanzy.whatsbreakfast.cp;

import android.net.Uri;

public class FoodItemTagsCP extends AbstractContentProvider {
	public static final String AUTHORITY= "in.fanzy.whatsbreakfask.cp.FoodItemTagsCP";
    
	public static final String TABLE_NAME = "food_item_tags";
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_NAME;
	}

	@Override
	public Uri getContentUri() {
		// TODO Auto-generated method stub
		return getUri();
		
	}

	public static Uri getUri() {
		// TODO Auto-generated method stub
		return Uri.parse("content://" + AUTHORITY + "/" +STR_CONTENTS);
	}

	@Override
	public Uri getUriForId(long id) {
		// TODO Auto-generated method stub
		return getUri(id);
		
	}

	public static Uri getUri(long id) {
		// TODO Auto-generated method stub
		return Uri.parse("content://" + AUTHORITY + "/" + STR_CONTENTS + "/" + id);
	}
}
