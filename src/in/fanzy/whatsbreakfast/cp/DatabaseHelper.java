/**
 * 
 */
package in.fanzy.whatsbreakfast.cp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author gautham
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "whats_food";
	private static final int DATABASE_VERSION = 1;
	private static Object sLock = new Object();
	static DatabaseHelper sInstance;

	private DatabaseHelper(Context context) {
		// super(context, DATABASE_NAME, null, DATABASE_VERSION);
		super(context, DATABASE_NAME, getCursorFactory(), DATABASE_VERSION);
	}

	@SuppressWarnings("unused")
	private static CursorFactory getCursorFactory() {
		CursorFactory cf = new SQLiteCursorFactory(true);
		return cf;
	}

	public static DatabaseHelper getInstance(Context context) {
		synchronized (sLock) {
			if (sInstance == null) {
				sInstance = new DatabaseHelper(context.getApplicationContext());
			}

			return sInstance;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(FoodItemCP.DATABASE_CREATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
