/**
 * 
 */
package in.fanzy.whatsbreakfast.routes;

import in.fanzy.androidmvc.data.RequestParams;
import in.fanzy.androidmvc.net.RequestDataFetcher;
import in.fanzy.androidmvc.view.UIBuilder;
import in.fanzy.whatsbreakfast.activities.ShowPageActivity;
import in.fanzy.whatsbreakfast.cp.FoodItemCP;
import in.fanzy.whatsbreakfast.data.FoodItem;
import in.fanzy.whatsbreakfast.data.FoodItemConstants;
import in.fanzy.whatsbreakfast.ui.MainUIBuilder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.database.Cursor;

/**
 * @author gautham
 * 
 */
public class MainRoute extends FanzyRoute {
	public static final String FOOD_ITEM = "FOOD_ITEM";

	public MainRoute() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.fanzy.androidmvc.router.AbstractRoute#createUIBuilder(android.app.Activity
	 * )
	 */
	@Override
	protected UIBuilder createUIBuilder(Activity activity) {
		return new MainUIBuilder(activity, getFoodType());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.router.AbstractRoute#getRequestParams()
	 */
	@Override
	public RequestParams getRequestParams() {
		return null;
	}

	/**
	 * Activity used for the route.
	 * 
	 * @return
	 */
	@Override
	public Class getActivityClass() {
		return ShowPageActivity.class;
	}

	@Override
	public void fetchData(boolean isRefreshAction, RequestDataFetcher dataFetcher)
			throws JSONException {

		String selection = FoodItemCP.KEY_FOOD_TYPE + " = ?";
		String[] selectionArgs = { String.valueOf(getFoodType()) };
		Cursor cursor = mContext.getContentResolver().query(FoodItemCP.getUri(),
				null, selection, selectionArgs, null);

		List<FoodItem> data = new ArrayList<FoodItem>();

		if (cursor != null && cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				FoodItem item = new FoodItem();
				item.parseCursor(cursor);
				data.add(item);
				cursor.moveToNext();
			}
		}

		mUIBuilder.onListDataObtained(data);
	}

	private int getFoodType() {
		return mRequestBundle.getInt(FOOD_ITEM, getCurrentFoodType());
	}

	private int getCurrentFoodType() {

		Time time = new Time(System.currentTimeMillis());

		int hour = time.getHours();

		int foodType = FoodItemConstants.FOOD_TYPE_BREAKFAST;
		if (hour >= 0 && hour <= 10) {
			// Breafast time.
			foodType = FoodItemConstants.FOOD_TYPE_BREAKFAST;
		} else if (hour >= 11 & hour <= 15) {
			foodType = FoodItemConstants.FOOD_TYPE_LUNCH;
		} else if (hour >= 16 && hour <= 18) {
			foodType = FoodItemConstants.FOOD_TYPE_SNACKS;
		} else {
			foodType = FoodItemConstants.FOOD_TYPE_DINNER;
		}

		return foodType;
	}
}
