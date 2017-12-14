package futurewiz.cou.kr.firebasechat.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;

/**
 * Created by yongtae on 2017. 12. 14..
 */

public class BaseActivity extends AppCompatActivity {

    protected FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    protected DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}