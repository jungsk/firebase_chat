package futurewiz.cou.kr.firebasechat.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;


/**
 * Created by qna116 on 2017-12-15.
 * 회원가입 Activity
 */

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.id_check_button)
    Button idCheckButton;

    @BindView(R.id.sign_button)
    Button SignupButton;

    @BindView(R.id.email_input)
    EditText EmailEditText;

    @BindView(R.id.pass_input)
    EditText PassEditText;

    @BindView(R.id.check_pass_input)
    EditText CheckpassEditText;

    private AuthManager authManager = AuthManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

        setTitle("회원가입");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @OnClick(R.id.sign_button)
    public void onSignButtonClick() {
        String emailText = EmailEditText.getText().toString();
        String passwordText = PassEditText.getText().toString();
        String checkPasswordText = CheckpassEditText.getText().toString();

        if (emailText.isEmpty()) {
            Toast.makeText(this, "이메일 주소를 입력해주세요.", Toast.LENGTH_LONG);
            return;
        }
        if (passwordText.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG);
            return;
        }
        if (checkPasswordText.isEmpty()) {
            Toast.makeText(this, "비밀번호 확인을 입력해주세요.", Toast.LENGTH_LONG);
            return;
        }

        if (!passwordText.equals(checkPasswordText)) {
            Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG);
            return;
        }

        this.signUp(emailText, passwordText);
    }

    public void signUp(String email, String password) {
        authManager.signUp(email, password, new AuthManager.SignUpListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    Toast.makeText(SignUpActivity.this, "회원가입 완료.",  Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "회원가입 실패.",  Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
