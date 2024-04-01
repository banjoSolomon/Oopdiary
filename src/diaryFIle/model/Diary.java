package diaryFIle.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Diary {
    @Id
    private String username;
    private String password;
    private List<Entry> entries = new ArrayList<Entry>();
    private List<Diary> diaryList;
    private boolean locked = true;


    public Diary(String username, String password) {

        this.password = password;
        this.username = username;
        this.diaryList = new ArrayList<>();
    }

    public Diary() {


    }

    public void setUserName(String username) {
        this.username = username;
    }
}