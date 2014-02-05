/**
 * 
 */
package in.fanzy.whatsbreakfast.ui;

import in.fanzy.androidmvc.view.AbstractUIBuilder;
import in.fanzy.whatsbreakfast.R;
import in.fanzy.whatsbreakfast.routes.AppRouter;

import java.util.Random;

import android.app.Activity;
import android.hardware.SensorManager;
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
	String[] breakfastList = { "Idli", "Dosa", "Poori", "Upma", "Chapathi",
			"Bisibelabath", "Pulav", "Pongal", "Ragi Mudda", "Akki Rotti",
			"Jowar Rotti", "Rava Idli", "Rava Dosa", "Kharam Rotti",
			"Avalakki", "Tomato Rice", "Puliyagare Rice", "Lemon Rice",
			"Semiya Upma", "Masala Upma", "Masala Avalakki", "Rice Avalakki",
			"Borugula Tiruvata", "Paddu", "Chapathi with Bhindi curry",
			"Chapathi & Vankay curry", "Ragi Rotti", "Maize Rotti",
			"Ragi Mudda with Aakura pappu", "Ragi mudda with ulavamalaka",
			"Chapathi with Dal fry", "Chapathi with Sagu",
			"Chapathi with majjigacharu", "Ragi Mudda perugu sasu",
			"Rava mudda with mango curry", "Vadapu with pappu",
			"Dosa pitakapappu", "Kharam Idli", "Kharam Dosa",
			"Kharam Chapathi", "Palak Chapathi",
			"Pulagam with Vankaya chatney", "Jonna Rotti vankaya kura",
			"Bilavoliga with peas curry", "Aloo Paratha", "Bread omlet",
			"Oats upma" };
	TextView mTextView;
	Button mChangeBreakfastButton;
    Button mNewbutton;
	public MainUIBuilder(Activity activity) {
		super(activity);
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
		String randomBreakfast = (breakfastList[new Random()
				.nextInt(breakfastList.length)]);
		mTextView.setText(randomBreakfast);
	}

	private void initializeUI() {

		mTextView = (TextView) mActivity.findViewById(R.id.textView1);
		mChangeBreakfastButton = (Button) mActivity.findViewById(R.id.button1);
		mNewbutton = (Button) mActivity.findViewById(R.id.button2);
        Button mListbutton = (Button) mActivity.findViewById(R.id.button3);
		SensorManager sensorManager = (SensorManager) mActivity.getSystemService(Activity.SENSOR_SERVICE);
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
		
		mListbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppRouter.getInstance().getRoute(AppRouter.ROUTE_FOOD_ITEMS, null, mActivity).transitionTo();
			}
		});
		
		mNewbutton.setOnClickListener(new OnClickListener() {
						
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AppRouter.getInstance().getRoute(AppRouter.ROUTE_NEW_FOOD_ITEM, null, mActivity).transitionTo();
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

	@Override
	public void hearShake() {
		// TODO Auto-generated method stub
		showRandomBreakfast();
	}

}
