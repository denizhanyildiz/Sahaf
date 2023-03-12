package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Exception.BookAvailableException;
import SahafManagement.Exception.BookNotAvailableException;
import SahafManagement.Exception.BookNotFoundException;
import SahafManagement.Exception.BookstoreNotFoundException;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IBookstoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
saveBookToBookstore bookId ve bookstoreId parametreleri alır. bookId ile veri tabanında kitabı kontrol eder ve bulamazsa BookNotFoundException geri döner.
bookstoreId parametresi ile veri tabanında sahafları kontrol eder ve sahaf bulamazsa BookstoreNotFoundException geri döner.
Daha sonra sahaflar için kitapları, kitap ile ilişkili kitapçılar listesine ekler.
Kitapların bulunduğu kitapçıların listesini alır ve kitapçılar ekler.
Daha sonra güncellenen bu liste kitabın kitapçılarına yerleştirilir ve kaydedilir.
 */

@Service
public class BookstoreBuysBooksService {

    IBookRepository iBookRepository;
    IBookstoreRepository iBookstoreRepository;
    public BookstoreBuysBooksService(IBookRepository bookRepository, IBookstoreRepository iBookstoreRepository) {
        this.iBookRepository = bookRepository;
        this.iBookstoreRepository = iBookstoreRepository;
    }

    public void saveBookToBookstore(Long bookId, Long bookstoreId) throws BookstoreNotFoundException, BookNotFoundException, BookAvailableException {
        Book book = iBookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException("Book #"+bookId + " not found."));

        Bookstore bookstore = iBookstoreRepository.findById(bookstoreId).orElseThrow(()->new BookstoreNotFoundException("Bookstore #"+bookstoreId + " not found."));

        boolean bookAvailable = bookstore.getBookstoreBooks().stream().anyMatch(booksId -> booksId.getBookId().equals(bookId));
        if (bookAvailable) {
            throw new BookAvailableException("Book #" + bookId + " is already available in Bookstore #" + bookstoreId);
        }

        List<Book> bookstoreBooks = bookstore.getBookstoreBooks();
        bookstoreBooks.add(book);
        bookstore.setBookstoreBooks(bookstoreBooks);

        List<Bookstore> bookBookstores = book.getBookBookstores();
        bookBookstores.add(bookstore);
        book.setBookBookstores(bookBookstores);

        iBookstoreRepository.save(bookstore);
        iBookRepository.save(book);

    }
}
