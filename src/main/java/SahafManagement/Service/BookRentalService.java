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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
            //Kullanıcıyı bul
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException( userId +" id li kullanıcı bulunamadı.."));

            //Kitapçıyı bul
            Bookstore bookstore = bookstoreRepository.findById(bookstoreId)
                    .orElseThrow(() -> new BookstoreNotFoundException(bookstoreId + " id li sahaf bulunamadı.."));

            //Kitabı bul
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(bookId + " id li kitap bulunamadı"));

            //Kitapların, kiralanabilir olduğunu kontrol et
            List<BookRental> rentals = book.getUserBookRental().stream().filter(bookRental ->
                    bookRental.getBookstore().getBookstoreId().equals(bookstore.getBookstoreId())).collect(Collectors.toList());
            for (BookRental rental : rentals) {
                if (rental.getRentalDate().isBefore(returnDate) && rental.getReturnDate().isAfter(rentalDate)) {
                    throw new BookNotAvailableException(rental.getRentalDate() + " - " + rental.getReturnDate() +
                            " tarihler arasında"+ bookstore.getBookstoreName() + " adlı sahaftaki " + "kitap farklı kullanıcıya kiralanmıştır.");
                }
            }

            //Kiralama ve geri getire tarihlerini kitap bilgisini ayarlar.
            BookRental rental = new BookRental();
            rental.setBook(book);
            rental.setRentalDate(rentalDate);
            rental.setReturnDate(returnDate);
            rental.setBookstore(bookstore);

            //Kiralamayı kullanıcı için kaydet
            rentals.add(rental);
            book.setUserBookRental(rentals);

            List<User> userList = book.getBooksUsers();
            userList.add(user);
            book.setBooksUsers(userList);

            // Değişiklikleri veri tabanına kaydet
            bookRentalRepository.save(rental);
            bookRepository.save(book);
            userRepository.save(user);
        }
    }