package com.digitallibrary;

import com.digitallibrary.util.InputValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    @Test
    void validateBookIdShouldAcceptNonEmptyString() {
        assertDoesNotThrow(() -> InputValidator.validateBookId("123"));
        assertThrows(IllegalArgumentException.class, () -> InputValidator.validateBookId(""));
    }

    @Test
    void validateNonEmptyStringShouldAcceptNonEmptyString() {
        assertDoesNotThrow(() -> InputValidator.validateNonEmptyString("Test"));
        assertThrows(IllegalArgumentException.class, () -> InputValidator.validateNonEmptyString(""));
    }
}