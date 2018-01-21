package futurewiz.cou.kr.firebasechat.etc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;
import futurewiz.cou.kr.firebasechat.login.UserData;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 프로필 Activity
 */

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.editText)
    EditText nickName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        setTitle("프로필");
    }

    @Override
    protected void onResume() {
        super.onResume();

        databaseReference.child("users/").child(authManager.getFirebaseUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData userData = dataSnapshot.getValue(UserData.class);
                email.setText(userData.getEmail());
                nickName.setText(userData.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.change_nickname)
    public void onChangeNickNameClick() {
        databaseReference.child("users/").child(authManager.getFirebaseUser().getUid()).child("name").setValue(nickName.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    authManager.getUserData().setName(nickName.getText().toString());
                    Toast.makeText(ProfileActivity.this, "닉네임 변경 완료", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "닉네임 변경 실패", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
