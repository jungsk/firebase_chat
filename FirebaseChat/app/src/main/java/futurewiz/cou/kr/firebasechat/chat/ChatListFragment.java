package futurewiz.cou.kr.firebasechat.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseFragment;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 */

public class ChatListFragment extends BaseFragment {

    @BindView(R.id.button)
    Button button;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.button)
    public void buttonOnClick() {
        startActivity(new Intent(getActivity(), ChattingActivity.class));
    }
}
