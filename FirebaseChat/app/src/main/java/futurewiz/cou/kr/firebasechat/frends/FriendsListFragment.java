package futurewiz.cou.kr.firebasechat.frends;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseFragment;
import futurewiz.cou.kr.firebasechat.chat.ChattingActivity;
import futurewiz.cou.kr.firebasechat.login.UserData;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 친구목록 Fragment
 */

public class FriendsListFragment extends BaseFragment {

    @BindView(R.id.friends_listView)
    ListView friendsListView;

    private ArrayAdapter adapter;
    private FriendsManager friendsManager = FriendsManager.getInstance();

    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setView(inflater.inflate(R.layout.fragment_friends_list, container, false));

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1);
        friendsListView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (authManager.isLogined()) {
            DatabaseReference friendDataBase = databaseReference.child("friends/" + authManager.getFirebaseUser().getUid());

            // 친구목록 데이터 요청
            friendDataBase .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    adapter.clear();
                    if (dataSnapshot.getValue() != null) {
                        Map<String, Boolean> friendsData = (Map<String, Boolean>) dataSnapshot.getValue();
                        for (String key : friendsData.keySet()) {
                            if (friendsData.get(key)) {
                                listingFriendList(key);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

//            // 실시간 친구목록 업데이트
//            O
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    String uid = dataSnapshot.getKey().toString();
//                    Boolean isAdded = (Boolean) dataSnapshot.getValue();
//
//                    refreshList(uid, isAdded);
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                    String uid = dataSnapshot.getKey().toString();
//                    Boolean isAdded = (Boolean) dataSnapshot.getValue();
//
//                    refreshList(uid, isAdded);
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//                    String uid = dataSnapshot.getKey().toString();
//                    refreshList(uid, false);
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
        }
    }

    @OnItemClick(R.id.friends_listView)
    public void onFriendsItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String friendUID = friendsManager.getFriendsList().get(position).getUid();

        Intent intent = new Intent(getActivity(), ChattingActivity.class);
        intent.putExtra("friendUID", friendUID);
        startActivity(intent);
    }

    @OnClick(R.id.add_friend)
    public void onAddFriendClick() {
        startActivity(new Intent(getActivity(), FriendsAddActivity.class));
    }

    public void listingFriendList(final String uid) {
        databaseReference.child("users/").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    UserData friendData = dataSnapshot.getValue(UserData.class);
                    friendsManager.addFriend(friendData);
                    adapter.add(friendData.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void refreshList(String uid, Boolean isAdded) {
        UserData friendData = friendsManager.isFriend(uid);

        if (friendData != null) {
            if (isAdded) {
                friendsManager.addFriend(friendData);
                adapter.add(friendData.getName());
            } else {
                friendsManager.removeFriend(friendData);
                adapter.remove(friendData.getName());
            }
        } else {
            friendsManager.addFriend(friendData);
            adapter.add(friendData);
        }
    }
}
