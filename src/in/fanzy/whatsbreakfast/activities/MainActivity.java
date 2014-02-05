package in.fanzy.whatsbreakfast.activities;

import in.fanzy.androidmvc.constants.Constants;
import in.fanzy.androidmvc.router.Route;
import in.fanzy.androidmvc.view.UIBuilder;
import in.fanzy.whatsbreakfast.routes.AppRouter;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseFragmentActivity {

	protected Route mRoute;
	protected UIBuilder mUIBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get the parameters.
		loadParameters();

		// Ready the UI.
		initializeUI();

		// Fetch the required Data.
		fetchRequiredData(false);
	}

	private void fetchRequiredData(boolean b) {
		try {
			mRoute.fetchData(b, null);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		mUIBuilder.onActivityPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mUIBuilder.onActivityResume();
	}

	@Override
	protected void onStop() {
		mUIBuilder.onActivityStop();
		super.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mUIBuilder.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mUIBuilder.onActivityStart();
	}

	private void initializeUI() {
		mUIBuilder = mRoute.getUIBuilder(this);
		mUIBuilder.buildUI();
	}

	private void loadParameters() {
		Intent i = getIntent();
		AppRouter router = AppRouter.getInstance();
		Bundle bundle = i.getBundleExtra(Constants.STR_REQUEST_BUNDLE);
		String route = null;
		if (bundle != null) {
			route = bundle.getString(Constants.STR_ROUTE);
		}
		mRoute = router.getRoute(route, bundle, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		mUIBuilder.onActivityCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// If the UI builder handles it, return true;
		if (mUIBuilder.onActivityOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
