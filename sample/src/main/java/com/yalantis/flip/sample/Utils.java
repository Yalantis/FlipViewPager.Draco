package com.yalantis.flip.sample;

import com.yalantis.flip.sample.model.Friend;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yalantis
 */
public class Utils {
    public static final List<Friend> friends = new ArrayList<>();

    static {
        friends.add(new Friend(R.drawable.anastasia, "ANASTASIA", R.color.sienna, "Sport", "Literature", "Music", "Art", "Technology"));
        friends.add(new Friend(R.drawable.irene, "IRENE", R.color.saffron, "Travelling", "Flights", "Books", "Painting", "Design"));
        friends.add(new Friend(R.drawable.kate, "KATE", R.color.green, "Sales", "Pets", "Skiing", "Hairstyles", "Сoffee"));
        friends.add(new Friend(R.drawable.paul, "PAUL", R.color.pink, "Android", "Development", "Design", "Wearables", "Pets"));
        friends.add(new Friend(R.drawable.daria, "DARIA", R.color.orange, "Design", "Fitness", "Healthcare", "UI/UX", "Chatting"));
        friends.add(new Friend(R.drawable.kirill, "KIRILL", R.color.saffron, "Development", "Android", "Healthcare", "Sport", "Rock Music"));
        friends.add(new Friend(R.drawable.julia, "JULIA", R.color.green, "Cinema", "Music", "Tatoo", "Animals", "Management"));
        friends.add(new Friend(R.drawable.yalantis, "YALANTIS", R.color.purple, "Android", "IOS", "Application", "Development", "Company"));
    }
}
