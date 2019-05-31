package com.yalantis.flip.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.flip.sample.R;
import com.yalantis.flip.sample.Utils;
import com.yalantis.flip.sample.model.Friend;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yalantis
 */
public class FriendsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();

        setContentView(R.layout.activity_friends);
        RecyclerView friends = (RecyclerView) findViewById(R.id.friends);

        FriendsAdapter adapter = new FriendsAdapter(this, Utils.friends, settings);
        friends.setLayoutManager(new LinearLayoutManager(this));
        friends.setHasFixedSize(true);
        friends.setAdapter(adapter);
    }

    class FriendsAdapter extends BaseFlipAdapter<Friend, FriendsAdapter.FriendsHolder> {
        private static final int ITEM_REMOVE_TIMEOUT = 60;
        private final int PAGES = 3;
        private final Activity activity;
        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3, R.id.interest_4, R.id.interest_5};

        public FriendsAdapter(Activity activity, List<Friend> items, FlipSettings settings) {
            super(activity, items, settings);
            this.activity = activity;
        }

        @Override
        public FriendsHolder newHolder(View v) {
            return new FriendsHolder(v);
        }


        @Override
        public View getPage(int position, View convertView, ViewGroup parent, Friend friend1, Friend friend2, final BaseFlipAdapter.CloseListener closeListener) {
            final FriendsHolder holder;

            if (convertView == null) {
                holder = new FriendsHolder(parent);

                convertView = activity.getLayoutInflater().inflate(R.layout.friends_merge_page, parent, false);

                // Set fixed size to
                ViewGroup.LayoutParams params = convertView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = Utils.getActivityWidth(activity) / 2; // Height = Width / 2 (to make every picture a Square)
                convertView.requestLayout();


                holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
                holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
                holder.infoPage = activity.getLayoutInflater().inflate(R.layout.friends_info, parent, false);
                holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);
                holder.btnShow = (Button) holder.infoPage.findViewById(R.id.btn_show);
                holder.btnClose = (Button) holder.infoPage.findViewById(R.id.btn_close);


                for (int id : IDS_INTEREST)
                    holder.interests.add((TextView) holder.infoPage.findViewById(id));

                convertView.setTag(holder);
            } else {
                holder = (FriendsHolder) convertView.getTag();
            }

            switch (position) {
                // Merged page with 2 drivers
                case 1:
                    holder.leftAvatar.setImageResource(friend1.getAvatar());
                    if (friend2 != null) {
                        holder.rightAvatar.setImageResource(friend2.getAvatar());
                        holder.rightAvatar.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    fillHolder(holder, closeListener, position == 0 ? friend1 : friend2);
                    holder.infoPage.setTag(holder);
                    return holder.infoPage;
            }
            return convertView;
        }

        @Override
        public int getPagesCount() {
            return PAGES;
        }

        private void fillHolder(FriendsHolder holder, final CloseListener closeListener, final Friend friend) {
            if (friend == null)
                return;
            Iterator<TextView> iViews = holder.interests.iterator();
            Iterator<String> iInterests = friend.getInterests().iterator();
            while (iViews.hasNext() && iInterests.hasNext())
                iViews.next().setText(iInterests.next());
            holder.infoPage.setBackgroundColor(getResources().getColor(friend.getBackground()));
            holder.nickName.setText(friend.getNickname());
            holder.btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FriendsActivity.this, friend.getNickname(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeListener.onClickClose();
                }
            });
        }


        /**
         * @param items
         * @apiNote This is anti-pattern
         */
        public void updateItems(List<Friend> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        public void addItem(Friend item) {
            this.items.add(item);
            notifyDataSetChanged();
        }

        class FriendsHolder extends BaseFlipAdapter.BaseViewHolder {
            Button btnClose;
            ImageView leftAvatar;
            ImageView rightAvatar;
            View infoPage;
            Button btnShow;

            List<TextView> interests = new ArrayList<>();
            TextView nickName;

            public FriendsHolder(View itemView) {
                super(itemView);
            }
        }
    }

}
