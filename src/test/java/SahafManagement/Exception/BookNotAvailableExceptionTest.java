package SahafManagement.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookNotAvailableExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "This book is not available";
        BookNotAvailableException exception = new BookNotAvailableException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

}