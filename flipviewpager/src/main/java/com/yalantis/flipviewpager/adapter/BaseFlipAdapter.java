package com.yalantis.flipviewpager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yalantis.flipviewpager.R;
import com.yalantis.flipviewpager.utils.FlipSettings;
import com.yalantis.flipviewpager.view.FlipViewPager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yalantis
 */
public abstract class BaseFlipAdapter extends RecyclerView.Adapter<BaseFlipAdapter.BaseViewHolder> {

    /**
     * We're saving which FlipViewPager is at which position so we can call flipToPage()
     * on a close click.
     */
    protected Map<Integer, FlipViewPager> flipViewPagerMap = new HashMap<>();
    private List items;
    private FlipSettings settings;
    private LayoutInflater inflater;

    public BaseFlipAdapter(Context context, List items, FlipSettings settings) {
        this.items = items;
        this.settings = settings;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inflater
                .inflate(R.layout.flipper, viewGroup, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(v);
        baseViewHolder.mFlipViewPager = (FlipViewPager) v.findViewById(R.id.flip_view);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, final int position) {
        // Trick to divide list into 2 parts
        Object item1 = items.get(position * 2);
        // Used for cases when we have not an even size in incoming list
        Object item2 = items.size() > (position * 2 + 1) ? items.get(position * 2 + 1) : null;

        // Listener to store flipped page
        baseViewHolder.mFlipViewPager.setOnChangePageListener(new FlipViewPager.OnChangePageListener() {
            @Override
            public void onFlipped(int page) {
                settings.savePageState(position, page);
            }
        });

        flipViewPagerMap.put(position, baseViewHolder.mFlipViewPager);
        baseViewHolder.mFlipViewPager.setAdapter(new MergeAdapter(item1, item2, position), settings.getDefaultPage(), position, items.size());
    }

    @Override
    public int getItemCount() {
        // Checking if we need an additional row for single item
        return items.size() % 2 != 0 ? ((items.size() / 2) + 1) : (items.size() / 2);
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        FlipViewPager mFlipViewPager;

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract View getPage(int position, View convertView, ViewGroup parent, Object item1, Object item2, CloseListener closeListener);

    public abstract int getPagesCount();

    // Adapter merges 2 items together
    private class MergeAdapter extends BaseAdapter implements CloseListener {
        private Object item1;
        private Object item2;
        private int position;

        public MergeAdapter(Object item1, Object item2, int position) {
            this.item1 = item1;
            this.item2 = item2;
            this.position = position;
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
            return getPage(position, convertView, parent, item1, item2, this);
        }

        @Override
        public void onClickClose() {
            flipViewPagerMap.get(position).flipToPage(1);
        }
    }

    public interface CloseListener {
        void onClickClose();
    }
}
