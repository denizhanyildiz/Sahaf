package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.BookRental;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Entity.User;
import SahafManagement.Repository.IBookRentalRepository;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IBookstoreRepository;
import SahafManagement.Repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRentalServiceTest {
    @Mock
    private IBookRentalRepository bookRentalRepository;

    @Mock
    private IBookRepository bookRepository;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IBookstoreRepository bookstoreRepository;

    @InjectMocks
    private BookRentalService bookRentalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRentBook() throws Exception {
        Long userId = 1L;
        Long bookstoreId = 2L;
        Long bookId = 3L;
        LocalDate rentalDate = LocalDate.now();
        LocalDate returnDate = rentalDate.plusDays(7);

        User user = new User();
        user.setUserId(userId);

        Bookstore bookstore = new Bookstore();
        bookstore.setBookstoreId(bookstoreId);

        Book book = new Book();
        book.setBookId(bookId);

        List<Book> bookstoreBooks = new ArrayList<>();
        bookstoreBooks.add(book);
        bookstore.setBookstoreBooks(bookstoreBooks);

        List<BookRental> bookRentals = new ArrayList<>();
        book.setUserBookRental(bookRentals);

        List<User> bookUsers = new ArrayList<>();
        book.setBooksUsers(bookUsers);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookstoreRepository.findById(bookstoreId)).thenReturn(Optional.of(bookstore));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(bookRentalRepository.save(any(BookRental.class))).thenReturn(new BookRental());

        bookRentalService.rentBook(userId, bookstoreId, bookId, rentalDate, returnDate);

        assertTrue(book.getBooksUsers().contains(user));
        assertTrue(book.getUserBookRental().size() == 1);
    }

}
