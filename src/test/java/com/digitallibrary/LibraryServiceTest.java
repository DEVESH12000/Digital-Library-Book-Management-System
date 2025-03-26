package com.digitallibrary;

import com.digitallibrary.model.Book;
import com.digitallibrary.repository.BookRepository;
import com.digitallibrary.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    private BookRepository bookRepository;
    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        libraryService = new LibraryService(bookRepository);
    }

    @Test
    void addBookShouldStoreNewBook() {
        // Normally we'd mock user input, but for simplicity we'll test the repository
        Book book = new Book("123", "Test Book", "Test Author", "Test Genre", Book.AvailabilityStatus.AVAILABLE);
        bookRepository.addBook(book);
        
        assertNotNull(bookRepository.getBookById("123"));
        assertEquals(1, bookRepository.getAllBooks().size());
    }

    @Test
    void viewAllBooksShouldReturnAllBooks() {
        Book book1 = new Book("1", "Book 1", "Author 1", "Genre", Book.AvailabilityStatus.AVAILABLE);
        Book book2 = new Book("2", "Book 2", "Author 2", "Genre", Book.AvailabilityStatus.CHECKED_OUT);
        
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        
        assertEquals(2, bookRepository.getAllBooks().size());
    }

    @Test
    void searchByIdShouldFindCorrectBook() {
        Book book = new Book("123", "Test Book", "Test Author", "Test Genre", Book.AvailabilityStatus.AVAILABLE);
        bookRepository.addBook(book);
        
        assertEquals(book, bookRepository.getBookById("123"));
        assertNull(bookRepository.getBookById("999"));
    }
}