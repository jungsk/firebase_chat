package futurewiz.cou.kr.firebasechat.frends;

import java.util.Map;

/**
 * Created by my on 2017-12-28.
 */

public class FriendData {
    private String email;
    private String name;

    public FriendData(String email, String name) {
        this.email = email;
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
