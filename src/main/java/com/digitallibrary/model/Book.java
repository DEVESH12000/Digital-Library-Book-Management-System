package com.digitallibrary.model;

/**
 * Represents a book in the library system.
 */
public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private AvailabilityStatus availability;

    public enum AvailabilityStatus {
        AVAILABLE("Available"),
        CHECKED_OUT("Checked Out");

        private final String displayName;

        AvailabilityStatus(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public Book(String id, String title, String author, String genre, AvailabilityStatus availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public AvailabilityStatus getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityStatus availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s | Title: %s | Author: %s | Genre: %s | Status: %s",
                id, title, author, genre, availability
        );
    }
}