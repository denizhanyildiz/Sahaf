package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.BookRental;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Repository.IBookRentalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookstoreRentalReportServiceTest {

    @Mock
    private IBookRentalRepository iBookRentalRepository;

    @InjectMocks
    private BookstoreRentalReportService bookstoreRentalReportService;

    @Test
    public void testCountDailyBookRentalsFromBookstores() {
        LocalDate rentalDate = LocalDate.now();
        Book book = new Book();
        Bookstore bookstore = new Bookstore();
        book.setBookBookstores(Arrays.asList(bookstore));
        BookRental rental = new BookRental(1L, rentalDate, null, book, bookstore);
        List<BookRental> rentalList = Arrays.asList(rental);

        when(iBookRentalRepository.findBookRentalsByRentalDate(rentalDate)).thenReturn(rentalList);

        List<BookRental> dailyRentalList = bookstoreRentalReportService.countDailyBookRentalsFromBookstores(rentalDate);

        assertEquals(1, dailyRentalList.size());
        assertEquals(rental, dailyRentalList.get(0));
    }
}