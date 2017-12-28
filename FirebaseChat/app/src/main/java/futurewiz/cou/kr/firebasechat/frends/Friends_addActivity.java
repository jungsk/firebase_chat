package futurewiz.cou.kr.firebasechat.frends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;
import futurewiz.cou.kr.firebasechat.base.BaseFragment;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 친구추가 Activity
 */

public class Friends_addActivity extends BaseActivity {

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
}
