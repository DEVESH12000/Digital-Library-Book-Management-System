package com.digitallibrary.service;

import com.digitallibrary.model.Book;
import com.digitallibrary.repository.BookRepository;
import com.digitallibrary.util.InputValidator;

import java.util.List;
import java.util.Scanner;



/**
 * Provides services for library operations.
 */
public class LibraryService {
    private final BookRepository bookRepository;
    private final Scanner scanner;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.scanner = new Scanner(System.in);
    }

    public void addBook() {
        System.out.println("\n=== Add a New Book ===");

        String id = promptForValidInput("Enter Book ID: ", InputValidator::validateBookId);
        if (bookRepository.containsBookId(id)) {
            System.out.println("Error: A book with this ID already exists.");
            return;
        }

        String title = promptForValidInput("Enter Title: ", InputValidator::validateNonEmptyString);
        String author = promptForValidInput("Enter Author: ", InputValidator::validateNonEmptyString);
        String genre = promptForNonEmptyString("Enter Genre: ");
        Book.AvailabilityStatus availability = promptForAvailabilityStatus();

        Book newBook = new Book(id, title, author, genre, availability);
        bookRepository.addBook(newBook);
        System.out.println("Book added successfully!");
    }

    public void viewAllBooks() {
        System.out.println("\n=== All Books ===");
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found in the library.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void searchBooks() {
        System.out.println("\n=== Search Books ===");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        System.out.print("Choose an option (1-2): ");

        int choice = InputValidator.validateIntegerInput(scanner, 1, 2);
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                searchById();
                break;
            case 2:
                searchByTitle();
                break;
        }
    }

    private void searchById() {
        String id = promptForValidInput("Enter Book ID: ", InputValidator::validateBookId);
        Book book = bookRepository.getBookById(id);
        if (book != null) {
            System.out.println("Found book: " + book);
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    private void searchByTitle() {
        String title = promptForNonEmptyString("Enter Title: ");
        List<Book> books = bookRepository.getBooksByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with title: " + title);
        } else {
            System.out.println("Found " + books.size() + " book(s):");
            books.forEach(System.out::println);
        }
    }

    public void updateBook() {
        System.out.println("\n=== Update Book ===");
        String id = promptForValidInput("Enter Book ID to update: ", InputValidator::validateBookId);

        Book book = bookRepository.getBookById(id);
        if (book == null) {
            System.out.println("No book found with ID: " + id);
            return;
        }

        System.out.println("Current book details: " + book);
        System.out.println("Leave field blank to keep current value.");

        String newTitle = promptForOptionalString("Enter new Title: ", book.getTitle());
        String newAuthor = promptForOptionalString("Enter new Author: ", book.getAuthor());
        String newGenre = promptForOptionalString("Enter new Genre: ", book.getGenre());
        Book.AvailabilityStatus newAvailability = promptForOptionalAvailabilityStatus(book.getAvailability());

        Book updatedBook = new Book(
            book.getId(),
            newTitle,
            newAuthor,
            newGenre,
            newAvailability
        );

        if (bookRepository.updateBook(updatedBook)) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Failed to update book.");
        }
    }

    public void deleteBook() {
        System.out.println("\n=== Delete Book ===");
        String id = promptForValidInput("Enter Book ID to delete: ", InputValidator::validateBookId);

        if (bookRepository.deleteBook(id)) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    private String promptForValidInput(String prompt, InputValidator.Validator<String> validator) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                validator.validate(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String promptForNonEmptyString(String prompt) {
        return promptForValidInput(prompt, InputValidator::validateNonEmptyString);
    }

    private String promptForOptionalString(String prompt, String currentValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentValue : input;
    }

    private Book.AvailabilityStatus promptForAvailabilityStatus() {
        System.out.println("Availability Status:");
        System.out.println("1. Available");
        System.out.println("2. Checked Out");
        System.out.print("Choose an option (1-2): ");

        int choice = InputValidator.validateIntegerInput(scanner, 1, 2);
        scanner.nextLine(); // Consume newline

        return choice == 1 
            ? Book.AvailabilityStatus.AVAILABLE 
            : Book.AvailabilityStatus.CHECKED_OUT;
    }

    private Book.AvailabilityStatus promptForOptionalAvailabilityStatus(Book.AvailabilityStatus currentStatus) {
        System.out.println("Current Availability: " + currentStatus);
        System.out.println("Update Availability? (y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        
        if (choice.equals("y")) {
            return promptForAvailabilityStatus();
        }
        return currentStatus;
    }
}