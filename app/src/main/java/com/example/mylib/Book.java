package com.example.mylib;

public class Book {
    private String book_name;
    private String author_name;
    private String date;
    private int pages;
    private String description;
    private String genre;
    private String status;
    private int photo;

    public Book(String book_name, String author_name, String date, int pages, String description, String genre, String status, int photo) {
        this.book_name = book_name;
        this.author_name = author_name;
        this.date = date;
        this.pages = pages;
        this.description = description;
        this.genre = genre;
        this.status = status;
        this.photo = photo;
    }
    public Book() {}

    public int getPages() {
        return pages;
    }

    public int getPhoto() {
        return photo;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getStatus() {
        return status;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}