package com.quintus.labs.datingapp.Main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.PulsatorLayout;
import com.quintus.labs.datingapp.Utils.TopNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 1;
    final private int MY_PERMISSIONS_REQUEST_LOCATION = 123;
    ListView listView;
    List<Cards> rowItems;
    FrameLayout cardFrame, moreFrame;
    private Context mContext = MainActivity.this;
    private NotificationHelper mNotificationHelper;
    private Cards cards_data[];
    private PhotoAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cardFrame = findViewById(R.id.card_frame);
        moreFrame = findViewById(R.id.more_frame);
        // start pulsator
        PulsatorLayout mPulsator = findViewById(R.id.pulsator);
        mPulsator.start();
        mNotificationHelper = new NotificationHelper(this);


        setupTopNavigationView();


        rowItems = new ArrayList<Cards>();
        Cards cards = new Cards("1", "Khánh Linh", 21, "https://gamek.mediacdn.vn/133514250583805952/2022/4/18/acm3-1650274793155361428126.jpg", "Simple and beautiful Girl", "Acting", 200);
        rowItems.add(cards);
        cards = new Cards("2", "Thanh Vy", 20, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fgamek.vn%2Ftim-hieu-ve-3-waifu-lot-top-10-best-girl-mua-dong-2022-trong-anime-my-dress-up-darling-20220418165035829.chn&psig=AOvVaw28Nlp7N-dTc5pyRt6_ryns&ust=1666422651905000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLiumKXi8PoCFQAAAAAdAAAAABAI", "cool Minded Girl", "Dancing", 800);
        rowItems.add(cards);
        cards = new Cards("3", "Vân Anh", 22, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fgamek.vn%2Ftim-hieu-ve-3-waifu-lot-top-10-best-girl-mua-dong-2022-trong-anime-my-dress-up-darling-20220418165035829.chn&psig=AOvVaw28Nlp7N-dTc5pyRt6_ryns&ust=1666422651905000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLiumKXi8PoCFQAAAAAdAAAAABAI", "Simple and beautiful Girl", "Singing", 400);
        rowItems.add(cards);
        cards = new Cards("4", "", 19, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fgamek.vn%2Ftim-hieu-ve-3-waifu-lot-top-10-best-girl-mua-dong-2022-trong-anime-my-dress-up-darling-20220418165035829.chn&psig=AOvVaw28Nlp7N-dTc5pyRt6_ryns&ust=1666422651905000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLiumKXi8PoCFQAAAAAdAAAAABAI", "dashing girl", "swiming", 1308);
        rowItems.add(cards);
        cards = new Cards("5", "", 20, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fgamek.vn%2Ftim-hieu-ve-3-waifu-lot-top-10-best-girl-mua-dong-2022-trong-anime-my-dress-up-darling-20220418165035829.chn&psig=AOvVaw28Nlp7N-dTc5pyRt6_ryns&ust=1666422651905000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLiumKXi8PoCFQAAAAAdAAAAABAI", "chulbuli nautankibaj ", "Drawing", 1200);
        rowItems.add(cards);
        cards = new Cards("6", "", 21, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fgamek.vn%2Ftim-hieu-ve-3-waifu-lot-top-10-best-girl-mua-dong-2022-trong-anime-my-dress-up-darling-20220418165035829.chn&psig=AOvVaw28Nlp7N-dTc5pyRt6_ryns&ust=1666422651905000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLiumKXi8PoCFQAAAAAdAAAAABAI", "Simple and beautiful Girl", "Sleeping", 700);
        rowItems.add(cards);
        cards = new Cards("7", "", 19, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fgamek.vn%2Ftim-hieu-ve-3-waifu-lot-top-10-best-girl-mua-dong-2022-trong-anime-my-dress-up-darling-20220418165035829.chn&psig=AOvVaw28Nlp7N-dTc5pyRt6_ryns&ust=1666422651905000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLiumKXi8PoCFQAAAAAdAAAAABAI", "Papa's Pari", "Art", 5000);
        rowItems.add(cards);

        arrayAdapter = new PhotoAdapter(this, R.layout.item, rowItems);

        checkRowItem();
        updateSwipeCard();
    }

    private void checkRowItem() {
        if (rowItems.isEmpty()) {
            moreFrame.setVisibility(View.VISIBLE);
            cardFrame.setVisibility(View.GONE);
        }
    }

    private void updateLocation() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        updateLocation();
                    } else {
                        Toast.makeText(MainActivity.this, "Location Permission Denied. You have to give permission inorder to know the user range ", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void updateSwipeCard() {
        final SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                checkRowItem();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;

                //check matches
                checkRowItem();

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here


            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void sendNotification() {
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(mContext.getString(R.string.app_name), mContext.getString(R.string.match_notification));

        mNotificationHelper.getManager().notify(1, nb.build());
    }


    public void DislikeBtn(View v) {
        if (rowItems.size() != 0) {
            Cards card_item = rowItems.get(0);

            String userId = card_item.getUserId();

            rowItems.remove(0);
            arrayAdapter.notifyDataSetChanged();

            Intent btnClick = new Intent(mContext, BtnDislikeActivity.class);
            btnClick.putExtra("url", card_item.getProfileImageUrl());
            startActivity(btnClick);
        }
    }

    public void LikeBtn(View v) {
        if (rowItems.size() != 0) {
            Cards card_item = rowItems.get(0);

            String userId = card_item.getUserId();

            //check matches

            rowItems.remove(0);
            arrayAdapter.notifyDataSetChanged();

            Intent btnClick = new Intent(mContext, BtnLikeActivity.class);
            btnClick.putExtra("url", card_item.getProfileImageUrl());
            startActivity(btnClick);
        }
    }


    /**
     * setup top tool bar
     */
    private void setupTopNavigationView() {
        Log.d(TAG, "setupTopNavigationView: setting up TopNavigationView");
        BottomNavigationViewEx tvEx = findViewById(R.id.topNavViewBar);
        TopNavigationViewHelper.setupTopNavigationView(tvEx);
        TopNavigationViewHelper.enableNavigation(mContext, tvEx);
        Menu menu = tvEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    @Override
    public void onBackPressed() {

    }


}
