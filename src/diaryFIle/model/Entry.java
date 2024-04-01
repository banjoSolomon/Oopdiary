package diaryFIle.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Entry {
    @Id
    private int id;
    private String title;
    private String body;
    private final LocalDateTime dateCreated = LocalDateTime.now();
    private String author;

    public Entry(String title, String body, int id, String author) {
        this.body = body;
        this.title = title;
        this.id = id;
        this.author = author;
    }

    public Entry() {

    }

}