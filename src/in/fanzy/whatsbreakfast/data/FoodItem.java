/**
 * 
 */
package in.fanzy.whatsbreakfast.data;

import in.fanzy.whatsbreakfast.cp.FoodItemCP;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * For each food item.
 * 
 * @author gautham
 * 
 */
public class FoodItem extends FanzyData {

	public String name;
	public int preparationTime;
	public int calories;
	public int foodType;
	public int spiceLevel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.data.InterfaceData#parseJSON(org.json.JSONObject)
	 */
	@Override
	public void parseJSON(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub

	}

	@Override
	public Uri getUri() {
		return FoodItemCP.getUri(id);
	}

	@Override
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(FoodItemCP.KEY_ID, id);
		values.put(FoodItemCP.KEY_NAME, name);
		values.put(FoodItemCP.KEY_PREP_TIME, preparationTime);
		values.put(FoodItemCP.KEY_CALORIES, calories);
		values.put(FoodItemCP.KEY_SPICE_LEVEL, spiceLevel);
		values.put(FoodItemCP.KEY_UPDATED, updated);
		return values;
	}

	@Override
	public void parseCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		id = cursor.getLong(cursor.getColumnIndex(FoodItemCP.KEY_ID));
		foodType = cursor.getInt(cursor.getColumnIndex(FoodItemCP.KEY_FOOD_TYPE));
		calories = cursor.getInt(cursor.getColumnIndex(FoodItemCP.KEY_CALORIES));
		spiceLevel = cursor.getInt(cursor
				.getColumnIndex(FoodItemCP.KEY_SPICE_LEVEL));
		name = cursor.getString(cursor.getColumnIndex(FoodItemCP.KEY_NAME));
	}
}
