package futurewiz.cou.kr.firebasechat.frends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnItemClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseFragment;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 친구목록 Fragment
 */

public class FriendsListFragment extends BaseFragment {

    @BindView(R.id.friends_listView)
    ListView friendsListView;

    private ArrayAdapter adapter;

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

        databaseReference.child("friends/" + authManager.getFirebaseUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Map<String, FriendData> friendsData = (Map<String, FriendData>) dataSnapshot.getValue();
                    for (String key :friendsData.keySet()) {
//                        FriendData friendData = (FriendData) friendsData.get(key);
//                        adapter.add(friendData.getName());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    @OnItemClick(R.id.friends_listView)
    public void onFriendsItemClick(AdapterView<?> adapterView, View view, int position, long l) {

    }
}
