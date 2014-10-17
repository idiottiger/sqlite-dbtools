package pkg.id2.dbtools.library;

import java.util.List;

import pkg.id2.sqlitedbtools.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StringListItemAdapter extends SimpleAdapter2<String> {

	public StringListItemAdapter(Context context) {
		super(context, new StringListViewHolderFactory());
	}

	public void update(List<String> items) {
		mItems.clear();
		mItems.addAll(items);
		notifyDataSetChanged();
	}

	private static class StringListViewHolder implements IViewHolder<String> {

		TextView mTextView;

		@Override
		public View createConvertView(int position, LayoutInflater inflater, ViewGroup parent) {
			return inflater.inflate(R.layout.string_listview_item, parent, false);
		}

		@Override
		public void initViewHolder(View convertView) {
			mTextView = (TextView) convertView.findViewById(R.id.listview_item_textview);
		}

		@Override
		public void bindAdapterItem(int position, String item) {
			mTextView.setText(item);
		}
	}

	private static class StringListViewHolderFactory implements IViewHolderFactory<String> {
		@Override
		public IViewHolder<String> createViewHolder(int type) {
			return new StringListViewHolder();
		}
	}
}
