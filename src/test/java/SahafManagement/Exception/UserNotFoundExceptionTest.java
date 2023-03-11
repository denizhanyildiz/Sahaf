package SahafManagement.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "This user nor found";
        UserNotFoundException exception = new UserNotFoundException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

}