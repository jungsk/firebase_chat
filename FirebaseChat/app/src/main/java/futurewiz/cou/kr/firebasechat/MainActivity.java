package futurewiz.cou.kr.firebasechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;
import futurewiz.cou.kr.firebasechat.chat.ChatListFragment;
import futurewiz.cou.kr.firebasechat.frends.Friends_addActivity;
import futurewiz.cou.kr.firebasechat.etc.ProfileActivity;
import futurewiz.cou.kr.firebasechat.etc.SettingActivity;
import futurewiz.cou.kr.firebasechat.frends.FriendsListFragment;
import futurewiz.cou.kr.firebasechat.login.AuthManager;
import futurewiz.cou.kr.firebasechat.login.SignInActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (AuthManager.getInstance().getFirebaseUser() == null) {
            startActivity(new Intent(this, SignInActivity.class));
        }
    }
    @OnClick(R.id.button)
    public void onButton1Clicked(View v) {

        startActivity(new Intent(this, Friends_addActivity.class));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FriendsListFragment(), "친구");
        adapter.addFragment(new ChatListFragment(), "채팅");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.action_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;


        }
        return true;
    }

    public void writeDataExample() {
        // 데이터 쓰기
        databaseReference.child("users").child("cUser").setValue("TEST");
    }

    public void readDataExample() {
        // 데이터 읽기
        databaseReference.child("friends/BNQ86mcIbQOwRmt8RuoERwsmNGC2/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Boolean> friendsData = (Map<String, Boolean>) dataSnapshot;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
