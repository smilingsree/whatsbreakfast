/**
 * 
 */
package in.fanzy.whatsbreakfast.routes;

import in.fanzy.androidmvc.data.RequestParams;
import in.fanzy.androidmvc.view.UIBuilder;
import in.fanzy.whatsbreakfast.ui.NewFoodItemUIBuilder;
import android.app.Activity;

/**
 * @author gautham
 * 
 */
public class NewFoodItemRoute extends FanzyRoute {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.fanzy.androidmvc.router.AbstractRoute#createUIBuilder(android.app.Activity
	 * )
	 */
	@Override
	protected UIBuilder createUIBuilder(Activity activity) {
		// TODO Auto-generated method stub
		return new NewFoodItemUIBuilder(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.router.AbstractRoute#dataType()
	 */
	@Override
	protected int dataType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.router.AbstractRoute#getRequestParams()
	 */
	@Override
	public RequestParams getRequestParams() {
		// TODO Auto-generated method stub
		return null;
	}

}
