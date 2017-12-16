package futurewiz.cou.kr.firebasechat.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.BindViews;
import futurewiz.cou.kr.firebasechat.MainActivity;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;

/**
 * Created by qna116 on 2017-12-15.
 * 로그인 Activity
 */

public class LoginActivity extends BaseActivity {

    EditText id, pw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        setTitle("로그인");

        id = (EditText)findViewById(R.id.id_email);
        pw = (EditText)findViewById(R.id.id_pw);

        Button loginup = (Button)findViewById(R.id.loginup);
        loginup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.equals("") && pw.equals("")) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }
}
