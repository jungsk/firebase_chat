package futurewiz.cou.kr.firebasechat.etc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 설정 Activity
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.notice)
    Switch notice;
    @BindView(R.id.sign_out)
    Button signOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);

        setTitle("설정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkNoticeSetting();
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

    public void checkNoticeSetting() {
        databaseReference.child("users/" + authManager.getFirebaseUser().getUid() + "/settings/push").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean bool = (Boolean) dataSnapshot.getValue();
                notice.setChecked(bool);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnCheckedChanged(R.id.notice)
    public void onAllimCheckedChange(CompoundButton compoundButton, boolean b) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("users/" + authManager.getFirebaseUser().getUid() + "/settings/push", b);

        databaseReference.updateChildren(childUpdates);
    }

    @OnClick(R.id.sign_out)
    public void onSignOutClick() {
        authManager.signOut();
        finish();
    }

    @OnClick(R.id.delete)
    public void onUserDeleteClick() {
        new AlertDialog.Builder(this)
                .setTitle("회원탈퇴")
                .setMessage("회원탈퇴시 모든 데이터가 삭제됩니다.\n회원탈퇴를 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("회원탈퇴", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final String firebaseUid = authManager.getFirebaseUser().getUid();

                        authManager.getFirebaseUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    databaseReference.child("users/" + firebaseUid).removeValue();
                                    finish();
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

}
