package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Repository.IBookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
/*
saveBook() kitapları veri tabanına kaydeder.
deleteBook() veri tabanında bulunan kitapları siler.
updateBook() veri tabanında kitabın olup olmadığını id parametresiyle kontrol eder ve updateBook parametresiyle veri tabanında bulunan değerlerini günceller.
getAllBooks() Veri tabanında bulunan kitapları geri döndürür.
 */

@Service
public class BookService {
    private IBookRepository iBookRepository;

    public BookService(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    public Book saveBook(Book book) {
        return iBookRepository.save(book);
    }

    public void deleteBook(Long id) {
        iBookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = iBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " id numaralı kitap bulunamadı.."));

        book.setBookName(updatedBook.getBookName());
        book.setBooksUsers(updatedBook.getBooksUsers());
        book.setBookBookstores(updatedBook.getBookBookstores());

        return iBookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return iBookRepository.findAll();
    }
}
