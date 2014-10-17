package pkg.id2.dbtools.library;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter2<T> extends BaseAdapter implements Handler.Callback {

	private static final int MSG_NOTIFY_DATA_SET_CHANGED = 1 << 20;
	private static final int DEFAULT_VIEW_TYPE_COUNT = 1;

	protected final List<T> mItems;
	private Context mContext;
	private LayoutInflater mInflater;
	private Handler mHandler;
	private IViewHolderFactory<T> mFactory;

	public SimpleAdapter2(Context context, IViewHolderFactory<T> factory) {
		this(context, 0, factory);
	}

	public SimpleAdapter2(Context context, int capacity, IViewHolderFactory<T> factory) {
		mContext = context;
		mItems = new ArrayList<T>(capacity);
		mInflater = LayoutInflater.from(mContext);
		mHandler = new Handler(Looper.getMainLooper(), this);
		mFactory = factory;
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public T getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final View getView(int position, View convertView, ViewGroup parent) {
		final T item = getItem(position);
		IViewHolder<T> viewHolder;
		if (convertView == null) {
			viewHolder = mFactory.createViewHolder(getItemViewType(position));
			convertView = viewHolder.createConvertView(position, mInflater, parent);

			viewHolder.initViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (IViewHolder<T>) convertView.getTag();
		}

		viewHolder.bindAdapterItem(position, item);

		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return DEFAULT_VIEW_TYPE_COUNT;
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	public final void release() {
		mContext = null;
		mItems.clear();
	}

	public final void reset() {
		mItems.clear();
		notifyDataSetChanged();
	}

	public final Context getContext() {
		return mContext;
	}

	public final Handler getHandler() {
		return mHandler;
	}

	public final List<T> getItems() {
		return mItems;
	}

	/**
	 * can notify in non-ui thread
	 */
	@Override
	public final void notifyDataSetChanged() {
		if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
			super.notifyDataSetChanged();
		} else {
			Log.i("TAG", "send notify message to main thread");
			mHandler.sendEmptyMessage(MSG_NOTIFY_DATA_SET_CHANGED);
		}
	}

	@Override
	public final boolean handleMessage(Message msg) {
		final int what = msg.what;
		if (what == MSG_NOTIFY_DATA_SET_CHANGED) {
			notifyDataSetChanged();
		} else {
			processMessage(msg);
		}
		return false;
	}

	/**
	 * can override this method to process the message, send by
	 * {@link #getHandler()}
	 *
	 * @param msg
	 */
	protected void processMessage(Message msg) {
	}

	public static interface IViewHolder<T> {

		public View createConvertView(int position, LayoutInflater inflater, ViewGroup parent);

		public void initViewHolder(View convertView);

		public void bindAdapterItem(int position, T item);
	}

	public static interface IViewHolderFactory<T> {
		public IViewHolder<T> createViewHolder(int type);
	}
}