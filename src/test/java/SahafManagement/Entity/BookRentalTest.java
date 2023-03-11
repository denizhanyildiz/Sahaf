package SahafManagement.Entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BookRentalTest {

    @Mock
    private Book mockBook;
    @Mock
    private Bookstore mockBookstore;

    private BookRental bookRental;

    @Before
    public void setUp() {
        bookRental = new BookRental();
        bookRental.setBook(mockBook);
        bookRental.setBookstore(mockBookstore);
        bookRental.setRentalDate(LocalDate.now());
    }

    @Test
    public void testGetBookRentalId() {
        Long expectedId = 1L;
        bookRental.setBookRentalId(expectedId);
        Long actualId = bookRental.getBookRentalId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetRentalDate() {
        LocalDate expectedDate = LocalDate.now();
        LocalDate actualDate = bookRental.getRentalDate();
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testGetReturnDate() {
        LocalDate expectedDate = null;
        LocalDate actualDate = bookRental.getReturnDate();
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testGetBook() {
        Book actualBook = bookRental.getBook();
        assertEquals(mockBook, actualBook);
    }

    @Test
    public void testGetBookstore() {
        Bookstore actualBookstore = bookRental.getBookstore();
        assertEquals(mockBookstore, actualBookstore);
    }
}