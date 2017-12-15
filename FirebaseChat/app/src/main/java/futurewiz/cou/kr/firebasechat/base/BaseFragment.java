package futurewiz.cou.kr.firebasechat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yongtae on 2017. 12. 14..
 */

public class BaseFragment extends Fragment {

    protected FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    protected DatabaseReference databaseReference = firebaseDatabase.getReference();

    protected View view;
    protected Unbinder unbinder;

    public void setView(View view) {
        this.view = view;
        if (unbinder == null) {
            unbinder = ButterKnife.bind(this, this.view);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
