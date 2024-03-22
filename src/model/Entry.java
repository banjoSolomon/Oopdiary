package model;

import java.time.LocalDateTime;

public class Entry {
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


    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
