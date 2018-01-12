package futurewiz.cou.kr.firebasechat.frends;

/**
 * Created by my on 2017-12-28.
 */

public class FriendData {
    private String uid;
    private String name;

    public FriendData(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }
    public String getUID() {
        return uid;
    }

    public void setUID(String email) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
