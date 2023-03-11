package SahafManagement.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "This bookstore not found";
        BookstoreNotFoundException exception = new BookstoreNotFoundException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

}