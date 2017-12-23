package futurewiz.cou.kr.firebasechat.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;

/**
 * Created by qna116 on 2017-12-15.
 * 로그인 Activity
 */

public class SignInActivity extends BaseActivity {

    @BindView(R.id.id_email)
    EditText idEditText;

    @BindView(R.id.id_pw)
    EditText pwEditText;

    private AuthManager authManager = AuthManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        setTitle("로그인");

        Button loginup = (Button)findViewById(R.id.sign_up);
        loginup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 뒤로가기 막기
    }

    @OnClick(R.id.sign_in)
    public void onSignInClick() {
        String idString = idEditText.getText().toString();
        String pwString = pwEditText.getText().toString();

        if (idString.isEmpty()) {
            Toast.makeText(this, "이메일 주소를 입력하세요.", Toast.LENGTH_LONG);
            return;
        }

        if (pwString.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_LONG);
            return;
        }

        authManager.signIn(idString, pwString, new AuthManager.SignInListener() {
            @Override
            public void onResult(Boolean success) {
                if (success) {
                    finish();
                } else {
                    Toast.makeText(SignInActivity.this, "이메일 또는 비밀번호를 확인 해주세요.", Toast.LENGTH_LONG);
                }
            }
        });
    }

    @OnClick(R.id.sign_up)
    public void onSignUpClick() {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }
}
