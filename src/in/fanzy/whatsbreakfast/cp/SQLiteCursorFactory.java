package in.fanzy.whatsbreakfast.cp;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

public class SQLiteCursorFactory implements CursorFactory {

	private boolean debugQueries = true;

	public SQLiteCursorFactory() {
		this.debugQueries = false;
	}

	public SQLiteCursorFactory(boolean debugQueries) {
		this.debugQueries = debugQueries;
	}

	@Override
	public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery,
			String editTable, SQLiteQuery query) {
		if (debugQueries) {
			Log.d("SQL", query.toString());
		}
		return new SQLiteCursor(db, masterQuery, editTable, query);
	}

}
