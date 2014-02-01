/**
 * 
 */
package in.fanzy.whatsbreakfast.ui;

import in.fanzy.androidmvc.view.AbstractUIBuilder;
import in.fanzy.whatsbreakfast.R;
import in.fanzy.whatsbreakfast.data.FoodItem;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author gautham
 * 
 */
public class NewFoodItemUIBuilder extends AbstractUIBuilder {

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

		Button button = (Button) mActivity.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText edittext = (EditText) mActivity.findViewById(R.id.editText1);
				String name = edittext.getText().toString();
				EditText edittext2 = (EditText) mActivity.findViewById(R.id.editText2);
				String preparationTime = edittext2.getText().toString();

				FoodItem item = new FoodItem();
				item.name = name;
				item.preparationTime = Integer.valueOf(preparationTime);

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

	}

}
