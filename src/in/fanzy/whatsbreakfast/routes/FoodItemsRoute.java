package in.fanzy.whatsbreakfast.routes;

import in.fanzy.androidmvc.data.RequestParams;
import in.fanzy.androidmvc.net.RequestDataFetcher;
import in.fanzy.androidmvc.view.UIBuilder;
import in.fanzy.whatsbreakfast.cp.FoodItemCP;
import in.fanzy.whatsbreakfast.data.FoodItem;
import in.fanzy.whatsbreakfast.ui.FoodItemsUiBuilder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.database.Cursor;

public class FoodItemsRoute extends FanzyRoute {

	@Override
	protected UIBuilder createUIBuilder(Activity activity) {
		// TODO Auto-generated method stub
		return new FoodItemsUiBuilder(activity);
	}
	
	

	@Override
	protected int dataType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RequestParams getRequestParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fetchData(boolean isRefreshAction,
			RequestDataFetcher dataFetcher) throws JSONException {
		Cursor cursor = mContext.getContentResolver().query(
				FoodItemCP.getUri(), null, null, null, null);

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

}
