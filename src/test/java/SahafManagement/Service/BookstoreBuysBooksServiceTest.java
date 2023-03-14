package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Exception.BookAvailableException;
import SahafManagement.Exception.BookNotFoundException;
import SahafManagement.Exception.BookstoreNotFoundException;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IBookstoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookstoreBuysBooksServiceTest {

        @Mock
        private IBookRepository iBookRepository;
        @Mock
        private IBookstoreRepository iBookstoreRepository;
        @InjectMocks
        private BookstoreBuysBooksService bookstoreBuysBooksService;

    @Test
    public void saveBookToBookstore_WhenBookAndBookstoreExists_ShouldSaveBookToBookstore() throws BookstoreNotFoundException, BookNotFoundException, BookAvailableException {
        Long bookId = 1L;
        Long bookstoreId = 1L;
        Book book = new Book(bookId, "Test Book", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Bookstore bookstore = new Bookstore(bookstoreId, "Test Bookstore", "Test Address", new ArrayList<>());

        when(iBookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(iBookstoreRepository.findById(bookstoreId)).thenReturn(Optional.of(bookstore));
        when(iBookRepository.save(any(Book.class))).thenReturn(book);

        bookstoreBuysBooksService.saveBookToBookstore(bookId, bookstoreId);

        Mockito.verify(iBookRepository).findById(bookId);
        Mockito.verify(iBookstoreRepository).findById(bookstoreId);
        Mockito.verify(iBookRepository).save(book);

        assertEquals(1, book.getBookBookstores().size());
        assertTrue(book.getBookBookstores().contains(bookstore));
    }

    @Test(expected = BookNotFoundException.class)
    public void saveBookToBookstore_WhenBookDoesNotExist_ShouldThrowBookNotFoundException() throws BookstoreNotFoundException, BookNotFoundException, BookAvailableException {
        Long bookId = 1L;
        Long bookstoreId = 1L;

        when(iBookRepository.findById(bookId)).thenReturn(Optional.empty());

        bookstoreBuysBooksService.saveBookToBookstore(bookId, bookstoreId);
    }
    @Test(expected = BookstoreNotFoundException.class)
    public void saveBookToBookstore_WhenBookstoreDoesNotExist_ShouldThrowBookstoreNotFoundException() throws BookstoreNotFoundException, BookNotFoundException, BookAvailableException {
        Long bookId = 1L;
        Long bookstoreId = 1L;
        Book book = new Book(bookId, "Test Book", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        when(iBookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(iBookstoreRepository.findById(bookstoreId)).thenReturn(Optional.empty());

        bookstoreBuysBooksService.saveBookToBookstore(bookId, bookstoreId);
    }

    @Test(expected = BookAvailableException.class)
    public void testSaveBookToBookstore_WhenBookIsAlreadyAvailable_ShouldThrowBookAvailableException() throws Exception {
        Long bookId = 1L;
        Long bookstoreId = 2L;
        Book book = new Book();
        book.setBookId(bookId);
        Bookstore bookstore = new Bookstore();
        bookstore.setBookstoreId(bookstoreId);
        List<Book> bookstoreBooksList = new ArrayList<>();
        bookstoreBooksList.add(book);
        bookstore.setBookstoreBooks(bookstoreBooksList);
        when(iBookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(iBookstoreRepository.findById(bookstoreId)).thenReturn(Optional.of(bookstore));

        bookstoreBuysBooksService.saveBookToBookstore(bookId, bookstoreId);
    }

}