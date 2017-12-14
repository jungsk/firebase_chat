package futurewiz.cou.kr.firebasechat.chatting;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;

/**
 * Created by my on 2017-12-09.
 */

public class ChattingActivity  extends BaseActivity {

    @BindView(R.id.chat_listView)
    ListView chatListView;

    @BindView(R.id.message_editText)
    EditText messageEditText;

    @BindView(R.id.send_button)
    Button sendButton;

    private String userName;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chatting);
        super.onCreate(savedInstanceState);

        userName = "user" + new Random().nextInt(10000);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        chatListView.setAdapter(adapter);

        databaseReference.child("message").addChildEventListener(childEventListner);
    }

    @OnClick(R.id.send_button)
    public void buttonOnClick() {
        String sendMessage = messageEditText.getText().toString();

        if (!sendMessage.isEmpty()) {
            ChatData chatData = new ChatData();
            chatData.setUserName(userName);
            chatData.setMessage(messageEditText.getText().toString());

            databaseReference.child("message").push().setValue(chatData);
            messageEditText.setText("");
        } else {
            Toast.makeText(this, "내용을 입력하세요.", Toast.LENGTH_LONG);
        }
    }

    private ChildEventListener childEventListner = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            ChatData chatData = dataSnapshot.getValue(ChatData.class);
            adapter.add(chatData.getUserName() + " : " + chatData.getMessage());

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
