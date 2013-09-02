package com.example.loadermanagerdemo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LoaderManager manager;
	private ListView listview;
	private AlertDialog alertDialog;
	private SimpleCursorAdapter mAdapter;
	private final String TAG="main";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (ListView) findViewById(R.id.listView1);

		mAdapter = new SimpleCursorAdapter(MainActivity.this,
				android.R.layout.simple_list_item_1, null,
				new String[] { "name" }, new int[] { android.R.id.text1 },0);
		listview.setAdapter(mAdapter);
		manager = getLoaderManager();
		manager.initLoader(1000, null, callbacks);

		registerForContextMenu(listview);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contentmenu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.menu_add:
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			final View view = LayoutInflater.from(MainActivity.this).inflate(
					R.layout.add_name, null);
			Button btnAdd = (Button) view.findViewById(R.id.btnAdd);
			btnAdd.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					EditText etAdd = (EditText) view
							.findViewById(R.id.username);
					String name = etAdd.getText().toString();
					ContentResolver contentResolver = getContentResolver();
					ContentValues contentValues = new ContentValues();
					contentValues.put("name", name);
					Uri uri = Uri
							.parse("content://com.example.loadermanagerdemo.StudentContentProvider/student");
					Uri result = contentResolver.insert(uri, contentValues);
					/*if (result != null) {
						manager.restartLoader(1000, null, callbacks);
					}*/
					alertDialog.dismiss();
					
					//Log.i(TAG, "添加数据成功，name="+name);
				}
			});
			builder.setView(view);
			alertDialog = builder.show();
			return true;
		case R.id.menu_delete:
			TextView tv = (TextView) info.targetView;
			String name = tv.getText().toString();
			Uri url = Uri
					.parse("content://com.example.loadermanagerdemo.StudentContentProvider/student");
			ContentResolver contentResolver = getContentResolver();
			String where = "name=?";
			String[] selectionArgs = { name };
			int count = contentResolver.delete(url, where, selectionArgs);
			/*if (count == 1) {
				manager.restartLoader(1000, null, callbacks);
			}*/
			//Log.i(TAG, "删除数据成功，name="+name);
			return true;
		default:
			return super.onContextItemSelected(item);
		}

	}

	private LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderCallbacks<Cursor>() {

		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
			Uri uri = Uri
					.parse("content://com.example.loadermanagerdemo.StudentContentProvider/student");
			CursorLoader loader = new CursorLoader(MainActivity.this, uri,
					null, null, null, null);
			Log.i("jltxgcy", "onCreateLoader被执行。");
			return loader;
		}

		@Override
		public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
			mAdapter.swapCursor(cursor);
			//listview.setAdapter(mAdapter);
			Log.i("jltxgcy", "onLoadFinished被执行。");
		}

		@Override
		public void onLoaderReset(Loader<Cursor> loader) {
			mAdapter.swapCursor(null);
			Log.i("jltxgcy", "onLoaderReset被执行。");
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
