package com.example.apimicroservicio3;

public class BookDetailsDTO {
    private String title;
    private String authorName;

    public BookDetailsDTO(String title, String authorName) {
        this.title = title;
        this.authorName = authorName;
    }
    // Getters y Setters


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
