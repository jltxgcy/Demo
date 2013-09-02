package com.example.loadermanagerdemo.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	
	private static String name="mydb.db";
	private static int version=1;
	public DbHelper(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.d("jltxgcy", "onCreate");
		String sql="Create table student(_id integer primary key autoincrement,name varchar(64))";
		database.execSQL(sql);
		ContentValues values=new ContentValues();
		values.put("name", "Jack");
		database.insert("student", null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
