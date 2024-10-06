package com.example.apimicroservicio3;

public class ReviewDTO {
    private String userName;
    private String userEmail;
    private int rating;
    private String comment;

    // Constructor, Getters y Setters
    public ReviewDTO(String userName, String userEmail, int rating, String comment) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.rating = rating;
        this.comment = comment;
    }
    // Getters y Setters


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
