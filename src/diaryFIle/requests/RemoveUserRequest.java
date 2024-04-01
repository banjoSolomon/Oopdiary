package diaryFIle.requests;

import lombok.NonNull;

public class RemoveUserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;

    public RemoveUserRequest() {


    }

    public void setUserName(String username) {
        this.username = username.toLowerCase();
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

