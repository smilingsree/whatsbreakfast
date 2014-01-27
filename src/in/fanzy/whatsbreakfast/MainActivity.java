package in.fanzy.whatsbreakfast;

import java.util.Random;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.seismic.ShakeDetector;

public class MainActivity extends Activity implements ShakeDetector.Listener {
	
	String[] breakfastList = {
		"Idli", "Dosa", "Poori", "Upma", "Chapathi", "Bisibelabath", "Pulav", "Pongal",
		"Ragi Mudda", "Akki Rotti", "Jowar Rotti", "Rava Idli", "Rava Dosa", "Kharam Rotti",
		"Avalakki", "Tomato Rice", "Puliyagare Rice", "Lemon Rice", "Semiya Upma", "Masala Upma",
		"Masala Avalakki", "Rice Avalakki", "Borugula Tiruvata", "Paddu", "Chapathi with Bhindi curry",
		"Chapathi & Vankay curry", "Ragi Rotti", "Maize Rotti", "Ragi Mudda with Aakura pappu",
		"Ragi mudda with ulavamalaka", "Chapathi with Dal fry", "Chapathi with Sagu", "Chapathi with majjigacharu",
		"Ragi Mudda perugu sasu", "Rava mudda with mango curry", "Vadapu with pappu", "Dosa pitakapappu",
		"Kharam Idli", "Kharam Dosa", "Kharam Chapathi", "Palak Chapathi", "Pulagam with Vankaya chatney",
		"Jonna Rotti vankaya kura", "Bilavoliga with peas curry", "Aloo Paratha", "Bread omlet", "Oats upma"
	};
	private TextView mTextView;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextView = (TextView) findViewById(R.id.textView1);
		mButton = (Button) findViewById(R.id.button1);
		
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    ShakeDetector sd = new ShakeDetector(this);
	    sd.start(sensorManager);
		
		showRandomBreakfast();
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showRandomBreakfast();
			}
		});
	}
	
	public void hearShake() {
		showRandomBreakfast();
	}

	private void showRandomBreakfast() {
		String randomBreakfast = (breakfastList[new Random().nextInt(breakfastList.length)]);
		mTextView.setText(randomBreakfast);
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
