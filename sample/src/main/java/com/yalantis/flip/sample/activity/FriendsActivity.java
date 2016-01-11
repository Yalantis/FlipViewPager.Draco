package com.yalantis.flip.sample.activity;

import android.support.v7.app.ActionBarActivity;

/**
 * @author Yalantis
 */
public class FriendsActivity extends ActionBarActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_friends);
//        final ListView friends = (ListView) findViewById(R.id.friends);
//
//        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
//        friends.setAdapter(new FriendsAdapter(this, Utils.friends, settings));
//        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Friend f = (Friend) friends.getAdapter().getItem(position);
//
//                Toast.makeText(FriendsActivity.this, f.getNickname(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    class FriendsAdapter extends BaseFlipAdapter {
//
//        private final int PAGES = 3;
//        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3, R.id.interest_4, R.id.interest_5};
//
//        public FriendsAdapter(Context context, List<Friend> items, FlipSettings settings) {
//            super(context, items, settings);
//        }
//
//        @Override
//        public View getPage(int position, View convertView, ViewGroup parent, Object friend1, Object friend2, CloseListener closeListener) {
//            final FriendsHolder holder;
//
//            if (convertView == null) {
//                holder = new FriendsHolder();
//                convertView = getLayoutInflater().inflate(R.layout.friends_merge_page, parent, false);
//                holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
//                holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
//                holder.infoPage = getLayoutInflater().inflate(R.layout.friends_info, parent, false);
//                holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);
//
//                for (int id : IDS_INTEREST)
//                    holder.interests.add((TextView) holder.infoPage.findViewById(id));
//
//                convertView.setTag(holder);
//            } else {
//                holder = (FriendsHolder) convertView.getTag();
//            }
//
//            switch (position) {
//                // Merged page with 2 friends
//                case 1:
//                    holder.leftAvatar.setImageResource(((Friend) friend1).getAvatar());
//                    if (friend2 != null)
//                        holder.rightAvatar.setImageResource(((Friend) friend2).getAvatar());
//                    break;
//                default:
//                    fillHolder(holder, position == 0 ? (Friend) friend1 : (Friend) friend2);
//                    holder.infoPage.setTag(holder);
//                    return holder.infoPage;
//            }
//            return convertView;
//        }
//
//        @Override
//        public int getPagesCount() {
//            return PAGES;
//        }
//
//        private void fillHolder(FriendsHolder holder, Friend friend) {
//            if (friend == null)
//                return;
//            Iterator<TextView> iViews = holder.interests.iterator();
//            Iterator<String> iInterests = friend.getInterests().iterator();
//            while (iViews.hasNext() && iInterests.hasNext())
//                iViews.next().setText(iInterests.next());
//            holder.infoPage.setBackgroundColor(getResources().getColor(friend.getBackground()));
//            holder.nickName.setText(friend.getNickname());
//        }
//
//        class FriendsHolder {
//            ImageView leftAvatar;
//            ImageView rightAvatar;
//            View infoPage;
//
//            List<TextView> interests = new ArrayList<>();
//            TextView nickName;
//        }
//    }
}
