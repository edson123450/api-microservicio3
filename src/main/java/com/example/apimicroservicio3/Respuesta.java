package com.example.apimicroservicio3;

public class Respuesta {
    private String message;
    private int rating;
    private String comment;

    // Constructor
    public Respuesta(String message, int rating, String comment) {
        this.message = message;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}