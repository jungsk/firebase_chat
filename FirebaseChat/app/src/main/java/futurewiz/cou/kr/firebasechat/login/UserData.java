package futurewiz.cou.kr.firebasechat.login;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by bag-yongtae on 2017. 12. 23..
 */

public class UserData {
    public static class UserSettings {
        public Boolean push;
    }

    private String email;
    private Map<String, Boolean> friends;
    private String name;
    private ArrayList<Map<String, Boolean>> rooms;
    private String photo;
    private UserSettings settings;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, Boolean> friends) {
        this.friends = friends;
    }

    public void AddFriend(String key, Boolean value) {
        this.friends.put(key, value);
    }

    public void RemoveFirned(int index) {
        this.friends.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Map<String, Boolean>> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Map<String, Boolean>> rooms) {
        this.rooms = rooms;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserSettings getSettings() {
        return settings;
    }

    public void setSettings(UserSettings settings) {
        this.settings = settings;
    }
}
