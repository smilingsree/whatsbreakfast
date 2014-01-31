/**
 * 
 */
package in.fanzy.whatsbreakfast.data;

import in.fanzy.androidmvc.data.AbstractData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * For each food item.
 * 
 * @author gautham
 * 
 */
public class FoodItem extends AbstractData {

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

}
