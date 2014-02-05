/**
 * 
 */
package in.fanzy.whatsbreakfast.ui;

import java.util.ArrayList;
import java.util.List;

import in.fanzy.androidmvc.view.AbstractUIBuilder;
import in.fanzy.whatsbreakfast.R;
import in.fanzy.whatsbreakfast.cp.CPWrapper;
import in.fanzy.whatsbreakfast.data.FoodItem;
import in.fanzy.whatsbreakfast.data.FoodItemConstants;
import in.fanzy.whatsbreakfast.routes.AppRouter;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * @author gautham
 * 
 */
public class NewFoodItemUIBuilder extends AbstractUIBuilder {

	private EditText mNameText;

	public NewFoodItemUIBuilder(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.view.UIBuilder#buildUI()
	 */
	@Override
	public void buildUI() {
		mActivity.setContentView(R.layout.new_food_item);
		setTitle();
  	final Spinner spinner = (Spinner) mActivity.findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		list.add(mActivity.getString(FoodItemConstants.STR_BREAKFAST));
		list.add(mActivity.getString(FoodItemConstants.STR_LUNCH));
		list.add(mActivity.getString(FoodItemConstants.STR_DINNER));
		list.add(mActivity.getString(FoodItemConstants.STR_SNACKS));
		list.add(mActivity.getString(FoodItemConstants.STR_SWEET));
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		mNameText = (EditText) mActivity.findViewById(R.id.editText1);
		Button button = (Button) mActivity.findViewById(R.id.button1);
		
		final Spinner categorySpinner = (Spinner) mActivity.findViewById(R.id.spinner2);
		List<String> categoryList = new ArrayList<String>();
		categoryList.add(mActivity.getString(FoodItemConstants.STR_VEGAN));
		categoryList.add(mActivity.getString(FoodItemConstants.STR_VEGETARIAN));
		categoryList.add(mActivity.getString(FoodItemConstants.STR_EGGETARIAN));
		categoryList.add(mActivity.getString(FoodItemConstants.STR_NON_VEGETARIAN));
		ArrayAdapter<String> categoryDataAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, categoryList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categorySpinner.setAdapter(categoryDataAdapter);
		categorySpinner.setSelection(1);
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String name = mNameText.getText().toString();

				
				FoodItem item = new FoodItem();
				item.name = name;
				int position = spinner.getSelectedItemPosition();
				int foodType = FoodItemConstants.FOOD_TYPE_BREAKFAST;
				int foodCategory = FoodItemConstants.FOOD_CATEGORY_VEGETARIAN;
				int categoryPosition = categorySpinner.getSelectedItemPosition();
				
				switch(position) {
				case 0:
					foodType = FoodItemConstants.FOOD_TYPE_BREAKFAST;
					break;
				case 1:
					foodType = FoodItemConstants.FOOD_TYPE_LUNCH;
					break;
				case 2:
					foodType = FoodItemConstants.FOOD_TYPE_DINNER;
					break;
				case 3:
					foodType = FoodItemConstants.FOOD_TYPE_SNACKS;
					break;
				default:
					foodType = FoodItemConstants.FOOD_TYPE_SWEET;
						
				}
				
				switch(categoryPosition) {
				case 0:
					foodCategory = FoodItemConstants.FOOD_CATEGORY_VEGAN;
					break;
				case 1:
					foodCategory = FoodItemConstants.FOOD_CATEGORY_VEGETARIAN;
					break;
				case 2:
					foodCategory = FoodItemConstants.FOOD_CATEGORY_EGGETARIAN;
					break;
				default:
					foodCategory = FoodItemConstants.FOOD_CATEGORY_NON_VEGETARIAN;
						
				}
				item.foodType = foodType;
			    item.foodCategory = foodCategory;
				CPWrapper.insert(item, mActivity);
				AppRouter.getInstance().getRoute(AppRouter.ROUTE_FOOD_ITEMS, null, mActivity).transitionTo();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.view.UIBuilder#setTitle()
	 */
	@Override
	public void setTitle() {
		// TODO Auto-generated method stub
		mActivity.setTitle("Breakfast Items");
	}

}
