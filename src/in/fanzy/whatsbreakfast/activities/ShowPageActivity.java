/**
 * 
 */
package in.fanzy.whatsbreakfast.activities;

import in.fanzy.whatsbreakfast.routes.MainRoute;

import org.json.JSONException;

import android.os.Bundle;

/**
 * @author gau
 * 
 */
public class ShowPageActivity extends MainActivity {

	public void setFoodType(int foodType) {
		Bundle bundle = new Bundle();
		bundle.putInt(MainRoute.FOOD_ITEM, foodType);
		mRoute.setRequestBundle(bundle);
		try {
			mRoute.fetchData(true, null);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
