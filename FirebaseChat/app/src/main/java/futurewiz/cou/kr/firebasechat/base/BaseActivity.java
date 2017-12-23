package futurewiz.cou.kr.firebasechat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.login.AuthManager;

/**
 * Created by yongtae on 2017. 12. 14..
 */

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    protected DatabaseReference databaseReference = firebaseDatabase.getReference();
    protected AuthManager authManager = AuthManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }
}
