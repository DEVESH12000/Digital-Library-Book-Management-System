package com.digitallibrary.util;

import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Provides input validation utilities.
 */
public class InputValidator {
    @FunctionalInterface
    public interface Validator<T> {
        void validate(T input) throws IllegalArgumentException;
    }

    public static void validateBookId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty");
        }
    }

    public static void validateNonEmptyString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Field cannot be empty");
        }
    }

    public static int validateIntegerInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input < min || input > max) {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                } else {
                    return input;
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public static <T> void validateWithPredicate(T input, Predicate<T> predicate, String errorMessage) {
        if (!predicate.test(input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}