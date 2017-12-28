package futurewiz.cou.kr.firebasechat.frends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;
import futurewiz.cou.kr.firebasechat.base.BaseFragment;
import futurewiz.cou.kr.firebasechat.login.UserData;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 친구추가 Activity
 */

public class Friends_addActivity extends BaseActivity {

    @BindView(R.id.find_email)
    EditText findEmailEditTexxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_friends_add);
        super.onCreate(savedInstanceState);

        setTitle("친구추가");
    }
    public void onBackButtonClicked(View v){
        Toast.makeText(getApplicationContext(),"돌아가기 버튼 누름", Toast.LENGTH_LONG).show();
        finish();
    }

    @OnClick(R.id.add_friend)
    public void addFriend() {
        String emailText = findEmailEditTexxt.getText().toString();
        if (emailText.isEmpty()) {
            Toast.makeText(this, "이메일 주소를 입력해주세요.", Toast.LENGTH_LONG);
        } else {
            databaseReference.child("users").orderByChild("email").equalTo(findEmailEditTexxt.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        String id = ((HashMap) dataSnapshot.getValue()).keySet().iterator().next().toString();
                        if (id.equals(authManager.getFirebaseUser().getUid())) {
                            Toast.makeText(Friends_addActivity.this, "자신의 이메일 주소입니다.", Toast.LENGTH_LONG);
                        } else {
                            HashMap userMap = (HashMap) ((HashMap) dataSnapshot.getValue()).get(id);
                            FriendData friendData = new FriendData(userMap.get("name").toString(), userMap.get("email").toString());

                            FriendsManager friendsManager = FriendsManager.getInstance();
                            if (friendsManager.isFriend(friendData)) {
                                Toast.makeText(Friends_addActivity.this, "이미 친구입니다.", Toast.LENGTH_LONG);
                            } else {friendsManager.addFriend(friendData);
                                Toast.makeText(Friends_addActivity.this, "친구 추가 완료.", Toast.LENGTH_LONG);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(Friends_addActivity.this, "이메일 주소를 확인해 주세요.", Toast.LENGTH_LONG);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
