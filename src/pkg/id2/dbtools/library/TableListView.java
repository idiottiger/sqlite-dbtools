package pkg.id2.dbtools.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class TableListView extends ListView implements IView {

	private Context mContext;
	private StringListItemAdapter mAdapter;
	private String mDbName;

	public TableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public void setDatabaseName(String dbName) {
		mDbName = dbName;
	}

	@Override
	public void init() {
		mAdapter = new StringListItemAdapter(mContext);
		setAdapter(mAdapter);

		update();
	}

	@Override
	public void update() {
	}

	@Override
	public void release() {
		setAdapter(null);
		mAdapter.release();
	}

}
