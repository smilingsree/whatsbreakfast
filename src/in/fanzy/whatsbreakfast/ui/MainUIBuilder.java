/**
 * 
 */
package in.fanzy.whatsbreakfast.ui;

import in.fanzy.androidmvc.data.AbstractData;
import in.fanzy.androidmvc.view.AbstractUIBuilder;
import in.fanzy.whatsbreakfast.R;
import in.fanzy.whatsbreakfast.activities.ShowPageActivity;
import in.fanzy.whatsbreakfast.data.FoodItem;
import in.fanzy.whatsbreakfast.data.FoodItemConstants;
import in.fanzy.whatsbreakfast.routes.AppRouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.seismic.ShakeDetector;
import com.squareup.seismic.ShakeDetector.Listener;

/**
 * @author gautham
 * 
 */
public class MainUIBuilder extends AbstractUIBuilder implements Listener {
	// TODO Auto-generated method stub
	ShowPageActivity mActivity;
	String[] breakfastList = { "Idli", "Dosa", "Poori", "Upma", "Chapathi",
			"Bisibelabath", "Pulav", "Pongal", "Ragi Mudda", "Akki Rotti",
			"Jowar Rotti", "Rava Idli", "Rava Dosa", "Kharam Rotti", "Avalakki",
			"Tomato Rice", "Puliyagare Rice", "Lemon Rice", "Semiya Upma",
			"Masala Upma", "Masala Avalakki", "Rice Avalakki", "Borugula Tiruvata",
			"Paddu", "Chapathi with Bhindi curry", "Chapathi & Vankay curry",
			"Ragi Rotti", "Maize Rotti", "Ragi Mudda with Aakura pappu",
			"Ragi mudda with ulavamalaka", "Chapathi with Dal fry",
			"Chapathi with Sagu", "Chapathi with majjigacharu",
			"Ragi Mudda perugu sasu", "Rava mudda with mango curry",
			"Vadapu with pappu", "Dosa pitakapappu", "Kharam Idli", "Kharam Dosa",
			"Kharam Chapathi", "Palak Chapathi", "Pulagam with Vankaya chatney",
			"Jonna Rotti vankaya kura", "Bilavoliga with peas curry", "Aloo Paratha",
			"Bread omlet", "Oats upma" };
	Button mChangeBreakfastButton;
	Button mNewbutton;
	private List<FoodItem> mListData;
	private int mFoodType;
	private TextView mFoodText;
	private TextView mHeadingText;
	private Button mBreakfastBtn;
	private Button mLunchBtn;
	private Button mDinnerBtn;
	private Button mSnacksBtn;

	public MainUIBuilder(Activity activity, int foodType) {
		super(activity);
		mListData = new ArrayList<FoodItem>();
		mFoodType = foodType;

		mActivity = (ShowPageActivity) activity;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.view.UIBuilder#buildUI()
	 */
	@Override
	public void buildUI() {

		// TODO Auto-generated method stub
		mActivity.setContentView(R.layout.activity_main);
		initializeUI();

	}

	private void showRandomBreakfast() {

		// String randomBreakfast = (breakfastList[new Random()
		// .nextInt(breakfastList.length)]);
		// mTextView.setText(randomBreakfast);
		if (mListData.size() > 0) {
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(mListData.size());
			FoodItem item = mListData.get(index);

			mFoodText.setText(item.name);
		}
	}

	@Override
	public <T extends AbstractData> void onListDataObtained(List<T> data) {
		mListData = (List<FoodItem>) data;

		showRandomBreakfast();
	}

	private void initializeUI() {

		mFoodText = (TextView) mActivity.findViewById(R.id.food_text);
		mChangeBreakfastButton = (Button) mActivity.findViewById(R.id.change_btn);
		mHeadingText = (TextView) mActivity.findViewById(R.id.heading_text);

		setChangeFoodTypeButtons();

		// Set the button and heading according to food type.
		setUIForFoodType();

		SensorManager sensorManager = (SensorManager) mActivity
				.getSystemService(Activity.SENSOR_SERVICE);
		ShakeDetector sd = new ShakeDetector(this);
		sd.start(sensorManager);

		showRandomBreakfast();
		mChangeBreakfastButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showRandomBreakfast();
			}
		});
	}

	private void setChangeFoodTypeButtons() {
		mBreakfastBtn = (Button) mActivity.findViewById(R.id.show_breakfast);
		mLunchBtn = (Button) mActivity.findViewById(R.id.show_lunch);
		mDinnerBtn = (Button) mActivity.findViewById(R.id.show_dinner);
		mSnacksBtn = (Button) mActivity.findViewById(R.id.show_snacks);

		mBreakfastBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeFoodType(FoodItemConstants.FOOD_TYPE_BREAKFAST);
			}
		});
		mLunchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeFoodType(FoodItemConstants.FOOD_TYPE_LUNCH);
			}
		});

		mDinnerBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeFoodType(FoodItemConstants.FOOD_TYPE_DINNER);
			}
		});

		mSnacksBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				changeFoodType(FoodItemConstants.FOOD_TYPE_SNACKS);
			}

		});
	}

	private void changeFoodType(int foodType) {
		mActivity.setFoodType(foodType);
		mFoodType = foodType;
		setUIForFoodType();
	}

	private void setUIForFoodType() {
		switch (mFoodType) {
		case FoodItemConstants.FOOD_TYPE_BREAKFAST:
			mHeadingText.setText("Breakfast Time. How about");
			mChangeBreakfastButton.setText("Change Breakfast");
			break;
		case FoodItemConstants.FOOD_TYPE_LUNCH:
			mHeadingText.setText("Lunch Time. How about");
			mChangeBreakfastButton.setText("Change Lunch");
			break;
		case FoodItemConstants.FOOD_TYPE_DINNER:
			mHeadingText.setText("Dinner Time. How about");
			mChangeBreakfastButton.setText("Change Dinner");
			break;
		case FoodItemConstants.FOOD_TYPE_SNACKS:
			mHeadingText.setText("Snacks Time!! How about");
			mChangeBreakfastButton.setText("Change Snack");
			break;
		case FoodItemConstants.FOOD_TYPE_SWEET:
			mHeadingText.setText("Yummy Sweets. How about");
			mChangeBreakfastButton.setText("Change Sweet");
			break;
		}
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

	@Override
	public void onActivityCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = mActivity.getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
	}

	@Override
	public boolean onActivityOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_list:
			AppRouter.getInstance()
					.getRoute(AppRouter.ROUTE_FOOD_ITEMS, null, mActivity).transitionTo();
			return true;
		case R.id.action_new:
			AppRouter.getInstance()
					.getRoute(AppRouter.ROUTE_NEW_FOOD_ITEM, null, mActivity)
					.transitionTo();
			return true;
		}

		return false;
	}

	@Override
	public void hearShake() {
		// TODO Auto-generated method stub
		showRandomBreakfast();
	}

}
