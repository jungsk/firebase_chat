package futurewiz.cou.kr.firebasechat.chat;

/**
 * Created by my on 2017-11-25.
 * 채팅 Data
 */

public class ChatData {
    private String userId;
    private String message;
    private int timestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
