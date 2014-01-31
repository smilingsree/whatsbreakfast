/**
 * 
 */
package in.fanzy.whatsbreakfast.routes;

import in.fanzy.androidmvc.router.Router;

/**
 * All the routes defined here.
 * 
 * @author gautham
 * 
 */
public class AppRouter extends Router {

	static final Object sLock = new Object();
	static AppRouter sInstance;

	public static final String ROUTE_MAIN = "main";
	public static final String ROUTE_QUIZ = "quiz";
	public static final String ROUTE_LOGIN = "login";
	public static final String ROUTE_QUIZ_STATISTICS = "quiz.statistics";

	static {
		mRouteClassMap.put(ROUTE_MAIN, MainRoute.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.fanzy.androidmvc.router.Router#getDefaultRouteClass()
	 */
	@Override
	public Class getDefaultRouteClass() {
		return MainRoute.class;
	}

	// Create a static class.

	private AppRouter() {

	}

	public static String defaultRoute() {
		return ROUTE_MAIN;
	}

	public static AppRouter getInstance() {
		synchronized (sLock) {
			if (sInstance == null) {
				sInstance = new AppRouter();
			}
			return sInstance;
		}
	}
}
