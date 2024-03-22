package dtos.requests;

public class RemoveUserRequest {
    private String username;
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

