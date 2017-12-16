package futurewiz.cou.kr.firebasechat.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;


/**
 * Created by qna116 on 2017-12-15.
 * 회원가입 Activity
 */

public class RegisterActivity extends BaseActivity {

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

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

        setTitle("회원가입");
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
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

        this.registerEmail(emailText, passwordText);
    }

    public  void registerEmail(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                     // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(RegisterActivity.this, "회원가입 완료.",  Toast.LENGTH_SHORT).show();
                    finish();
                 } else {
                     // If sign in fails, display a message to the user.
                    Toast.makeText(RegisterActivity.this, "회원가입 실패.",  Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
}
