package com.digitallibrary.repository;

import com.digitallibrary.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles data storage and retrieval for books.
 */
public class BookRepository {
    private final Map<String, Book> booksById;
    private final Map<String, List<Book>> booksByTitle;

    public BookRepository() {
        booksById = new HashMap<>();
        booksByTitle = new HashMap<>();
    }

    public void addBook(Book book) {
        if (book == null || book.getId() == null || book.getTitle() == null) {
            throw new IllegalArgumentException("Book, ID, and title cannot be null");
        }

        booksById.put(book.getId(), book);
        booksByTitle.computeIfAbsent(book.getTitle().toLowerCase(), k -> new ArrayList<>()).add(book);
    }

    public Book getBookById(String id) {
        return booksById.get(id);
    }

    public List<Book> getBooksByTitle(String title) {
        return booksByTitle.getOrDefault(title.toLowerCase(), new ArrayList<>());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(booksById.values());
    }

    public boolean updateBook(Book book) {
        if (book == null || book.getId() == null || !booksById.containsKey(book.getId())) {
            return false;
        }

        // Remove old title mapping if title changed
        Book existingBook = booksById.get(book.getId());
        if (!existingBook.getTitle().equalsIgnoreCase(book.getTitle())) {
            List<Book> booksWithSameTitle = booksByTitle.get(existingBook.getTitle().toLowerCase());
            if (booksWithSameTitle != null) {
                booksWithSameTitle.remove(existingBook);
            }
        }

        booksById.put(book.getId(), book);
        booksByTitle.computeIfAbsent(book.getTitle().toLowerCase(), k -> new ArrayList<>()).add(book);
        return true;
    }

    public boolean deleteBook(String id) {
        Book book = booksById.remove(id);
        if (book != null) {
            List<Book> booksWithSameTitle = booksByTitle.get(book.getTitle().toLowerCase());
            if (booksWithSameTitle != null) {
                booksWithSameTitle.remove(book);
                if (booksWithSameTitle.isEmpty()) {
                    booksByTitle.remove(book.getTitle().toLowerCase());
                }
            }
            return true;
        }
        return false;
    }

    public boolean containsBookId(String id) {
        return booksById.containsKey(id);
    }
}