package futurewiz.cou.kr.firebasechat.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.MainActivity;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseFragment;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 채팅방 목록 Fragment
 */

public class ChatListFragment extends BaseFragment {

    private int i;
    private ArrayAdapter arrayAdapter;
    private ArrayList arrayList;

    private Boolean boolValue;

    @BindView(R.id.chat_list)
    ListView chat_list;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1);


        chat_list.setAdapter(arrayAdapter);

        databaseReference.child("rooms").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                arrayList = new ArrayList();

                for (i=0; i<dataSnapshot.getChildrenCount(); i++) {
                    arrayList.add(child.next().getKey());

                    // 자신의 계정이 채팅에 참여할 수 있는지 없는지 확인하여 리스트에 추가할지 추가 안 할지 결정한는 코드
//                    databaseReference.child("rooms/" + arrayList.get(0) + "/users/" + "aUser").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            boolValue = (Boolean) dataSnapshot.getValue();
//
//                            // 사용자가 접속할 수 있다면
//                            if (boolValue) {
//                                arrayAdapter.add(arrayList.get(0));
//                            } else if (boolValue == null) {
//                                /* 방 만들기
//                                Map<String, Boolean> joinUsers = new HashMap<>();
//                                joinUsers.put("myUserId", true);
//                                joinUsers.put("yourUserId", true);
//                                DatabaseReference roomData = databaseReference.child("rooms").push();
//                                roomData.getKey();
//                                roomData.setValue(joinUsers);
//                                */
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
                }

                chat_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // 리스트뷰의 방 이름을 클릭하면 방으로 이동
                        if (arrayAdapter.getItem(i).equals(arrayList.get(i))) {
                            Toast.makeText(getActivity(), "확인 완료", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getActivity(), ChattingActivity.class));
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    /*
    @OnClick(R.id.button)
    public void buttonOnClick() {
        startActivity(new Intent(getActivity(), ChattingActivity.class));
    }
    */
}
