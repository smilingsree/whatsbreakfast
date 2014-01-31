/**
 * 
 */
package in.fanzy.whatsbreakfast.routes;

import in.fanzy.androidmvc.router.AbstractRoute;
import in.fanzy.whatsbreakfast.activities.MainActivity;

/**
 * Base Class for all the routes in this app.
 * 
 * @author gautham
 * 
 */
public abstract class FanzyRoute extends AbstractRoute {
	/**
	 * Activity used for the route.
	 * 
	 * @return
	 */
	@Override
	public Class getActivityClass() {
		return MainActivity.class;
	}
}
