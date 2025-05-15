package chat.app.Database;

public class UserDB {
    private String username;
    private String email;
    private boolean isOnline;

    public UserDB(String username, String email) {
        this.username = username;
        this.email = email;
        this.isOnline = false;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
