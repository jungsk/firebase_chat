package futurewiz.cou.kr.firebasechat.frends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;
import futurewiz.cou.kr.firebasechat.login.UserData;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 친구추가 Activity
 */

public class FriendsAddActivity extends BaseActivity {

    @BindView(R.id.find_email)
    EditText findEmailEditTexxt;

    FriendsManager friendsManager = FriendsManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_friends_add);
        super.onCreate(savedInstanceState);

        setTitle("친구추가");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add_friend)
    public void onAddFriendClick() {
        String emailText = findEmailEditTexxt.getText().toString();
        if (emailText.isEmpty()) {
            Toast.makeText(this, "이메일 주소를 입력해주세요.", Toast.LENGTH_LONG).show();
        } else {
            databaseReference.child("users").orderByChild("email").equalTo(findEmailEditTexxt.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        String uid = ((HashMap) dataSnapshot.getValue()).keySet().iterator().next().toString();
                        if (uid.equals(authManager.getFirebaseUser().getUid())) {
                            Toast.makeText(FriendsAddActivity.this, "자신의 이메일 주소입니다.", Toast.LENGTH_LONG).show();
                        } else {
                            if (friendsManager.isFriend(uid) != null) {
                                Toast.makeText(FriendsAddActivity.this, "이미 등록된 친구입니다.", Toast.LENGTH_LONG).show();
                            } else {
                                addFriend(uid);
                            }
                        }
                    } else {
                        Toast.makeText(FriendsAddActivity.this, "가입되지 않은 이메일 주소입니다.\n이메일 주소를 확인해 주세요.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(FriendsAddActivity.this, "에러.", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void addFriend(final String friendUID) {
        databaseReference.child("users/").child(friendUID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    UserData friendData = dataSnapshot.getValue(UserData.class);
                    friendsManager.addFriend(friendData);

                    databaseReference.child("friends").child(authManager.getFirebaseUser().getUid()).child(friendData.getUid()).setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(FriendsAddActivity.this, "친구 추가 완료", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(FriendsAddActivity.this, "친구 추가 실패", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
