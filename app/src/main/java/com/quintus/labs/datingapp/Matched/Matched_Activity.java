package com.quintus.labs.datingapp.Matched;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.TopNavigationViewHelper;
import com.quintus.labs.datingapp.Utils.User;

import java.util.ArrayList;
import java.util.List;



public class Matched_Activity extends AppCompatActivity {

    private static final String TAG = "Matched_Activity";
    private static final int ACTIVITY_NUM = 2;
    List<Users> matchList = new ArrayList<>();
    List<User> copyList = new ArrayList<>();
    private Context mContext = Matched_Activity.this;
    private String userId, userSex;
    private double latitude = 37.349642;
    private double longtitude = -121.938987;
    private EditText search;
    private List<Users> usersList = new ArrayList<>();
    private RecyclerView recyclerView, mRecyclerView;
    private ActiveUserAdapter adapter;
    private MatchUserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched);

        setupTopNavigationView();
        searchFunc();


        recyclerView = findViewById(R.id.active_recycler_view);
        mRecyclerView = findViewById(R.id.matche_recycler_view);

        adapter = new ActiveUserAdapter(usersList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareActiveData();

        mAdapter = new MatchUserAdapter(matchList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager1);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        prepareMatchData();


    }

    private void prepareActiveData() {
        Users users = new Users("1", "Thanh Vy", 21, "https://www.google.com/url?sa=i&url=https%3A%2F%2Faminoapps.com%2Fc%2Fanime%2Fpage%2Fblog%2Fbecoming-a-wifu%2Fdxtb_umbaYglPrn5KKR7G6qXKnrJMW&psig=AOvVaw1RdFQAMdHiMBXWTbIXttX2&ust=1666404574842000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLDVhK_e8PoCFQAAAAAdAAAAABAJ", "Thích anime", "Acting", 200);
        usersList.add(users);
        users = new Users("2", "Ngọc Anh", 20, "https://www.google.com/url?sa=i&url=https%3A%2F%2Faminoapps.com%2Fc%2Fanime%2Fpage%2Fblog%2Fbecoming-a-wifu%2Fdxtb_umbaYglPrn5KKR7G6qXKnrJMW&psig=AOvVaw1RdFQAMdHiMBXWTbIXttX2&ust=1666404574842000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLDVhK_e8PoCFQAAAAAdAAAAABAJ", "", "Dancing", 800);
        usersList.add(users);
        users = new Users("3", "Khánh Linh", 22, "https://www.google.com/url?sa=i&url=https%3A%2F%2Faminoapps.com%2Fc%2Fanime%2Fpage%2Fblog%2Fbecoming-a-wifu%2Fdxtb_umbaYglPrn5KKR7G6qXKnrJMW&psig=AOvVaw1RdFQAMdHiMBXWTbIXttX2&ust=1666404574842000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLDVhK_e8PoCFQAAAAAdAAAAABAJ", "", "Singing", 400);
        usersList.add(users);
        users = new Users("4", "Nhật Hạ", 19, "", "", "Art", 5000);
        usersList.add(users);

        adapter.notifyDataSetChanged();
    }

    private void prepareMatchData() {
        Users users = new Users("1", "Thanh Vy", 21, "https://gamek.mediacdn.vn/133514250583805952/2022/4/18/acm3-1650274793155361428126.jpg", "", "Acting", 200);
        matchList.add(users);
        users = new Users("2", "Nhật Hạ", 20, "https://www.google.com/url?sa=i&url=https%3A%2F%2Faminoapps.com%2Fc%2Fanime%2Fpage%2Fblog%2Fbecoming-a-wifu%2Fdxtb_umbaYglPrn5KKR7G6qXKnrJMW&psig=AOvVaw1RdFQAMdHiMBXWTbIXttX2&ust=1666404574842000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLDVhK_e8PoCFQAAAAAdAAAAABAJ", "", "Dancing", 800);
        matchList.add(users);
        users = new Users("3", "Thuỳ Linh", 22, "https://www.google.com/url?sa=i&url=https%3A%2F%2Faminoapps.com%2Fc%2Fanime%2Fpage%2Fblog%2Fbecoming-a-wifu%2Fdxtb_umbaYglPrn5KKR7G6qXKnrJMW&psig=AOvVaw1RdFQAMdHiMBXWTbIXttX2&ust=1666404574842000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCLDVhK_e8PoCFQAAAAAdAAAAABAJ", "", "Singing", 400);
        matchList.add(users);
        users = new Users("4", "", 19, "", "", "swiming", 1308);
        matchList.add(users);
        users = new Users("5", "", 20, "", "", "Drawing", 1200);
        matchList.add(users);
        users = new Users("6", "", 21, "", "", "Sleeping", 700);
        matchList.add(users);
        users = new Users("7", "", 19, "", "", "Art", 5000);
        matchList.add(users);

        mAdapter.notifyDataSetChanged();
    }

    private void searchFunc() {
       /* search = findViewById(R.id.searchBar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchText();
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchText();
            }
        });*/
    }

    /* private void searchText() {
         String text = search.getText().toString().toLowerCase(Locale.getDefault());
         if (text.length() != 0) {
             if (matchList.size() != 0) {
                 matchList.clear();
                 for (User user : copyList) {
                     if (user.getUsername().toLowerCase(Locale.getDefault()).contains(text)) {
                         matchList.add(user);
                     }
                 }
             }
         } else {
             matchList.clear();
             matchList.addAll(copyList);
         }

         mAdapter.notifyDataSetChanged();
     }

     private boolean checkDup(User user) {
         if (matchList.size() != 0) {
             for (User u : matchList) {
                 if (u.getUsername() == user.getUsername()) {
                     return true;
                 }
             }
         }

         return false;
     }

     private void checkClickedItem(int position) {

         User user = matchList.get(position);
         //calculate distance
         Intent intent = new Intent(this, ProfileCheckinMatched.class);
         intent.putExtra("classUser", user);

         startActivity(intent);
     }
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


}
