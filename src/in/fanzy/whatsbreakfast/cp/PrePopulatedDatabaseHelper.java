/**
 * 
 */
package in.fanzy.whatsbreakfast.cp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author gautham
 * 
 */
public class PrePopulatedDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "whats_food";
	private static final int DATABASE_VERSION = 1;
	private static Object sLock = new Object();
	static PrePopulatedDatabaseHelper sInstance;
	final Context mContext;
	private SQLiteDatabase mDatabase;

	private static String DB_PATH = "/data/data/in.fanzy.whatsbreakfast/databases/";

	private PrePopulatedDatabaseHelper(Context context) {
		// super(context, DATABASE_NAME, null, DATABASE_VERSION);
		super(context, DATABASE_NAME, getCursorFactory(), DATABASE_VERSION);
		mContext = context.getApplicationContext();
	}

	@SuppressWarnings("unused")
	private static CursorFactory getCursorFactory() {
		CursorFactory cf = new SQLiteCursorFactory(true);
		return cf;
	}

	public void createDatabase()  {
		boolean dbExist = checkDataBase();

		if (!dbExist) {
			// Database does not exist. Create on.
			getReadableDatabase();
			try {
				copyDataBase();

			} catch (IOException e) {
				throw new Error("Error copying database");
			}

		}
	}

	public SQLiteDatabase openDataBase() throws SQLException {

		// Open the database
		if (mDatabase == null) {
			String myPath = DB_PATH + DATABASE_NAME;
			createDatabase();
			mDatabase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		}
		
		return mDatabase;
	}

	@Override
	public synchronized void close() {

		if (mDatabase != null)
			mDatabase.close();

		super.close();

	}

	public static PrePopulatedDatabaseHelper getInstance(Context context) {
		synchronized (sLock) {
			if (sInstance == null) {
				sInstance = new PrePopulatedDatabaseHelper(
						context.getApplicationContext());
			}

			return sInstance;
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = mContext.getAssets().open(DATABASE_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DATABASE_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

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
//		db.execSQL(FoodItemCP.DATABASE_CREATE);
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
