package ru.maxima.babayan.project1.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int bookId;
    private int personId;
    @NotEmpty(message = "Title shouldn't be empty")
    @Size(min = 2, max = 50, message = "Title should between 2 and 50 characters")
    private String title;

    @NotEmpty(message = "Author shouldn't be empty")
    @Size(min = 2, max = 50, message = "Author should between 2 and 50 characters")
    private String author;

//    @NotEmpty(message = "Year of publishing shouldn't be empty")
    @Min(value = 1800, message = "Year of publishing should be greater than 1700")
    private int yearOfPublishing;

    public Book(int bookId, int personId, String title, String author, int yearOfPublishing) {
        this.bookId = bookId;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }

    public Book() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
