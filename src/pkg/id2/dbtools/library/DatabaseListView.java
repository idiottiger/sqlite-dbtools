package pkg.id2.dbtools.library;

import java.io.File;
import java.util.Arrays;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DatabaseListView extends ListView implements IView, android.widget.AdapterView.OnItemClickListener {

	private static final String DB_FOLDER_NAME = "databases";

	private Context mContext;
	private File mBaseFolder;
	private StringListItemAdapter mAdapter;
	private OnDatabaseSelectedListener mListener;

	public DatabaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public void setOnDatabaseSelectedListener(OnDatabaseSelectedListener listener) {
		mListener = listener;
	}

	@Override
	public void init() {
		mAdapter = new StringListItemAdapter(mContext);
		mBaseFolder = mContext.getFilesDir().getParentFile();
		setAdapter(mAdapter);
		setOnItemClickListener(this);
		update();
	}

	@Override
	public void update() {
		mAdapter.reset();
		final File databaseFolder = new File(mBaseFolder, DB_FOLDER_NAME);
		if (databaseFolder.exists() && databaseFolder.isDirectory()) {
			final String[] databaseList = databaseFolder.list();
			if (databaseList != null) {
				mAdapter.update(Arrays.asList(databaseList));
			}
		}
	}

	@Override
	public void release() {
		setAdapter(null);
		mAdapter.release();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (mListener != null) {
			final String dbName = mAdapter.getItem(position);
			mListener.onDatabaseSelected(dbName);
		}
	}

	public static interface OnDatabaseSelectedListener {
		public void onDatabaseSelected(String dbName);
	}

}
