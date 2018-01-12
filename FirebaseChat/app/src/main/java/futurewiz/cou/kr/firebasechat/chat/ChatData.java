package futurewiz.cou.kr.firebasechat.chat;

/**
 * Created by my on 2017-11-25.
 * 채팅 Data
 */

public class ChatData {
    private String uid;
    private String message;
    private int timestamp;

    public String getUID() {
        return uid;
    }

    public void setUID(String uid) {
        this.uid = uid;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
