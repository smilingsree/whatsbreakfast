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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author gau
 *
 */
public class FoodItemsUiBuilder extends AbstractUIBuilder {

	private ListView mListView;
	private FoodItemListAdapter mAdapter;


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
		mAdapter = new FoodItemListAdapter(mActivity);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View v, int position,
					long id) {
				mAdapter.getItem(position);
			}
		});
		// mAdapter = new FoodItemListAdapter(mActivity);
	}
	
	

	@Override
	public <T extends AbstractData> void onListDataObtained(List<T> dataList) {
		String[] resultArray = new String[dataList.size()];
		
		mAdapter.setListData((List<FoodItem>) dataList);
		
		
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
