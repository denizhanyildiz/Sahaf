package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IBookstoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookstoreServiceTest {

    @Mock
    private IBookstoreRepository iBookstoreRepository;

    @Mock
    private IBookRepository iBookRepository;

    @InjectMocks
    private BookstoreService bookstoreService;

    @Test
    public void testSaveBookstore() {
        Bookstore bookstore = new Bookstore();
        bookstore.setBookstoreName("Bookstore 1");
        when(iBookstoreRepository.save(bookstore)).thenReturn(bookstore);
        Bookstore savedBookstore = bookstoreService.saveBookstore(bookstore);
        assertEquals("Bookstore 1", savedBookstore.getBookstoreName());
        Mockito.verify(iBookstoreRepository).save(bookstore);
    }

    @Test
    void deleteBookstore() {
        Long bookstoreId = 1L;
        Mockito.doNothing().when(iBookstoreRepository).deleteById(bookstoreId);
        bookstoreService.deleteBookstore(bookstoreId);
        Mockito.verify(iBookstoreRepository).deleteById(bookstoreId);

    }

    @Test
    public void updateBookstore() {
        Long bookstoreId = 1L;
        Book book1 = new Book();
        book1.setBookName("Book 1");
        book1.setBookBookstores(new ArrayList<>());
        book1.setUserBookRental(new ArrayList<>());
        book1.setBooksUsers(new ArrayList<>());
        iBookRepository.save(book1);

        Book book2 = new Book();
        book2.setBookName("Book 2");
        book2.setBookBookstores(new ArrayList<>());
        book2.setUserBookRental(new ArrayList<>());
        book2.setBooksUsers(new ArrayList<>());
        iBookRepository.save(book2);

        Bookstore bookstore = new Bookstore();
        bookstore.setBookstoreId(bookstoreId);
        bookstore.setBookstoreName("Bookstore 1");
        bookstore.setBookstoreAddress("Address 1");
        bookstore.setBookstoreBooks(Arrays.asList(book1, book2));


        when(iBookstoreRepository.findById(bookstoreId)).thenReturn(Optional.of(bookstore));
        when(iBookstoreRepository.save(bookstore)).thenReturn(bookstore);

        Book book3 = new Book();
        book3.setBookName("Book 3");
        book3.setBookBookstores(new ArrayList<>());
        book3.setUserBookRental(new ArrayList<>());
        book3.setBooksUsers(new ArrayList<>());

        Book book4 = new Book();
        book4.setBookName("Book 4");
        book4.setBookBookstores(new ArrayList<>());
        book4.setUserBookRental(new ArrayList<>());
        book4.setBooksUsers(new ArrayList<>());

        Bookstore updatedBookstore = new Bookstore();
        updatedBookstore.setBookstoreId(bookstoreId);
        updatedBookstore.setBookstoreName("Bookstore 2");
        updatedBookstore.setBookstoreAddress("Address 2");
        updatedBookstore.setBookstoreBooks(Arrays.asList(book3, book4));

        Bookstore result = bookstoreService.updateBookstore(bookstoreId, updatedBookstore);

        assertEquals(updatedBookstore.getBookstoreName(), result.getBookstoreName());
        assertEquals(updatedBookstore.getBookstoreBooks(), result.getBookstoreBooks());

        Mockito.verify(iBookstoreRepository).findById(bookstoreId);
        Mockito.verify(iBookstoreRepository).save(argThat(argument -> argument.getBookstoreName().equals("Bookstore 2")
                && argument.getBookstoreBooks().equals(Arrays.asList(book3, book4))));
    }

    @Test
    void getAllBookstores() {
        List<Bookstore> bookstoreList = Arrays.asList(new Bookstore(), new Bookstore(),new Bookstore());

        when(iBookstoreRepository.findAll()).thenReturn(bookstoreList);

        List<Bookstore> result = bookstoreService.getAllBookstores();

        assertEquals(bookstoreList, result);

        verify(iBookstoreRepository).findAll();
    }
}