package diaryFIle.requests;

import lombok.NonNull;

public class LoginRequest {
    private String username;

    private String password;

    public LoginRequest() {

    }


    public void setUserName(String username) {
        this.username = username.toLowerCase();
    }

    public String getUserName() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}


