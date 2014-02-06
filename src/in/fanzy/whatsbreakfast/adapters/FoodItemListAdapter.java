/**
 * 
 */
package in.fanzy.whatsbreakfast.adapters;

import in.fanzy.whatsbreakfast.R;
import in.fanzy.whatsbreakfast.data.FoodItem;
import in.fanzy.whatsbreakfast.routes.AppRouter;
import in.fanzy.whatsbreakfast.routes.NewFoodItemRoute;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author gau
 * 
 */
public class FoodItemListAdapter extends BaseAdapter {
	List<FoodItem> mDataList;
	private final Context mContext;
	private final LayoutInflater mInflater;
	

	public FoodItemListAdapter(Context context) {
		mContext = context;

		mDataList = new ArrayList<FoodItem>();
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mDataList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public FoodItem getItem(int position) {
		// TODO Auto-generated method stub
		return mDataList.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return getItem(position).id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.food_item_row, null);
		TextView nameText = (TextView) convertView.findViewById(R.id.textView1);
		Button editButton = (Button) convertView.findViewById(R.id.button2);
		Button deleteButton = (Button) convertView.findViewById(R.id.button1);
		final FoodItem item = getItem(position);
		nameText.setText(item.name);
		editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putLong(NewFoodItemRoute.FOOD_ITEM_ID, item.id);
				AppRouter.getInstance()
				.getRoute(AppRouter.ROUTE_NEW_FOOD_ITEM, null, mContext)
				.transitionTo();
			}
		});
		return convertView;
	}

	public void setListData(List<FoodItem> dataList) {
		mDataList = dataList;
		
		notifyDataSetChanged();
	}

}
