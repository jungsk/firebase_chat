package futurewiz.cou.kr.firebasechat.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;

/**
 * Created by qna116 on 2017-12-15.
 * 회원가입 Activity
 */

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

        setTitle("회원가입");
    }
}
