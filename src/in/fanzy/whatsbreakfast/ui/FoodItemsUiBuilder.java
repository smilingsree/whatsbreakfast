/**
 * 
 */
package in.fanzy.whatsbreakfast.ui;

import in.fanzy.androidmvc.data.AbstractData;
import in.fanzy.androidmvc.view.AbstractUIBuilder;
import in.fanzy.whatsbreakfast.R;
import in.fanzy.whatsbreakfast.adapters.FoodItemListAdapter;
import in.fanzy.whatsbreakfast.data.FoodItem;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author gau
 *
 */
public class FoodItemsUiBuilder extends AbstractUIBuilder {

	private ListView mListView;


	public FoodItemsUiBuilder(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see in.fanzy.androidmvc.view.UIBuilder#buildUI()
	 */
	@Override
	public void buildUI() {
		// TODO Auto-generated method stub
		mActivity.setContentView(R.layout.food_items);
		setTitle();
		mListView = (ListView) mActivity.findViewById(android.R.id.list);
		// mAdapter = new FoodItemListAdapter(mActivity);
	}
	
	

	@Override
	public <T extends AbstractData> void onListDataObtained(List<T> dataList) {
		String[] resultArray = new String[dataList.size()];
		
		int i = 0;
		for (T data : dataList) {
			FoodItem item = (FoodItem) data;
			resultArray[i] = item.name;
			i++;
			Log.d("Items", item.name);
		}
		
		ArrayAdapter adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, resultArray);
		mListView.setAdapter(adapter);
	}
	
	
	/* (non-Javadoc)
	 * @see in.fanzy.androidmvc.view.UIBuilder#setTitle()
	 */
	@Override
	public void setTitle() {
		// TODO Auto-generated method stub
		mActivity.setTitle("Breakfast Items");
	}

}
