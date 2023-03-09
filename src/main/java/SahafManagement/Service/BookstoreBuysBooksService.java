package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.Bookstore;
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

    public void saveBookToBookstore(Long bookId, Long bookstoreId) throws BookstoreNotFoundException, BookNotFoundException {
        Optional<Book> optionalBook = iBookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException("Book #"+bookId + " not found.");
        }

        Optional<Bookstore> optionalBookstore = iBookstoreRepository.findById(bookstoreId);
        if (!optionalBookstore.isPresent()) {
            throw new BookstoreNotFoundException("Bookstore #"+bookstoreId + " not found.");
        }

        Book book = optionalBook.get();
        Bookstore bookstore = optionalBookstore.get();

        List<Bookstore> bookstoreList = book.getBookBookstores();
        bookstoreList.add(bookstore);
        book.setBookBookstores(bookstoreList);
        iBookRepository.save(book);
    }
}
