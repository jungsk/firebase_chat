package futurewiz.cou.kr.firebasechat.etc;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 설정 Activity
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.allim)
    Switch allim;
    @BindView(R.id.sign_out)
    Button signOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);

        setTitle("설정");
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkAllimSetting();
    }

    public void checkAllimSetting() {
        databaseReference.child("users/" + authManager.getFirebaseUser().getUid() + "/settings/push").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean bool = (Boolean) dataSnapshot.getValue();
                allim.setChecked(bool);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnCheckedChanged(R.id.allim)
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
    public void onUserDeleteClick(){
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

}
