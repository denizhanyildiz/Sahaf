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
public class BookstoreTest {

    @Mock
    private Book mockBook;

    private Bookstore bookstore;

    @Before
    public void setUp() {
        bookstore = new Bookstore();
        bookstore.setBookstoreName("Bookstore 1");
        bookstore.setBookstoreAddress("Istanbul");
        bookstore.setBookstoreBooks(Collections.singletonList(mockBook));
    }

    @Test
    public void testGetBookstoreId() {
        Long expectedId = 1L;
        bookstore.setBookstoreId(expectedId);
        Long actualId = bookstore.getBookstoreId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetBookstoreName() {
        String expectedName = "Bookstore 1";
        String actualName = bookstore.getBookstoreName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetBookstoreAddress() {
        String expectedAddress = "Istanbul";
        String actualAddress = bookstore.getBookstoreAddress();
        assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void testGetBookstoreBooks() {
        List<Book> expectedList = Collections.singletonList(mockBook);
        List<Book> actualList = bookstore.getBookstoreBooks();
        assertEquals(expectedList, actualList);
    }
}