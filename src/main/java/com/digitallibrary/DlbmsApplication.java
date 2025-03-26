package com.digitallibrary;

import com.digitallibrary.repository.BookRepository;
import com.digitallibrary.service.LibraryService;
import com.digitallibrary.util.InputValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DlbmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DlbmsApplication.class, args);

	BookRepository bookRepository = new BookRepository();
	LibraryService libraryService = new LibraryService(bookRepository);
	Scanner scanner = new Scanner(System.in);

	boolean running = true;
        while (running) {
		displayMenu();
		System.out.print("Choose an option (1-6): ");

		try {
			int choice = InputValidator.validateIntegerInput(scanner, 1, 6);
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					libraryService.addBook();
					break;
				case 2:
					libraryService.viewAllBooks();
					break;
				case 3:
					libraryService.searchBooks();
					break;
				case 4:
					libraryService.updateBook();
					break;
				case 5:
					libraryService.deleteBook();
					break;
				case 6:
					running = false;
					System.out.println("Exiting the system. Goodbye!");
					break;
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}

		if (running) {
			System.out.println("\nPress Enter to continue...");
			scanner.nextLine();
		}
	}
        scanner.close();
}

	private static void displayMenu() {
		System.out.println("\n=== Library Management System ===");
		System.out.println("1. Add a Book");
		System.out.println("2. View All Books");
		System.out.println("3. Search Books");
		System.out.println("4. Update Book Details");
		System.out.println("5. Delete a Book");
		System.out.println("6. Exit");
	}
}
