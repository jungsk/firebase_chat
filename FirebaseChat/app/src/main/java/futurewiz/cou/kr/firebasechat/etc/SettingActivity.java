package futurewiz.cou.kr.firebasechat.etc;

import android.os.Bundle;
import android.support.annotation.Nullable;

import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseActivity;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 설정 Activity
 */

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);

        setTitle("설정");
    }
}
