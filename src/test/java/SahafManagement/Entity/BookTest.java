package SahafManagement.Entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    @Mock
    private Bookstore mockBookstore;
    @Mock
    private BookRental mockBookRental;
    @Mock
    private User mockUser;

    private Book book;

    @Before
    public void setUp() {
        book = new Book();
        book.setBookName("Test Book 1");
        book.setBookBookstores(Collections.singletonList(mockBookstore));
        book.setUserBookRental(Collections.singletonList(mockBookRental));
        book.setBooksUsers(Collections.singletonList(mockUser));
    }

    @Test
    public void testGetBookId() {
        Long expectedId = 1L;
        book.setBookId(expectedId);
        Long actualId = book.getBookId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetBookName() {
        String expectedName = "Test Book 1";
        String actualName = book.getBookName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetBookBookstores() {
        List<Bookstore> expectedList = Collections.singletonList(mockBookstore);
        List<Bookstore> actualList = book.getBookBookstores();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testGetUserBookRental() {
        List<BookRental> expectedList = Collections.singletonList(mockBookRental);
        List<BookRental> actualList = book.getUserBookRental();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testGetBooksUsers() {
        List<User> expectedList = Collections.singletonList(mockUser);
        List<User> actualList = book.getBooksUsers();
        assertEquals(expectedList, actualList);
    }
}