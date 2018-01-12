package futurewiz.cou.kr.firebasechat.chat;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;
import futurewiz.cou.kr.firebasechat.login.UserData;

/**
 * Created by my on 2017-12-09.
 * 채팅방 Activity
 */

public class ChattingActivity extends BaseActivity {

    @BindView(R.id.chat_listView)
    ListView chatListView;

    @BindView(R.id.message_editText)
    EditText messageEditText;

    @BindView(R.id.send_button)
    Button sendButton;

    private String roomID = "";
    private ArrayAdapter adapter;

    private String myUID = "";
    private String friendUID = "";
    private UserData myUserData;
    private UserData friendUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chatting);
        super.onCreate(savedInstanceState);

        setTitle("채팅방");

        myUID = authManager.getFirebaseUser().getUid();
        friendUID = getIntent().getStringExtra("friendUID");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        chatListView.setAdapter(adapter);

        connectChat();
    }

    @OnClick(R.id.send_button)
    public void buttonOnClick() {
        // 채팅 전송
        if (!roomID.isEmpty()) {
            String sendMessage = messageEditText.getText().toString();

            if (!sendMessage.isEmpty()) {
                ChatData chatData = new ChatData();
                chatData.setUID(authManager.getFirebaseUser().getUid());
                chatData.setMessage(messageEditText.getText().toString());
                chatData.setTimestamp(1);

                databaseReference.child("rooms/").child(roomID).push().setValue(chatData);

                messageEditText.setText("");
            } else {
                Toast.makeText(this, "내용을 입력하세요.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // 채팅방 연결
    public void connectChat() {
        // 상대방 UserData 요청
        databaseReference.child("users").child(friendUID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 나의 UserData
                myUserData = authManager.getUserData();
                // 상대방 UserData
                friendUserData = dataSnapshot.getValue(UserData.class);

                if (!friendUID.isEmpty()) {
                    // 채팅방 번호 생성
                    roomID = myUID.compareTo(friendUID) >= 0 ? (friendUID + myUID).hashCode() + "" : "" + (myUID + friendUID).hashCode();

                    if (!roomID.isEmpty()) {
                        databaseReference.child("rooms/").child(roomID).addChildEventListener(childEventListner);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private ChildEventListener childEventListner = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            ChatData chatData = dataSnapshot.getValue(ChatData.class);
            String userName = "";

            if (myUID.equals(chatData.getUID())) {
                userName = myUserData.getName();
            } else {
                userName = friendUserData.getName();
            }

            adapter.add(userName + " : " + chatData.getMessage());

            chatListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
