package futurewiz.cou.kr.firebasechat.frends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import futurewiz.cou.kr.firebasechat.R;
import futurewiz.cou.kr.firebasechat.base.BaseFragment;

/**
 * Created by bag-yongtae on 2017. 12. 15..
 * 친구목록 Fragment
 */

public class FriendsListFragment extends BaseFragment {

    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setView(inflater.inflate(R.layout.fragment_friends_list, container, false));

        return view;
    }
}
