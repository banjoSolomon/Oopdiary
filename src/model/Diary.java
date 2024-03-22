package model;

import java.util.ArrayList;
import java.util.List;

public class Diary {

    private String username;
    private String password;
    private List<Entry> enteries = new ArrayList<Entry>();
    private List<Diary> diaryList;
    private boolean locked = true;


    public Diary(String username, String password) {

        this.password = password;
        this.username = username;
        this.diaryList = new ArrayList<>();
    }

    public Diary() {

    }

    public String getUsername() {
        return username;
    }


    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getPassword() {
        return password;
    }
}