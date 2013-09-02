package com.example.loadermanagerdemo;

import com.example.loadermanagerdemo.dbhelper.DbHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class StudentContentProvider extends ContentProvider {
	private DbHelper helper;
	private final String TAG = "main";
	private final static UriMatcher URI_MATCHAR = new UriMatcher(
			UriMatcher.NO_MATCH);
	private final static int STUDENT = 1;
	private final static int STUDENTS = 2;
	static {

		URI_MATCHAR.addURI(
				"com.example.loadermanagerdemo.StudentContentProvider",
				"student", STUDENTS);
		URI_MATCHAR.addURI(
				"com.example.loadermanagerdemo.StudentContentProvider",
				"student/#", STUDENT);
	}

	public StudentContentProvider() {
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = -1;
		int flag = URI_MATCHAR.match(uri);
		SQLiteDatabase database = helper.getWritableDatabase();
		switch (flag) {
		case STUDENT:
			long stuid = ContentUris.parseId(uri);
			String where_value = "_id=?";
			String[] where_args = { String.valueOf(stuid) };
			count = database.delete("student", where_value, where_args);
			break;

		case STUDENTS:
			count = database.delete("student", selection, selectionArgs);
			break;
		}
		Log.i(TAG, "---->>" + count);
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		int flag = URI_MATCHAR.match(uri);
		switch (flag) {
		case STUDENT:
			return "vnd.android.cursor.item/student";
		case STUDENTS:
			return "vnd.android.cursor.dir/students";
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int flag = URI_MATCHAR.match(uri);
		SQLiteDatabase database = helper.getWritableDatabase();
		Uri returnUri = null;

		if (STUDENTS == flag) {
			long id = database.insert("student", null, values);
			returnUri = ContentUris.withAppendedId(uri, id);
		}
		Log.i(TAG, "---->>" + returnUri.toString());
		getContext().getContentResolver().notifyChange(uri, null);
		return returnUri;
	}

	@Override
	public boolean onCreate() {
		helper = new DbHelper(getContext());
		Log.d("jltxgcy", "Provider onCreate");
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		SQLiteDatabase database=helper.getReadableDatabase();
		int flag = URI_MATCHAR.match(uri);
		switch (flag) {
		case STUDENTS:
			cursor=database.query(true, "student", null, selection, selectionArgs, null, null, null, null);
			break;

		case STUDENT:
			long stuid=ContentUris.parseId(uri);
			String where_value="_id=?";
			String[] where_args={String.valueOf(stuid)};
			cursor=database.query(true, "student", null, where_value, where_args, null, null, null, null);
			break;
		}
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = -1;
		int flag = URI_MATCHAR.match(uri);
		SQLiteDatabase database = helper.getWritableDatabase();
		switch (flag) {
		case STUDENTS:
			count = database
					.update("student", values, selection, selectionArgs);
			break;

		case STUDENT:
			long stuid = ContentUris.parseId(uri);
			String where_value = "_id=?";
			String[] where_args = { String.valueOf(stuid) };
			count = database.update("student", values, where_value, where_args);
			break;
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
