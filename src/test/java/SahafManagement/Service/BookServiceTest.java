package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Repository.IBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/*
@RunWith(MockitoJUnitRunner.class) ile jUnnit testlerini çalıştırmak için sahte çerçeve olarak Mockito'yu kullanmasını söyler.
@Moeck ile repositorynin sahte bir nesnesi oluşturulur.
@InjectMocks ile sahte  repositoryi test edilecek olan BookService'e inject eder.
Yeni bir book nesnesi oluşur, sahte repository save() metodunu, kendisine verilen book'u döndürmesi gerekir.
Daha sonra servis nesnesinin saveBook() metona daha önce oluşturulmuş olan book örneği verilir.
Son adımda, repository'e gidilen book ile servisten dönen book kıyaslanır.
 */

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    @WithMockUser(username="admin",roles={"ROLE_ADMIN"})
    public void testSaveBook() {
        Book book = new Book();
        book.setBookName("Test Book");

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        Mockito.verify(bookRepository).save(book);

        assertEquals(book, savedBook);
    }
}