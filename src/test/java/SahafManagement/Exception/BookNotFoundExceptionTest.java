package SahafManagement.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "This book not found";
        BookNotFoundException exception = new BookNotFoundException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

}