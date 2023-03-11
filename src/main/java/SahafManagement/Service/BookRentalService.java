package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.BookRental;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Entity.User;
import SahafManagement.Exception.BookNotAvailableException;
import SahafManagement.Exception.BookNotFoundException;
import SahafManagement.Exception.BookstoreNotFoundException;
import SahafManagement.Exception.UserNotFoundException;
import SahafManagement.Repository.IBookRentalRepository;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IBookstoreRepository;
import SahafManagement.Repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/*
rentBook metodunda userId, bookstoreId, bookId, rentalDate, returnDate parametre olarak alınır.
kullanıcının, sahafın ve kitabın veri tabanında olup olmadğı kontrol edilir.
Kitabın kiralanabilir olup olmadığının kontrolü yapılır eğer kitap tarihlere göre kesişiyorsa BookNotAvailableException hatasını döner.
Kitap uygunsa, kitap ve tarih bilgilerini ayarlar.
Daha sonra kitap ilgili kullanıcı ile ilişkili listeye eklenir ve daha sonra veri tabanına kadedilir.
 */

@Service
public class BookRentalService {
    private IBookRentalRepository bookRentalRepository;
    private IBookRepository bookRepository;
    private IUserRepository userRepository;
    private IBookstoreRepository bookstoreRepository;

    public BookRentalService(IBookRentalRepository bookRentalRepository, IBookRepository bookRepository, IUserRepository userRepository, IBookstoreRepository bookstoreRepository) {
        this.bookRentalRepository = bookRentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookstoreRepository = bookstoreRepository;
    }

    public void rentBook(Long userId, Long bookstoreId, Long bookId, LocalDate rentalDate, LocalDate returnDate)
            throws BookNotFoundException, UserNotFoundException, BookstoreNotFoundException, BookNotAvailableException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User #" + userId + " not found."));

        Bookstore bookstore = bookstoreRepository.findById(bookstoreId)
                .orElseThrow(() -> new BookstoreNotFoundException("Bookstore #" + bookstoreId + " not found."));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book #" + bookId + " not found"));

        boolean bookAvailable = bookstore.getBookstoreBooks().stream().anyMatch(booksId -> booksId.getBookId().equals(bookId));
        if (!bookAvailable) {
            throw new BookNotAvailableException("Book #" + bookId + " is not available in bookstore #" + bookstoreId);
        }

        List<BookRental> rentalList = book.getUserBookRental();
        for (BookRental rental : rentalList) {
            if (rental.getRentalDate().isBefore(returnDate) && rental.getReturnDate().isAfter(rentalDate)) {
                throw new BookNotAvailableException("Between " + rental.getRentalDate() + " - " + rental.getReturnDate() +
                        " the book #" + book.getBookId() + " in the bookstore #" + bookstore.getBookstoreName() + " rented by another user.");
            }
        }

        BookRental rental = new BookRental();
        rental.setBook(book);
        rental.setRentalDate(rentalDate);
        rental.setReturnDate(returnDate);
        rental.setBookstore(bookstore);

        rentalList.add(rental);
        book.setUserBookRental(rentalList);

        List<User> userList = book.getBooksUsers();
        userList.add(user);
        book.setBooksUsers(userList);

        bookRentalRepository.save(rental);
        bookRepository.save(book);
        userRepository.save(user);
    }
}