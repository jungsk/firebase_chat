package futurewiz.cou.kr.firebasechat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;

/**
 * Created by yongtae on 2017. 12. 14..
 */

public class BaseFragment extends Fragment {

    protected FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    protected DatabaseReference databaseReference = firebaseDatabase.getReference();

    private View view;

    protected void setView(View view) {
        this.view = view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (view != null) {
            ButterKnife.bind(view);
        }
    }
}
