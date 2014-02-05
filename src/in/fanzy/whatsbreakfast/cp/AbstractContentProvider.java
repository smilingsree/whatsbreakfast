/**
 * 
 */
package in.fanzy.whatsbreakfast.cp;

import in.fanzy.whatsbreakfast.AppConstants;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Abstract content provider to have most common content provider classes.
 * 
 * @author smilingsree
 * 
 */
public abstract class AbstractContentProvider extends ContentProvider {
	// Creates a UriMatcher object.
	protected static final UriMatcher sUriMatcher;

	// DB helper
	protected PrePopulatedDatabaseHelper mDbHelper;

	public static final String STR_CONTENT = "content";
	public static final String STR_CONTENTS = "contents";

	public static final String KEY_UPDATED = "updated";

	static final int FOOD_ITEMS_CODE = 1;
	static final int FOOD_ITEM_CODE = 2;

	// Key ID.
	public static final String KEY_ID = BaseColumns._ID;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		// sUriMatcher.addURI(TournamentCP.AUTHORITY, STR_CONTENTS,
		// TOURNAMENTS_CODE);
		// sUriMatcher.addURI(TournamentCP.AUTHORITY, STR_CONTENT + "/*",
		// TOURNAMENT_CODE);

	}

	// Tablename of child content providers.
	public abstract String getTableName();

	public abstract Uri getContentUri();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#onCreate()
	 */
	@Override
	public boolean onCreate() {
		mDbHelper = PrePopulatedDatabaseHelper.getInstance(getContext());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#query(android.net.Uri,
	 * java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// Open a database
		SQLiteDatabase db = mDbHelper.openDataBase();

		Cursor cursor;

		// Table name used for querying.
		String tableName = uri
				.getQueryParameter(AppConstants.QUERY_PARAMETER_TABLE_NAME);
		if (tableName == null) {
			tableName = getTableName();
		}

		// Group by for querying.
		String groupBy = uri
				.getQueryParameter(AppConstants.QUERY_PARAMETER_GROUP_BY);

		// If the limit is given in the query parameter, use it.
		String limit = uri.getQueryParameter(AppConstants.QUERY_PARAMETER_LIMIT);

		// Selection according to the URI.
		// QuerySelectionData selectionData = getSelectionForUri(uri, selection,
		// selectionArgs);

		cursor = db.query(tableName, projection, selection, selectionArgs, groupBy,
				groupBy, sortOrder, limit);

		cursor.setNotificationUri(getContext().getContentResolver(),
				getContentUri());
		return cursor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#getType(android.net.Uri)
	 */
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#insert(android.net.Uri,
	 * android.content.ContentValues)
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Open a read / write database to support the transaction.
		SQLiteDatabase db = mDbHelper.openDataBase();

		// Validate the requested uri.

		if (values.get(KEY_UPDATED) == null) {
			values.put(KEY_UPDATED, System.currentTimeMillis());
		}

		long rowId = db.insert(getTableName(), null, values);
		if (rowId > 0) {
			Uri transUri = getUriForId(rowId);
			// Notify the listeners.
			notifyChange(getContentUri());

			return transUri;
		}
		throw new SQLException("Failed to insert row into " + uri + " "
				+ values.toString());
	}

	public abstract Uri getUriForId(long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#delete(android.net.Uri,
	 * java.lang.String, java.lang.String[])
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		// Open a read / write database to support the transaction.
		SQLiteDatabase db = mDbHelper.openDataBase();

		// Selection according to uri.
		// QuerySelectionData selectionData = getSelectionForUri(uri, selection,
		// selectionArgs);

		int rows = db.delete(getTableName(), selection, selectionArgs);
		// Notify the listeners.
		if (rows > 0)
			notifyChange(getContentUri());

		return rows;
	}

	// public QuerySelectionData getSelectionForUri(Uri uri, String selection,
	// String[] selectionArgs) {
	// QuerySelectionData selectionData = new QuerySelectionData(selection,
	// selectionArgs);
	// int match = sUriMatcher.match(uri);
	// switch (match) {
	// case TOURNAMENTS_CODE:
	//
	// break;
	// case TOURNAMENT_CODE:
	// String id = uri.getLastPathSegment();
	// selectionData.addSelectionData(KEY_ID, id);
	// }
	//
	// return selectionData;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.ContentProvider#update(android.net.Uri,
	 * android.content.ContentValues, java.lang.String, java.lang.String[])
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// Open a read / write database to support the transaction.
		SQLiteDatabase db = mDbHelper.openDataBase();

		// QuerySelectionData selectionData = getSelectionForUri(uri, selection,
		// selectionArgs);

		// Current timestamp if not added.
		if (values.get(KEY_UPDATED) == null) {
			values.put(KEY_UPDATED, System.currentTimeMillis());
		}

		int rows = db.update(getTableName(), values, selection, selectionArgs);

		if (rows > 0) {
			// Notify the listeners.
			notifyChange(getContentUri());
			return rows;
		}
		return 0;
	}

	private void notifyChange(Uri uri) {
		getContext().getContentResolver().notifyChange(uri, null);
	}
}
