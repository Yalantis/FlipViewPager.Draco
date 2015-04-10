package com.yalantis.flipviewpager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yalantis.flipviewpager.R;
import com.yalantis.flipviewpager.utils.FlipSettings;
import com.yalantis.flipviewpager.view.FlipViewPager;

import java.util.List;

/**
 * @author Yalantis
 */
public abstract class BaseFlipAdapter<T> extends BaseAdapter {
    private List<T> items;
    private FlipSettings settings;
    private LayoutInflater inflater;

    public BaseFlipAdapter(Context context, List<T> items, FlipSettings settings) {
        this.items = items;
        this.settings = settings;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // Checking if we need an additional row for single item
        return items.size() % 2 != 0 ? ((items.size() / 2) + 1) : (items.size() / 2);
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Trick to divide list into 2 parts
        T item1 = getItem(position * 2);
        // Used for cases when we have not an even size in incoming list
        T item2 = items.size() > (position * 2 + 1) ? getItem(position * 2 + 1) : null;

        final ViewHolder viewHolder;
        if (convertView == null)
            convertView = inflater.inflate(R.layout.flipper, null);
        if (convertView.getTag() != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.mFlipViewPager = (FlipViewPager) convertView.findViewById(R.id.flip_view);
        }

        // Listener to store flipped page
        viewHolder.mFlipViewPager.setOnChangePageListener(new FlipViewPager.OnChangePageListener() {
            @Override
            public void onFlipped(int page) {
                settings.savePageState(position, page);
            }
        });

        if (viewHolder.mFlipViewPager.getAdapter() == null) {
            viewHolder.mFlipViewPager.setAdapter(new MergeAdapter(item1, item2), settings.getDefaultPage(), position, items.size());
        } else {
            // Recycling internal adapter
            // So, it's double recycling - we have only 4-5 mFlipViewPager objects
            // and each of them have an adapter
            MergeAdapter adapter = (MergeAdapter) viewHolder.mFlipViewPager.getAdapter();
            adapter.updateData(item1, item2);
            viewHolder.mFlipViewPager.setAdapter(adapter, settings.getPageForPosition(position), position, items.size());
        }
        return convertView;
    }

    class ViewHolder {
        FlipViewPager mFlipViewPager;
    }

    public abstract View getPage(int position, View convertView, ViewGroup parent, T item1, T item2);

    public abstract int getPagesCount();

    // Adapter merges 2 items together
    private class MergeAdapter extends BaseAdapter {
        private T item1;
        private T item2;

        public MergeAdapter(T item1, T item2) {
            this.item1 = item1;
            this.item2 = item2;
        }

        public void updateData(T item1, T item2) {
            this.item1 = item1;
            this.item2 = item2;
        }

        @Override
        public int getCount() {
            return item2 == null ? getPagesCount() - 1 : getPagesCount();
        }

        @Override
        public Object getItem(int position) {
            return position; // Stub
        }

        @Override
        public long getItemId(int position) {
            return position; // Stub
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getPage(position, convertView, parent, item1, item2);
        }
    }
}
