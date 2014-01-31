/**
 * 
 */
package in.fanzy.whatsbreakfast.activities;

import in.fanzy.whatsbreakfast.MainApplication;
import android.support.v7.app.ActionBarActivity;

/**
 * Base Activity for all activities.
 * 
 * @author gautham
 * 
 */
public class BaseFragmentActivity extends ActionBarActivity {

	private static final String DEBUG_TAG = "BaseFragmentActivity";

	@Override
	protected void onPause() {

		((MainApplication) getApplicationContext()).removeActivityToStack();

		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();

		((MainApplication) getApplicationContext()).addActivityToStack();
	}
}
