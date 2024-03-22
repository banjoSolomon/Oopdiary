package dtos.requests;

public class RegisterRequest {
    private String username;
    private String password;

    public RegisterRequest() {

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
