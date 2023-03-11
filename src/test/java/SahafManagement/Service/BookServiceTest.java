package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.User;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
@RunWith(MockitoJUnitRunner.class) ile jUnnit testlerini çalıştırmak için sahte çerçeve olarak Mockito'yu kullanmasını söyler.
@Moeck ile repositorynin sahte bir nesnesi oluşturulur.
@InjectMocks ile sahte  repositoryi test edilecek olan BookService'e inject eder.
 */

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private IBookRepository iBookRepository;

    @Mock
    private IUserRepository iUserRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setBookName("Test Book");

        when(iBookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        Mockito.verify(iBookRepository).save(book);

        assertEquals(book, savedBook);
    }

    @Test
    public void testDeleteBook() {
        Long id = 1L;

        bookService.deleteBook(id);

        Mockito.verify(iBookRepository).deleteById(id);
    }

    @Test
    public void testUpdateBook() {
        Long userId = 1L;
        User user1 = new User();
        user1.setUserName("User 1");
        user1.setUserPassword("12345");
        user1.setUserRole("ROLE_ADMIN");
        iUserRepository.save(user1);

        User user2 = new User();
        user2.setUserName("User 2");
        user2.setUserPassword("12345");
        user2.setUserRole("ROLE_ADMIN");
        iUserRepository.save(user2);

        Book book = new Book();
        book.setBookName("Book 1");
        book.setBooksUsers(Arrays.asList(user1, user2));

        when(iBookRepository.findById(userId)).thenReturn(Optional.of(book));
        when(iBookRepository.save(book)).thenReturn(book);

        Book updatedBook = new Book();
        updatedBook.setBookName("Book 2");

        User user3 = new User();
        user3.setUserName("User 3");
        user3.setUserPassword("123456");
        user3.setUserRole("ROLE_USER");

        User user4 = new User();
        user4.setUserName("User 4");
        user4.setUserPassword("123456");
        user4.setUserRole("ROLE_USER");
        updatedBook.setBooksUsers(Arrays.asList(user3, user4));

        Book result = bookService.updateBook(userId, updatedBook);

        assertEquals(updatedBook.getBookName(), result.getBookName());
        assertEquals(updatedBook.getBooksUsers(), result.getBooksUsers());
        Mockito.verify(iBookRepository).findById(userId);
        Mockito.verify(iBookRepository).save(argThat(argument -> argument.getBookName().equals("Book 2")
                && argument.getBooksUsers().equals(Arrays.asList(user3, user4))));

    }

    @Test
    public void testGetAllBooks() {
        List<Book> bookList = Arrays.asList(new Book(), new Book(), new Book());

        when(iBookRepository.findAll()).thenReturn(bookList);

        List<Book> result = bookService.getAllBooks();

        assertEquals(bookList, result);
        Mockito.verify(iBookRepository).findAll();
    }
}