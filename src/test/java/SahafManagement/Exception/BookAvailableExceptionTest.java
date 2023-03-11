package SahafManagement.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookAvailableExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "This book is available";
        BookAvailableException exception = new BookAvailableException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}