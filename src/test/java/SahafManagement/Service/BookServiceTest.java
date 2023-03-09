package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Repository.IBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testSaveBook() {
        // Create a book instance to save
        Book book = new Book();
        book.setBookName("Test Book");

        // Mock the book repository's save() method to return the same book instance
        when(bookRepository.save(book)).thenReturn(book);

        // Call the saveBook() method on the book service
        Book savedBook = bookService.saveBook(book);

        // Verify that the save() method was called on the book repository with the same book instance
        Mockito.verify(bookRepository, times(1)).save(book);

        // Verify that the saved book instance is the same as the original book instance
        assertEquals(book, savedBook);
    }
}