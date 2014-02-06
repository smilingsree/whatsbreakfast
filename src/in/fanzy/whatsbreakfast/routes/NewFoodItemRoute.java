/**
 * 
 */
package in.fanzy.whatsbreakfast.routes;

import org.json.JSONException;

import in.fanzy.androidmvc.data.RequestParams;
import in.fanzy.androidmvc.net.RequestDataFetcher;
import in.fanzy.androidmvc.view.UIBuilder;
import in.fanzy.whatsbreakfast.cp.FoodItemCP;
import in.fanzy.whatsbreakfast.data.FoodItem;
import in.fanzy.whatsbreakfast.ui.NewFoodItemUIBuilder;
import android.app.Activity;
import android.database.Cursor;

/**
 * @author gautham
 * 
 */
public class NewFoodItemRoute extends FanzyRoute {

	public static final String FOOD_ITEM_ID = "id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.fanzy.androidmvc.router.AbstractRoute#createUIBuilder(android.app.Activity
	 * )
	 */
	@Override
	protected UIBuilder createUIBuilder(Activity activity) {
		// TODO Auto-generated method stub
		return new NewFoodItemUIBuilder(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.router.AbstractRoute#dataType()
	 */
	@Override
	protected int dataType() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	@Override
	public void fetchData(boolean isRefreshAction,
			RequestDataFetcher dataFetcher) throws JSONException {
		
		long id = mRequestBundle.getLong(FOOD_ITEM_ID);
		if (id != 0) {
			String selection = FoodItemCP.KEY_ID + " = " + id;
			Cursor c = mContext.getContentResolver().query(FoodItemCP.getUri(), null, selection, null, null);
			
			if (c!= null && c.moveToFirst()) {
				FoodItem item = new FoodItem();
				item.parseCursor(c);
				mUIBuilder.onDataObtained(item);
			}
			
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.router.AbstractRoute#getRequestParams()
	 */
	@Override
	public RequestParams getRequestParams() {
		// TODO Auto-generated method stub
		return null;
	}

}
