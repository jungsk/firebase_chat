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

    @BindView(R.id.name_input)
    EditText nameEditText;

    @BindView(R.id.email_input)
    EditText EmailEditText;

    @BindView(R.id.pass_input)
    EditText PassEditText;

    @BindView(R.id.check_pass_input)
    EditText CheckpassEditText;

    @BindView(R.id.sign_button)
    Button SignupButton;

    private AuthManager authManager = AuthManager.getInstance();
    private String nameString;
    private String emailString;
    private String passwordString;
    private String checkPasswordString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_sign_up);
        super.onCreate(savedInstanceState);

        setTitle("회원가입");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @OnClick(R.id.sign_button)
    public void onSignButtonClick() {
        nameString = nameEditText.getText().toString();
        emailString = EmailEditText.getText().toString();
        passwordString = PassEditText.getText().toString();
        checkPasswordString = CheckpassEditText.getText().toString();

        if (nameString.isEmpty()) {
            Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_LONG);
            return;
        }
        if (emailString.isEmpty()) {
            Toast.makeText(this, "이메일 주소를 입력해주세요.", Toast.LENGTH_LONG);
            return;
        }
        if (passwordString.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG);
            return;
        }
        if (checkPasswordString.isEmpty()) {
            Toast.makeText(this, "비밀번호 확인을 입력해주세요.", Toast.LENGTH_LONG);
            return;
        }

        if (!passwordString.equals(checkPasswordString)) {
            Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG);
            return;
        }

        this.signUp(emailString, passwordString);
    }

    public void signUp(final String email, String password) {
        authManager.signUp(email, password, new AuthManager.SignUpListener() {
            @Override
            public void onResult(Boolean success, String exceptionString) {
                if (success) {
                    UserData userData = new UserData();
                    userData.setUid(authManager.getFirebaseUser().getUid());
                    userData.setEmail(emailString);
                    userData.setName(nameString);
                    userData.setPhoto("");

                    // 기본 설정
                    UserData.UserSettings userSetting = new UserData.UserSettings();
                    userSetting.push = false;
                    userData.setSettings(userSetting);

                    databaseReference.child("users").child(emailString).setValue(userData);

                    Toast.makeText(SignUpActivity.this, "회원가입 완료.",  Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "회원가입 실패." + exceptionString,  Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
