package futurewiz.cou.kr.firebasechat.frends;

import java.util.ArrayList;
import java.util.List;

import futurewiz.cou.kr.firebasechat.login.AuthManager;
import futurewiz.cou.kr.firebasechat.login.UserData;

/**
 * Created by my on 2017-12-28.
 */

public class FriendsManager {
    private static FriendsManager instance = new FriendsManager();
    private List<UserData> friendsList = new ArrayList();

    public static FriendsManager getInstance() {
        return instance;
    }

    public List<UserData> getFriendsList() {
        return friendsList;
    }

    public void addFriend(UserData friendData) {
        friendsList.add(friendData);
    }

    public void removeFriend(UserData friendData) {
        friendsList.remove(friendData);
    }

    public void clear() {
        friendsList.clear();
    }

    public UserData isFriend(UserData friendData) {
        UserData findFriendData = null;

        for (UserData _data: friendsList) {
            if (_data != null && friendData != null) {
                if (_data.getUid().equals(friendData.getUid())) {
                    findFriendData = _data;
                }
            }
        }

        return findFriendData;
    }

    public UserData isFriend(String uid) {
        UserData findFriendData = null;

        for (UserData _data: friendsList) {
            if (_data != null) {
                if (_data.getUid().equals(uid)) {
                    findFriendData = _data;
                }
            }
        }

        return findFriendData;
    }
}
