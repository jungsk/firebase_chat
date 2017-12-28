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
    private List<FriendData> friendsList = new ArrayList();
    public static FriendsManager getInstance() {
        return instance;
    }

    public List<FriendData> getFriendsList() {
        return friendsList;
    }

    public void addFriend(FriendData friendData) {
        friendsList.add(friendData);
    }

    public void removeFriend(FriendData removeFrinedData) {
        friendsList.remove(removeFrinedData);
    }

    public Boolean isFriend(FriendData friendData) {
        Boolean isFriend = false;
        for (FriendData friendData1: friendsList) {
            if (friendData1.getEmail().equals(friendData.getEmail())) {
                isFriend = true;
            }
        }

        return isFriend;
    }
}
