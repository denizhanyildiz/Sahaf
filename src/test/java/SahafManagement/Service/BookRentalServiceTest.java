package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.BookRental;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Entity.User;
import SahafManagement.Repository.IBookRentalRepository;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IBookstoreRepository;
import SahafManagement.Repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRentBook() throws Exception {
        // Given
        Long userId = 1L;
        Long bookstoreId = 2L;
        Long bookId = 3L;
        LocalDate rentalDate = LocalDate.now();
        LocalDate returnDate = LocalDate.now().plusDays(7);

        User user = new User();
        user.setUserId(userId);

        Bookstore bookstore = new Bookstore();
        bookstore.setBookstoreId(bookstoreId);

        Book book = new Book();
        book.setBookId(bookId);
        List<BookRental> rentalList = new ArrayList<>();
        book.setUserBookRental(rentalList);
        List<User> userList = new ArrayList<>();
        book.setBooksUsers(userList);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookstoreRepository.findById(bookstoreId)).thenReturn(Optional.of(bookstore));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRentalRepository.save(any(BookRental.class))).thenReturn(null);
        when(bookRepository.save(any(Book.class))).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(null);

        bookRentalService.rentBook(userId, bookstoreId, bookId, rentalDate, returnDate);

        verify(bookRentalRepository, times(1)).save(any(BookRental.class));

        Assertions.assertEquals(1, book.getUserBookRental().size());
        Assertions.assertEquals(rentalDate, book.getUserBookRental().get(0).getRentalDate());
        Assertions.assertEquals(returnDate, book.getUserBookRental().get(0).getReturnDate());
        Assertions.assertEquals(bookstore, book.getUserBookRental().get(0).getBookstore());

        Assertions.assertEquals(1, book.getBooksUsers().size());
        Assertions.assertEquals(user, book.getBooksUsers().get(0));
    }

}
