# Changes:

  * Made clickable views like a button clickable inside the FlipViewPager.
  * Use RecyclerView.
  * Updated to API 23.
  * Added support for close clicks on a MergePage e.g. to add a close button.

# FlipViewPager.Draco

This project aims to provide a working page flip implementation for usage in ListView. Made in [Yalantis] (https://yalantis.com/?utm_source=github)

![Preview](https://d13yacurqjgara.cloudfront.net/users/125056/screenshots/1758298/99miles-find-friends-interface-animation.gif)

* Check this [project on Dribbble] (https://dribbble.com/shots/1758298-Find-Friends-Interaction?list=users&offset=35)

* See how it works on [Youtube] (https://www.youtube.com/watch?v=zNRPjS53m5w)

#Usage

*For a working implementation, Have a look at the Sample Project - sample*

To achieve the same grid-looking view you should:

1. Include the library as local library project:

	``` groovy
	compile 'com.yalantis:flipviewpager:1.0.0'
	```

2. Create your main layout, it will be the view with 2 items merged together:
	``` xml
	<!-- ... -->

	<ImageView
	    android:id="@+id/first"
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="0dp"
	    android:layout_weight="1"
	    android:contentDescription="left image"
	    android:layout_height="wrap_content"
	    android:scaleType="fitXY" />

	<LinearLayout
	    android:layout_width="1dp"
	    android:layout_weight="0"
	    android:background="#000000"
	    android:layout_height="fill_parent"/>

	<ImageView
	    android:id="@+id/second"
	    android:layout_width="0dp"
	    android:layout_weight="1"
	    android:contentDescription="right image"
	    android:layout_height="wrap_content"
	    android:scaleType="fitXY" />

	<!-- ... -->
	```
3. Create layout for displaying an additional info for each merged item:
	``` xml
	<!-- ... -->

	<com.yalantis.flip.sample.views.FontTextView
	    style="@style/TextView.Nickname"
	    android:id="@+id/nickname" />

	<LinearLayout
	    android:layout_below="@+id/nickname"
	    android:id="@+id/interestsPrimary"
	    style="@style/LinearLayout.Interests">

	    <com.yalantis.flip.sample.views.FontTextView
	        style="@style/TextView.Interest"
	        android:id="@+id/interest_1" />

	    <!-- ... -->

	</LinearLayout>

	```

4. 	Create your adapter and extend it from ```BaseFlipAdapter<T>```

	``` java
	class FriendsAdapter extends BaseFlipAdapter<Friend> {

		@Override
		public View getPage(int position,
				    View convertView,
				    ViewGroup parent,
				    Friend friend1,
				    Friend friend2) {
			// ...
		}

		class FriendsHolder {
	    	// ...
	    }
	}
	```

5.  Set your adapter in ```ListView```
	``` java
	final ListView friends = (ListView) findViewById(R.id.friends);
	friends.setAdapter(new FriendsAdapter(this, Utils.friends, settings));
	```

6.  You can handle clicks just like in regular ```ListView```

``` java
friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Friend friend = (Friend) friends.getAdapter().getItem(position);
        Toast.makeText(FriendsActivity.this, friend.getNickname(), Toast.LENGTH_SHORT).show();
    }
});
```

More options will be added soon :)

#Customization

To customize page will be shown first - create and pass FlipSettings object into adapter

``` java
FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
```

#Compatibility

  * Android 4.0+

# Changelog

### Version: 1.0

  * Initial Build

#### Let us know!

We’d be really happy if you sent us links to your projects where you use our component. Just send an email to github@yalantis.com And do let us know if you have any questions or suggestion regarding the animation. 

P.S. We’re going to publish more awesomeness wrapped in code and a tutorial on how to make UI for Android (iOS) better than better. Stay tuned!

## License

    Copyright 2015, Yalantis

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
